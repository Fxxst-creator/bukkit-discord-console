package net.fxxstu.console.discord.command;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.fxxstu.console.BukkitDiscordConsole;
import net.fxxstu.console.discord.precommand.executor.DiscordCommandExecutor;

public class PingCMD extends DiscordCommandExecutor {

    @Override
    @Command(name = "ping", description = "Analisar a latência do bot.")
    public void execute(BukkitDiscordConsole plugin, User user, Message message, String label, String[] args) {
        long ping = message.getJDA().getGatewayPing();

        message.getChannel().sendMessage(":ping_pong: **|** " + user.getAsMention() + ", **Pong!** " + ping + "ms").queue();
    }
}
