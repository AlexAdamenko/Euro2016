package alexadamenko.euro2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    Button subscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Intent i1 = new Intent(this, MyInstanceIDListenerService.class);
        final Intent i2 = new Intent(this, RegistrationIntentService.class);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        System.out.println("Inserting ..");
        dbHelper.addGame(new Game("Bayern", "Manchester"));
        dbHelper.addGame(new Game("Liverpool", "Barcelona"));
        dbHelper.addGame(new Game("Juventus", "Porto"));

        System.out.println("Reading all contacts..");
        List<Game> games = dbHelper.getAllGames();

        final LinearLayout LinearLayoutView = new LinearLayout(this);
        TextView DisplayStringArray = new TextView(this);
        DisplayStringArray.setTextSize(25);
        final Button button = new Button(this);
        button.setText("Sub");
        LinearLayoutView.addView(DisplayStringArray);
        LinearLayoutView.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startService(i1);
                startService(i2);
                LinearLayoutView.removeView(button);
            }
        });

        for (Game cn : games){

            if(cn.getFirst_player().equals("Bayern"))
            {
                DisplayStringArray.append(cn.getFirst_player() + " VS " + cn.getSecond_player());
                DisplayStringArray.append("\n");
            }

        dbHelper.deleteAll();
        setContentView(LinearLayoutView);


        }
}
}
