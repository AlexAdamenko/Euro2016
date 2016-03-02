package alexadamenko.euro2016;

/**
 * Created by AlexAdamenko on 3/1/2016.
 */

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GcmListenerService;

public class GcmMessageHandler extends GcmListenerService{
    public static final int MESSAGE_NOTIFICATION_ID = 435345;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        createNotification(from, message);
    }

    // Creates notification based on title and body received
    private void createNotification(String title, String body) {
        

        Context context = getBaseContext();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("ZALOOOPPA")
                .setContentText("ZALOOOPPA");
        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(MESSAGE_NOTIFICATION_ID, mBuilder.build());
    }


}
