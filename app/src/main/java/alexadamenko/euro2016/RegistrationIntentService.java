package alexadamenko.euro2016;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by AlexAdamenko on 3/1/2016.
 */

public class RegistrationIntentService extends IntentService {

    // abbreviated tag name
    private static final String PREFS_NAME = "EURO_PREFS";
    private static final String TAG = "RegIntentService";
    private static final String BASE_URL = "http://192.168.0.10:3000/post";
    private Boolean sendMessage = true;



    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Make a call to Instance API
        InstanceID instanceID = InstanceID.getInstance(this);
        String senderId = getResources().getString(R.string.gcm_defaultSenderId);
        try {
            // request token that will be used by the server to send push notifications
            String token = instanceID.getToken(senderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE);
            Log.d(TAG, "GCM Registration Token: " + token);

            // pass along this data
            sendRegistrationToServer(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendRegistrationToServer(String token) throws IOException {

        final SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        postData(token);
        editor.putString("token", token);
        editor.commit();


    }

    public void postData(String token) throws IOException {

        if(sendMessage){
        URL url = new URL(BASE_URL);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestProperty("Token", token);
        httpCon.setRequestMethod("POST");
        OutputStreamWriter out = new OutputStreamWriter(
                httpCon.getOutputStream());
        System.out.println(httpCon.getResponseCode());
        System.out.println(httpCon.getResponseMessage());
        out.close();
        sendMessage=false;
    }
    }

}
