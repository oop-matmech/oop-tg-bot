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

    public CommunicatorWrapper getCommand(String command, Message message) {
        HashMap<String, CommunicatorWrapper> hashMap = new HashMap<>();
        hashMap.put("/start", new StartCommand());
        hashMap.put("/about", new AboutCommand());
        hashMap.put("/help", new HelpCommand());
        hashMap.put("/get_popular", new GetPopularCommand());
        hashMap.put("/add", new StartCommand());
        hashMap.put("/create_playlist", new CreatePlaylistCommand());
        CommunicatorWrapper res = hashMap.getOrDefault(command, new DefaultCommand());
        res.setMessage(message);
        res.setBot(bot);
        res.setCommunicator(communicator);
        return res;
    }

}
