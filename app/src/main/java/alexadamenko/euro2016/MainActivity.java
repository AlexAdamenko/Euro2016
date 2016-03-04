package alexadamenko.euro2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    Button subscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        System.out.println("Inserting ..");
        dbHelper.addGame(new Game("Bayern", "Manchester"));
        dbHelper.addGame(new Game("Liverpool", "Barcelona"));
        dbHelper.addGame(new Game("Juventus", "Porto"));

        System.out.println("Reading all contacts..");
        List<Game> games = dbHelper.getAllGames();

        LinearLayout LinearLayoutView = new LinearLayout(this);
        TextView DisplayStringArray = new TextView(this);
        DisplayStringArray.setTextSize(25);
        Button button = new Button(this);
        button.setText("Sub");
        LinearLayoutView.addView(DisplayStringArray);
        LinearLayoutView.addView(button);

        for (Game cn : games){

            if(cn.getFirst_player().equals("Bayern"))
            {
                DisplayStringArray.append(cn.getFirst_player() + " VS " + cn.getSecond_player());
                DisplayStringArray.append("\n");
                //subscribe.setVisibility(View.VISIBLE);
            }

        dbHelper.deleteAll();


        //Intent i1 = new Intent(this, MyInstanceIDListenerService.class);
        //Intent i2 = new Intent(this, RegistrationIntentService.class);

        //startService(i1);
       // startService(i2);

        setContentView(LinearLayoutView);


        }
}
}
