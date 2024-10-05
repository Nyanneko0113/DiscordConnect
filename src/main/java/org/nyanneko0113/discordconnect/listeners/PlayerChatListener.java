package org.nyanneko0113.discordconnect.listeners;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.nyanneko0113.discordconnect.managers.SettingManager;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        TextChannel send_channel = SettingManager.getSendChannel();
        send_channel.sendMessage(player.getName() + ":" + event.getMessage()).queue();
    }

}
