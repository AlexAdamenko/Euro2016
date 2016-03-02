package alexadamenko.euro2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    PhotoHandler test = new PhotoHandler();
    TextView textElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        textElement = (TextView) findViewById(R.id.testtext);

        Intent i1 = new Intent(this, MyInstanceIDListenerService.class);
        Intent i2 = new Intent(this, RegistrationIntentService.class);

        startService(i1);
        startService(i2);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
}
