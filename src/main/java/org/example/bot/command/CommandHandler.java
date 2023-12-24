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
public class CommandHandler {

    private final Bot bot;
    private final ICommunicator communicator;

    public CommandHandler(Bot bot, ICommunicator communicator) {
        this.bot = bot;
        this.communicator = communicator;
    }

    /**
     * Функция выполнения команды
     *
     * @param command строка команды
     * @param message объект сообщения тг
     */
    public void handle(String command, Message message) {
        getCommand(command, message).execute();
    }

    /**
     * Функция извлечения команды
     *
     * @param command команда
     * @param message объект сообщения тг
     * @return CommunicatorWrapper - объект команды с коммуникатором
     */
    public CommunicatorWrapper getCommand(String command, Message message) {
        HashMap<String, CommunicatorWrapper> hashMap = new HashMap<>();
        hashMap.put("/start", new StartCommand());
        hashMap.put("/about", new HelpCommand());
        hashMap.put("/find", new FindCommand());
        hashMap.put("/help", new AboutCommand());
        hashMap.put("/get_popular", new GetPopularCommand());
        hashMap.put("/add", new AddSongCommand());
        hashMap.put("/create_playlist", new CreatePlaylistCommand());
        hashMap.put("/get", new GetSongsFromPlaylistCommand());
        hashMap.put("/get_my_playlists", new GetMyPlaylistsCommand());
        hashMap.put("/share_playlist", new SharePlaylistCommand());
        hashMap.put("/get_playlist", new GetPlaylistCommand());
        hashMap.put("/get_top_10_24h", new GetTopSongsCommand());
        hashMap.put("/get_top_10_1w", new GetTopSongsCommand());
        hashMap.put("/get_top_10_1m", new GetTopSongsCommand());
        hashMap.put("/like", new LikeCommand());


        CommunicatorWrapper res = hashMap.getOrDefault(command, new DefaultCommand());
        res.setMessage(message);
        res.setBot(bot);
        res.setCommunicator(communicator);
        return res;
    }

}
