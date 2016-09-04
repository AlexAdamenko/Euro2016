package alexadamenko.euro2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Send button */
    public void openGamesList(View view) {
        Intent intent = new Intent(this, GamesList.class);
        startActivity(intent);
    }

    public void openGallery(View view) {
        Intent intent = new Intent(this, Gallery.class);
        startActivity(intent);
    }
}
