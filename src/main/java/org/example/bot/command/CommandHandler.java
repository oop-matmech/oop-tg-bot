package org.example.bot.command;

import org.example.bot.Bot;
import org.example.bot.command.listCommands.*;
import org.example.bot.communicator.ICommunicator;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;

/**
 * Class responsable for command handle
 *
 * @author StAl15
 */
//some
public class CommandHandler {

    private final Bot bot;
    private final ICommunicator communicator;

    public CommandHandler(Bot bot, ICommunicator communicator) {
        this.bot = bot;
        this.communicator = communicator;
    }

    public void handle(String command, Message message) {
        getCommand(command, message).execute();
    }

    public Command getCommand(String command, Message message) {
        HashMap<String, Command> hashMap = new HashMap<>();
        hashMap.put("/start", new StartCommand(communicator, message, bot));
        hashMap.put("/about", new AboutCommand(communicator, message, bot));
        hashMap.put("/help", new HelpCommand(communicator, message, bot));
        hashMap.put("/get_popular", new GetPopularCommand(communicator, message, bot));
        hashMap.put("/add", new StartCommand(communicator, message, bot));
        hashMap.put("/create_playlist", new CreatePlaylistCommand(communicator, message, bot));
        return hashMap.getOrDefault(command, new DefaultCommand(communicator, message, bot));
    }

}
