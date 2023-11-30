package org.example.bot.command;

import org.example.bot.Bot;
import org.example.bot.communicator.ICommunicator;
import org.example.db.UserDatabase.dao.PlayListDao;
import org.example.db.UserDatabase.dao.SongDao;
import org.example.db.UserDatabase.dao.UserDao;
import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbEntities.UserEntity;
import org.example.db.UserDatabase.dbService.PlayListService;
import org.example.db.UserDatabase.dbService.SongsService;
import org.example.db.UserDatabase.dbService.UserService;
import org.example.service.music.MusicApi;
import org.example.utils.FormatArtists;
import org.example.utils.FormatTracks;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;

/**
 * Class responsable for command handle
 *
 * @author StAl15
 */
//some
public class CommandHandler {

    private final Bot bot;
    private final ICommunicator communicator;
    private final SongDao songDao = new SongDao();
    private final PlayListDao playListDao = new PlayListDao();
    private final UserDao userDao = new UserDao();
    private final MusicApi musicApi = new MusicApi();
    private final FormatTracks formatTracks = new FormatTracks();
    private final FormatArtists formatArtists = new FormatArtists();

    public CommandHandler(Bot bot, ICommunicator communicator) {
        this.bot = bot;
        this.communicator = communicator;
    }

    public void handle(Command command, Message message) {
        switch (command) {
            case START -> {
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
            case HELP -> {
                var args = message.getText().trim().split(" ");
                var res = args.length > 1 ? args[1] : args[0];
                communicator.sendText(
                        bot,
                        message.getFrom().getId(),
                        helper(res).trim()
                );
            }
            case ABOUT -> {
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
            case GET_SINGERS -> {
                communicator.sendText(
                        bot,
                        message.getFrom().getId(),
                        """
                                Загрузка...
                                """.trim()
                );
                String api = formatArtists.format(musicApi.getTopArtists());
                String res = String.format("Список исполнителей: %s", api);
                communicator.sendText(
                        bot,
                        message.getFrom().getId(),
                        res.trim()
                );
            }
            case GET_POPULAR -> {
                communicator.sendText(
                        bot,
                        message.getFrom().getId(),
                        """
                                Загрузка...
                                """.trim()
                );
                String api = formatTracks.format(musicApi.getTopTracks());
                String res = String.format("Список чартов:\n%s", api);
                String[] arr = res.split("\n\n");
                for (String s : arr) {
                    communicator.sendText(
                            bot,
                            message.getFrom().getId(),
                            s
                    );
                }
            }
            case CREATE_PLAYLIST -> {
                var args = message.getText().replace("/create_playlist", "").split(" ");
                var from = message.getFrom();
                var isEmpty = userDao.findAll()
                        .stream()
                        .filter(it -> it.getName() == from.getUserName())
                        .toList()
                        .get(0) == null;
                try {
                    if (isEmpty) {
                        UserEntity user = new UserEntity(from.getUserName(), from.getId().toString());
                        userDao.save(user);
                    }
                    var currUser = userDao.findByName(from.getUserName());
                    PlayListEntity playList = new PlayListEntity(args[0], args[1], currUser);
                    userDao.addPlaylist(currUser.getId(), playList);
                    communicator.sendText(
                            bot,
                            message.getFrom().getId(),
                            "Создан плейлист с именем: " + args[0]
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                    communicator.sendText(
                            bot,
                            message.getFrom().getId(),
                            "Произошла ошибка: " + e.getMessage()
                    );
                }

            }
            case GET_PLAYLIST -> {

            }
            case ADD_SONG -> {
                String songname = message.getText().replace("/add", "");
                String username = message.getFrom().getUserName();

            }
            case SHARE_PLAYLIST -> {

            }
            default -> {
                communicator.copyMessage(bot, message);
            }
        }
    }

    public static Command toCommand(String text) {
        return switch (text) {
            case "/start" -> Command.START;
            case "/help" -> Command.HELP;
            case "/about" -> Command.ABOUT;
            case "/get_singers" -> Command.GET_SINGERS;
            case "/get_popular" -> Command.GET_POPULAR;
            case "/share" -> Command.SHARE_PLAYLIST;
            case "/add" -> Command.ADD_SONG;
            case "/get_playlist" -> Command.GET_PLAYLIST;
            case "/create_playlist" -> Command.CREATE_PLAYLIST;
            default -> Command.UNKNOWN;
        };
    }

    public static String helper(String command) {
        return switch (command) {
            case "about" -> "информация о командах";
            case "get_popular" -> "Список исполнителей";
            case "share" -> "Делиться плейлистом";
            case "add" -> "Добавить песню";
            case "get_playlist" -> "Получить плейлист";
            default -> "А кому щас легко";
        };
    }

}
