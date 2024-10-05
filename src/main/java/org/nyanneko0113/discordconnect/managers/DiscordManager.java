package org.nyanneko0113.discordconnect.managers;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.bukkit.Bukkit;
import org.nyanneko0113.discordconnect.Main;
import org.nyanneko0113.discordconnect.listeners.UserChatListener;

import javax.security.auth.login.LoginException;
import java.util.stream.Collectors;

public class DiscordManager extends ListenerAdapter implements EventListener {

    private static JDA jda;
    private static final String token = Main.getInstance().getConfig().getString("token");

    public static void startBot() throws LoginException, InterruptedException {
        if (jda == null) {
            jda = JDABuilder.createDefault(token)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .addEventListeners(new DiscordManager())
                    .addEventListeners(new UserChatListener())
                    .build();
            jda.awaitReady();

            CommandListUpdateAction commands = jda.updateCommands();
            commands.addCommands(Commands.slash("server-join", "サーバーに参加するコマンド")
                            .addOption(OptionType.STRING, "code", "認証コード", false))
                    .queue();


            Bukkit.getLogger().info("[DiscordUtil] [情報] ボットが起動しました。");
            Bukkit.getLogger().info("[DiscordUtil] [情報] 利用可能なコマンド：" + commands.complete().stream().map(Command::getName).collect(Collectors.toSet()));
        }
    }

    public static JDA getJda() {
        return jda;
    }

    public static void stopBot() {
        if (jda != null) {
            jda.shutdownNow();
        }
        else {
            Bukkit.getLogger().info("[DiscordUtil] [エラー] ボットが起動されていないためシャットダウンできません。");
        }
    }


}
