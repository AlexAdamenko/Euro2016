package alexadamenko.euro2016;

/**
 * Created by AlexAdamenko on 3/4/2016.
 */
public class Game {

    int _id;
    String game_id;
    String first_player;
    String second_player;
    String game_date;
    String time;
    String stage;

    public Game() {

    }



    public Game(String first_player, String second_player) {
        this.first_player = first_player;
        this.second_player = second_player;
    }

    public Game(int _id, String game_id, String first_player, String second_player, String date, String time, String stage) {
        this._id = _id;
        this.game_id = game_id;
        this.first_player = first_player;
        this.second_player = second_player;
        this.game_date = date;
        this.time = time;
        this.stage = stage;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getFirst_player() {
        return first_player;
    }

    public void setFirst_player(String first_player) {
        this.first_player = first_player;
    }

    public String getSecond_player() {
        return second_player;
    }

    public void setSecond_player(String second_player) {
        this.second_player = second_player;
    }

    public String getDate() {
        return game_date;
    }

    public void setDate(String date) {
        this.game_date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

}
