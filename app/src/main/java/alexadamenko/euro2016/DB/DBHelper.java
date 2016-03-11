package alexadamenko.euro2016.DB;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import alexadamenko.euro2016.DB.Models.Game;

/**
 * Created by AlexAdamenko on 3/4/2016.
 */

public class DBHelper extends SQLiteOpenHelper {


    final String LOG_TAG = "myLogs";
    private static final int DATABASE_VERSION =23;
    private static final String DATABASE_NAME = "gamesManager";
    private static final String TABLE_CONTACTS = "games";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "game_id";
    private static final String FIRST_PLAYER = "first_player";
    private static final String SECOND_PLAYER = "second_player";
    private static final String KEY_DATE = "game_date";
    private static final String KEY_TIME = "time";
    private static final String KEY_STAGE = "stage";
    String CREATE_TABLE = "CREATE TABLE "+TABLE_CONTACTS+" ("+KEY_ID+" INTEGER PRIMARY KEY," + KEY_NAME+" TEXT,"+FIRST_PLAYER+" TEXT,"+SECOND_PLAYER+" TEXT,"+KEY_DATE+" TEXT,"+KEY_TIME+" TEXT,"+KEY_STAGE+" TEXT)";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }


    public void addGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, game.getGame_id());
            values.put(FIRST_PLAYER, game.getFirst_player());
            values.put(SECOND_PLAYER, game.getSecond_player());
            values.put(KEY_DATE, game.getDate());
            values.put(KEY_TIME, game.getTime());
            values.put(KEY_STAGE, game.getStage());
            db.insert(TABLE_CONTACTS, null, values);
            db.close();
        } catch (Exception e) {
            Log.e("problem",e+"");
        }
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, null, null);
        db.close();
    }

    public void updateValue(String id, String value){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_STAGE,value);
        db.update(TABLE_CONTACTS, cv, "game_id=" + "'" + id + "'", null);
        db.close();
    }

    public List<Game> getAllGames() {
        List<Game> gamesList = new ArrayList<Game>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Game game = new Game();
                game.set_id(cursor.getInt(0));
                game.setGame_id(cursor.getString(1));
                game.setFirst_player(cursor.getString(2));
                game.setSecond_player(cursor.getString(3));
                game.setDate(cursor.getString(4));
                game.setTime(cursor.getString(5));
                game.setStage(cursor.getString(6));
                gamesList.add(game);
            } while (cursor.moveToNext());
        }

        db.close();
        return gamesList;
    }

    public List<Game>  getMatchDay(String date) {
        List<Game> gamesListOnDay = new ArrayList<Game>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS +" WHERE game_date = " + "'" + date + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Game game = new Game();
                game.set_id(cursor.getInt(0));
                game.setGame_id(cursor.getString(1));
                game.setFirst_player(cursor.getString(2));
                game.setSecond_player(cursor.getString(3));
                game.setDate(cursor.getString(4));
                game.setTime(cursor.getString(5));
                game.setStage(cursor.getString(6));
                gamesListOnDay.add(game);
            } while (cursor.moveToNext());
        }

        db.close();
        return gamesListOnDay;
    }
}

