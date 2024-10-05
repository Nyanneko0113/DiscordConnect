package org.nyanneko0113.discordconnect;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.nyanneko0113.discordconnect.listeners.PlayerChatListener;
import org.nyanneko0113.discordconnect.managers.DiscordManager;
import org.nyanneko0113.discordconnect.managers.SettingManager;

import javax.security.auth.login.LoginException;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
        registerListener();

        try {
            DiscordManager.startBot();
        } catch (LoginException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        saveDefaultConfig();

        TextChannel send_channel = SettingManager.getSendChannel();
        getLogger().info("準備が完了しました。");
        getLogger().info("メッセージを送信するチャンネル:" + send_channel.getName() + "(" + send_channel.getId() + ")");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {

    }

    private void registerListener() {
        PluginManager plm = Bukkit.getServer().getPluginManager();

        plm.registerEvents(new PlayerChatListener(), this);
    }

    public static Main getInstance() {
        return getPlugin(Main.class);
    }

}
