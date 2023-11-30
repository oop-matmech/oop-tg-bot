package org.example.bot.command;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.communicator.ICommunicator;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Класс с набором полей для обработки сообщения в тг
 */
public class CommunicatorWrapper implements Command {
    protected ICommunicator communicator;
    protected Message message;
    protected Bot bot;

    public CommunicatorWrapper() {

    }

    public CommunicatorWrapper(ICommunicator communicator, Message message, Bot bot) {
        this.communicator = communicator;
        this.message = message;
        this.bot = bot;
    }

    public ICommunicator getCommunicator() {
        return communicator;
    }

    public void setCommunicator(ICommunicator communicator) {
        this.communicator = communicator;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Bot getBot() {
        return bot;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void execute() {

    }

    @Override
    public String name() {
        return null;
    }
}
