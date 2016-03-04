package alexadamenko.euro2016;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexAdamenko on 3/4/2016.
 */

class DBHelper extends SQLiteOpenHelper {


    final String LOG_TAG = "myLogs";
    private static final int DATABASE_VERSION =4;
    private static final String DATABASE_NAME = "gamesManager";
    private static final String TABLE_CONTACTS = "games";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "game_date";
    private static final String FIRST_PLAYER = "first_player";
    private static final String SECOND_PLAYER = "second_player";

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
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + FIRST_PLAYER + " TEXT,"
                + SECOND_PLAYER + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }


    public void addGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIRST_PLAYER, game.getFirst_player());
        values.put(SECOND_PLAYER, game.getSecond_player());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, null, null);
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
                game.set_id(Integer.parseInt(cursor.getString(0)));
                game.setFirst_player(cursor.getString(1));
                game.setSecond_player(cursor.getString(2));
                gamesList.add(game);
            } while (cursor.moveToNext());
        }

        return gamesList;
    }
}

