package net.fxxstu.console.discord;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.fxxstu.console.discord.command.ExecutorCMD;
import net.fxxstu.console.discord.command.PingCMD;
import net.fxxstu.console.discord.listener.UserMentionClientListener;
import net.fxxstu.console.discord.precommand.DiscordCommandLoader;
import org.bukkit.configuration.file.FileConfiguration;

public class DiscordApplication {

    @Getter private final JDA jda;
    @Getter private final String prefix;
    @Getter private final DiscordCommandLoader commandLoader;

    public DiscordApplication(FileConfiguration config) throws Exception {
        this.jda = JDABuilder.createDefault(config.getString("token")).build();
        this.prefix = config.getString("prefix");
        this.commandLoader = new DiscordCommandLoader(jda, prefix);

        this.jda.setAutoReconnect(true);
        this.jda.getPresence().setStatus(OnlineStatus.ONLINE);

        int activityTypeIndex = config.getInt("activity.type");
        Activity.ActivityType activityType = Activity.ActivityType.values()[activityTypeIndex];
        String activityMessage = config.getString("activity.message");

        this.jda.getPresence().setActivity(Activity.of(activityType, activityMessage));

        this.loadCommands();
        this.loadListeners();
    }

    private void loadCommands() {
        this.commandLoader.registerCommand(new ExecutorCMD());
        this.commandLoader.registerCommand(new PingCMD());
    }

    private void loadListeners() {
        this.jda.addEventListener(new UserMentionClientListener());
    }
}
