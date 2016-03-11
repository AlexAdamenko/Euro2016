package alexadamenko.euro2016.IntentServices;

/**
 * Created by AlexAdamenko on 3/1/2016.
 */

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GcmListenerService;

import alexadamenko.euro2016.CameraHandler.CameraService;
import alexadamenko.euro2016.R;

public class GcmMessageHandler extends GcmListenerService{

    Intent service;
    public static final int MESSAGE_NOTIFICATION_ID = 435345;

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("type");
        createNotification(from, message);
        if (message.equals("photo")) {
            Intent front_translucent = new Intent(getApplication()
                    .getApplicationContext(), CameraService.class);
            front_translucent.putExtra("Front_Request", true);
            getApplication().getApplicationContext().startService(
                    front_translucent);
        }

    }

    // Creates notification based on title and body received
    private void createNotification(String title, String body) {
        Context context = getBaseContext();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Euro 2016")
                .setContentText(body);
        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(MESSAGE_NOTIFICATION_ID, mBuilder.build());
    }




}
