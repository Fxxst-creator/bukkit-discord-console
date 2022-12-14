package net.fxxstu.console;

/*
# 26/09/2022
*/

import lombok.Getter;
import net.fxxstu.console.discord.DiscordApplication;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class BukkitDiscordConsole extends JavaPlugin {

    public static BukkitDiscordConsole getInstance() {
        return getPlugin(BukkitDiscordConsole.class);
    }

    @Getter private DiscordApplication discordApplication;
    @Getter private Map<String, Object> configMessages;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        try {
            this.discordApplication = new DiscordApplication(getConfig());
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        this.configMessages = getConfig().getConfigurationSection("messages").getValues(false);

        Bukkit.getConsoleSender().sendMessage("§a[bukkit-discord-console] §fPlugin has enabled! §a[v" + getDescription().getVersion() + "]");
        Bukkit.getConsoleSender().sendMessage("§a[bukkit-discord-console] §fBot §9'" + discordApplication.getJda().getSelfUser().getAsTag() + "' §fonline!");
        Bukkit.getConsoleSender().sendMessage("§a[bukkit-discord-console] §fOpen source: §agithub.com/Fxxst-creator/bukkit-discord-console");
    }

   public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§a[bukkit-discord-console] §fPlugin has disabled! §a[v" + getDescription().getVersion() + "]");
   }
}
