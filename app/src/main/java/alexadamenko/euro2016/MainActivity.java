package alexadamenko.euro2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launchTestService();

    }

    public void launchTestService(){
        // Construct our Intent specifying the Service
        Intent i1 = new Intent(this, MyInstanceIDListenerService.class);
        Intent i2 = new Intent(this, RegistrationIntentService.class);
        startService(i1);
        startService(i2);
    };
}
