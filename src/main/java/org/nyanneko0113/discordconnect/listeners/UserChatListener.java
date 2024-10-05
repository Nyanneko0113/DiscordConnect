package org.nyanneko0113.discordconnect.listeners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.CacheRestAction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class UserChatListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] cmd = event.getMessage().getContentRaw().split(" ", 2);

        JDA jda = event.getJDA();
        MessageChannelUnion channel = event.getChannel();
        Member member = event.getMember();
        User user = event.getAuthor();
        String message = event.getMessage().getContentRaw();

        if (!user.isBot()) {
            Bukkit.getLogger().info("[Discord]" + user.getGlobalName() + ":" + message);
            for (Player all_player : Bukkit.getOnlinePlayers()) {
                all_player.sendMessage(ChatColor.DARK_BLUE + "[Discord]" + ChatColor.RESET +
                        user.getName() + ":" + message);
            }
        }
    }
}
