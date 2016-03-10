package alexadamenko.euro2016;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by AlexAdamenko on 3/10/2016.
 */
public class SubcriptionService{

    private static final String PREFS_NAME = "EURO_PREFS";
    private static final String BASE_URL = "http://192.168.0.10:3000/update";
    private static final String TAG = "RegIntentService";
    Activity mainActivity;

    public SubcriptionService(MainActivity mainActivity) {

        this.mainActivity = mainActivity;

    }

    public void subscribe() throws IOException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        SharedPreferences prefs = mainActivity.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        String token = prefs.getString("token", "No token provided");
        String game = prefs.getString("game", "No game selected");

        Log.d(TAG, "GCM Registration T!!!!oken: " + token);
        Log.d(TAG, "GCM Registration Toke!!!!!n: " + game);
        URL url = new URL(BASE_URL);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestProperty("Token", token);
        httpCon.setRequestProperty("Game", game);
        httpCon.setRequestMethod("POST");
        OutputStreamWriter out = new OutputStreamWriter(
                httpCon.getOutputStream());
        System.out.println(httpCon.getResponseCode());
        System.out.println(httpCon.getResponseMessage());
        out.close();


    }


}
