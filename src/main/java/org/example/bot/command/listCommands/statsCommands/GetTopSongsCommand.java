package org.example.bot.command.listCommands.statsCommands;

import org.example.bot.Bot;
import org.example.bot.command.Command;
import org.example.bot.command.CommunicatorWrapper;
import org.example.bot.communicator.ICommunicator;
import org.example.db.UserDatabase.dbEntities.PlayListEntity;
import org.example.db.UserDatabase.dbEntities.SongEntity;
import org.example.db.UserDatabase.dbEntities.UserEntity;
import org.example.db.UserDatabase.dbService.PlayListService;
import org.example.db.UserDatabase.dbService.SongsService;
import org.example.db.UserDatabase.dbService.StatsService;
import org.example.db.UserDatabase.dbService.UserService;
import org.example.utils.PlaylistUrlParser;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;

public class GetTopSongsCommand extends CommunicatorWrapper implements Command {
    private final StatsService statsService = new StatsService();
    private final UserService userService = new UserService();
    private final PlayListService playListService = new PlayListService();
    private final SongsService songsService = new SongsService();

    public GetTopSongsCommand() {
    }

    public GetTopSongsCommand(ICommunicator communicator, Message message, Bot bot) {
        super(communicator, message, bot);
    }

    @Override
    public void execute() {
        communicator.sendText(
                bot,
                message.getFrom().getId(),
                """
                        Загрузка...
                        """.trim()
        );

        var args = message.getText().split(" ");
        var command = args[0];
        StringBuilder res = new StringBuilder();

        var isExist = userService.findById(3) != null;
        UserEntity user = new UserEntity();
        PlayListEntity playList = new PlayListEntity();
        if (!isExist) {
            user = new UserEntity("Superuser", "0");
            user.setId(0);
            userService.save(user);
        } else {
            user = userService.findById(3);
        }

        ArrayList<StatsService.CountEntity> itemStats = statsService.getStatsDay();
        switch (command) {
            case "/get_top_10_24h":
                res.append("Топ за 24 часа: \n\n");
//                if (playListService.userAlreadyHasPlayList(user, "Топ 10 за день")) {
//                    playListService.delete(playListService.findByNameFromUser(user, "Топ 10 за день"));
//                }
//                playList = new PlayListEntity("Топ 10 за день", "выбранные пользователями треки!", user);
//                userService.addPlaylist(user.getId(), playList);
                break;
            case "/get_top_10_1w":
                itemStats = statsService.getStatsWeek();
//                if (playListService.userAlreadyHasPlayList(user, "Топ 10 за неделю")) {
//                    try {
//                        playListService.delete(playListService.findByNameFromUser(user, "Топ 10 за неделю"));
//                    } catch (Exception ignored) {
//                    }
//                }
//                playList = new PlayListEntity("Топ 10 за неделю", "выбранные пользователями треки!", user);
//                userService.addPlaylist(user.getId(), playList);
                res.append("Топ за неделю: \n\n");
                break;
            case "/get_top_10_1m":
                itemStats = statsService.getStatsMonth();
//                if (playListService.userAlreadyHasPlayList(user, "Топ 10 за месяц")) {
//                    playListService.delete(playListService.findByNameFromUser(user, "Топ 10 за месяц"));
//                }
//                playList = new PlayListEntity("Топ 10 за месяц", "выбранные пользователями треки!", user);
//                userService.addPlaylist(user.getId(), playList);
                res.append("Топ за месяц: \n\n");
                break;
        }


        int idx = 1;
        for (int i = 0; i < itemStats.size(); i++) {
            if (idx == 11) {
                break;
            } else {
                res.append(String.format("%s. %s - %s прослушиваний \n url: %s \n ----------\n", idx, itemStats.get(i).stats.getSong().getName(), itemStats.get(i).counter, itemStats.get(i).stats.getSong().getUrl()));
//                SongEntity currSong = itemStats.get(i).stats.getSong();
//                var boolAdding = playListService.addSongToPlaylist(playList.getId(), currSong);
//                currSong.addPlaylist(playList);
//                try {
//                    songsService.update(currSong);
//                } catch (Exception ignored) {
//                }
                idx++;
            }
        }

        if (itemStats.isEmpty()) {
            res.append("За выбранные промежуток времени нет чарта.");
        }
//        else {
//            res.append("\n\nСсылка на плейлист: ")
//                    .append(PlaylistUrlParser.toURL(playList))
//                    .append("\n");

//        }
        communicator.sendText(
                bot,
                message.getFrom().getId(),
                res.toString()
        );


    }

    @Override
    public String name() {
        return "start";
    }
}