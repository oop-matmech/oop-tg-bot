package org.example.bot.command.listCommands.infoCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.command.CommunicatorWrapper;
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
                        Привет, я музыкальный бот!
                                                
                        /help - мои команды
                        /about - обо мне
                        """.trim()
        );
    }

    @Override
    public String name() {
        return "start";
    }
}
