package org.example.bot.command.listCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.communicator.ICommunicator;
import org.telegram.telegrambots.meta.api.objects.Message;

public class AboutCommand extends CommunicatorWrapper implements Command {
    public AboutCommand(ICommunicator communicator, Message message, Bot bot) {
        super(communicator, message, bot);
    }

    public AboutCommand() {
    }

    @Override
    public void execute() {
        communicator.sendText(
                bot,
                message.getFrom().getId(),
                """
                        /get_singers - список исполнителей
                        /get_popular - список популярных треков
                        /start - запуск
                        /about - о командах
                        """.trim()
        );
    }

    @Override
    public String name() {
        return "start";
    }
}

