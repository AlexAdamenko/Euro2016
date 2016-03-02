package alexadamenko.euro2016;

        import android.content.Intent;
        import android.provider.MediaStore;
        import android.support.v7.app.AppCompatActivity;

/**
 * Created by AlexAdamenko on 3/2/2016.
 */
public class PhotoHandler extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager())!=null) {
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }

    }
}
