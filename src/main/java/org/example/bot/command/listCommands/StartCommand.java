package org.example.bot.command.listCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.communicator.ICommunicator;
import org.telegram.telegrambots.meta.api.objects.Message;

public class StartCommand extends CommunicatorWrapper implements Command {
    public StartCommand(ICommunicator communicator, Message message, Bot bot) {
        super(communicator, message, bot);
    }

    public StartCommand() {
    }

    @Override
    public void execute() {
        communicator.sendText(
                bot,
                message.getFrom().getId(),
                """
                        Добро пожаловать
                        Команды:
                        /help - памагите
                        /about - хто я
                        """.trim()
        );
    }

    @Override
    public String name() {
        return "start";
    }
}
