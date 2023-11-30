package org.example.bot.command.listCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.command.CommunicatorWrapper;
import org.example.bot.communicator.ICommunicator;
import org.telegram.telegrambots.meta.api.objects.Message;

public class DefaultCommand extends CommunicatorWrapper implements Command {
    public DefaultCommand(ICommunicator communicator, Message message, Bot bot) {
        super(communicator, message, bot);
    }

    public DefaultCommand() {
    }

    @Override
    public void execute() {
        communicator.copyMessage(bot, message);
    }

    @Override
    public String name() {
        return "default";
    }
}