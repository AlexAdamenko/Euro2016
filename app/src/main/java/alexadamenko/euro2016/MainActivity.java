package alexadamenko.euro2016;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private static final String PREFS_NAME = "EURO_PREFS";
    DBHelper dbHelper;
    GameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);

        dbHelper = new DBHelper(this);

        final SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        final SubcriptionService subService = new SubcriptionService(this);

        super.onCreate(savedInstanceState);

        final Intent i1 = new Intent(this, MyInstanceIDListenerService.class);
        final Intent i2 = new Intent(this, RegistrationIntentService.class);

        startService(i1);
        startService(i2);


        Boolean DbisLoaded = prefs.getBoolean("DBloaded", false);


        if(!DbisLoaded)
        {
            new DataFetcherTask().execute();
            editor.putBoolean("DBloaded", true);
            editor.commit();
        }
        else
        {
            new DataFetcherTask().currentMatches();
        }

    }




        /*Boolean subbed = prefs.getBoolean("subbed", false);

                final LinearLayout LinearLayoutView = new LinearLayout(this);
        TextView DisplayStringArray = new TextView(this);
        DisplayStringArray.setTextSize(25);
        LinearLayoutView.addView(DisplayStringArray);

        if(!subbed) {
            final Button button = new Button(this);
            button.setText("Sub");
            LinearLayoutView.addView(button);

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    LinearLayoutView.removeView(button);
                    editor.putString("game", "GAME");
                    editor.putBoolean("subbed", true);
                    editor.commit();
                    try {
                        subService.subscribe();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
        }


         final Button clearPrefs = new Button(this);
         clearPrefs.setText("clearPrefs");
         LinearLayoutView.addView(clearPrefs);

        clearPrefs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editor.clear();
                editor.commit();
           }
       });*/




    class DataFetcherTask extends AsyncTask<Void,Void,Void> {

        String jsonTxt;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String serverData = null;
            InputStream is = null;
            try {
                is = getAssets().open("matches.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                jsonTxt = new String(buffer, "UTF-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            serverData = jsonTxt;
            Log.d("response", serverData);

            try {
                JSONObject jsonObject = new JSONObject(serverData);
                JSONArray jsonArray = jsonObject.getJSONArray("matches");

                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObjectCity = jsonArray.getJSONObject(i);
                    String gameID = jsonObjectCity.getString("game_id");
                    String firstPlayer = jsonObjectCity.getString("first_player");
                    String secondPlayer = jsonObjectCity.getString("second_player");
                    String date = jsonObjectCity.getString("date");
                    String time = jsonObjectCity.getString("time");
                    String stage = jsonObjectCity.getString("stage");
                    Game game = new Game();
                    game.setGame_id(gameID);
                    game.setFirst_player(firstPlayer);
                    game.setSecond_player(secondPlayer);
                    game.setDate(date);
                    game.setTime(time);
                    game.setStage(stage);
                    dbHelper.addGame(game);// Inserting into DB

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            currentMatches();
        }

        public void currentMatches(){
            List<Game> games = dbHelper.getMatchDay("11.06.2016");
            adapter = new GameAdapter(MainActivity.this,games);
            listView.setAdapter(adapter);
        }

    }
}
