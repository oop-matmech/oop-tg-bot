package org.example.bot.command;

import org.example.bot.Bot;
import org.example.bot.communicator.ICommunicator;
import org.example.db.UserDatabase.Database;
import org.example.db.UserDatabase.dbMethods.PlayListTable;
import org.example.db.UserDatabase.dbMethods.SongsTable;
import org.example.db.UserDatabase.dbMethods.UserTable;
import org.example.service.music.MusicApi;
import org.example.utils.FormatArtists;
import org.example.utils.FormatTracks;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;

/**
 * Class responsable for command handle
 *
 * @author StAl15
 */
//some
public class CommandHandler {

    private final Bot bot;
    private final ICommunicator communicator;

    private final MusicApi musicApi = new MusicApi();
    private final FormatTracks formatTracks = new FormatTracks();
    private final FormatArtists formatArtists = new FormatArtists();
    private final SongsTable songsTable = new SongsTable();
    private final UserTable userTable = new UserTable();
    private final PlayListTable playListTable = new PlayListTable();
    private Database database = new Database();

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
                communicator.sendText(
                        bot,
                        message.getFrom().getId(),
                        """
                                А кому щас легко
                                """.trim()
                );
            }
            case ABOUT -> {
                communicator.sendText(
                        bot,
                        message.getFrom().getId(),
                        """
                                /get_singers - список исполнителей
                                /get_popular - список популярных треков
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
            case GET_PLAYLIST -> {

            }
            case ADD_SONG -> {
                String songname = message.getText().replace("/add", "");
                String username = message.getFrom().getUserName();

                if (userTable.getItemByName(database.getConnection(), username) != null) {

                }
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
            default -> Command.UNKNOWN;
        };
    }

}
