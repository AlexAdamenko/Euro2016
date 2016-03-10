package alexadamenko.euro2016;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "EURO_PREFS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        final SubcriptionService subService = new SubcriptionService(this);

        final Intent i1 = new Intent(this, MyInstanceIDListenerService.class);
        final Intent i2 = new Intent(this, RegistrationIntentService.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(i1);
        startService(i2);

        DBHelper dbHelper = new DBHelper(this);
        System.out.println("Inserting ..");
        dbHelper.addGame(new Game("Russia", "Austria"));
        dbHelper.addGame(new Game("Liverpool", "Barcelona"));
        dbHelper.addGame(new Game("Juventus", "Porto"));

        System.out.println("Reading all contacts..");
        List<Game> games = dbHelper.getAllGames();

        final LinearLayout LinearLayoutView = new LinearLayout(this);
        TextView DisplayStringArray = new TextView(this);
        DisplayStringArray.setTextSize(25);
        LinearLayoutView.addView(DisplayStringArray);


        Boolean subbed = prefs.getBoolean("subbed", false);

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
       });

        for (Game cn : games){

            if(cn.getFirst_player().equals("Russia"))
            {
                DisplayStringArray.append(cn.getFirst_player() + " VS " + cn.getSecond_player());
                DisplayStringArray.append("\n");
            }

        dbHelper.deleteAll();
        setContentView(LinearLayoutView);


        }
}
}
