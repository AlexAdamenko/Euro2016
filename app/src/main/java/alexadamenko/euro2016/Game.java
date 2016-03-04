package alexadamenko.euro2016;

/**
 * Created by AlexAdamenko on 3/4/2016.
 */
public class Game {

    int _id;
    String first_player;
    String second_player;

    public Game() {

    }

    public Game(int _id, String date, String first_player, String second_player) {
        this._id = _id;
        this.first_player = first_player;
        this.second_player = second_player;
    }

    public Game(String first_player, String second_player) {
        this.first_player = first_player;
        this.second_player = second_player;
    }



    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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





}
