package free.parking.parkit;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by veladii on 6/8/17.
 */

public class FcmInstanceIdService extends FirebaseInstanceIdService {

    public static final String TAG = "NOTIFICATION";

    @Override
    public void onTokenRefresh() {
        String recent_tocker = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token : " + recent_tocker);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.FCM_TOKEN), recent_tocker);
        editor.commit();
    }
}
