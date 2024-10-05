package org.nyanneko0113.discordconnect.managers;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.configuration.file.FileConfiguration;
import org.nyanneko0113.discordconnect.Main;

public class SettingManager {

    public static TextChannel getSendChannel() {
        String channel_id = getConfig().getString("send_channel_id");
        return DiscordManager.getJda().getChannelById(TextChannel.class, channel_id);
    }

    private static FileConfiguration getConfig() {
        return Main.getInstance().getConfig();
    }
}
