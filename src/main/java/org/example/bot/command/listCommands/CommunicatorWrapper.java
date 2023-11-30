package org.example.bot.command.listCommands;

import org.example.bot.Bot;
import org.example.bot.communicator.ICommunicator;
import org.telegram.telegrambots.meta.api.objects.Message;

public class CommunicatorWrapper {
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
}
