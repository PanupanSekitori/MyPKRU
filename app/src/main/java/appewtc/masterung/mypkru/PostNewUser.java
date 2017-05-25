package appewtc.masterung.mypkru;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;

/**
 * Created by masterUNG on 5/25/2017 AD.
 */

public class PostNewUser extends AsyncTask<String, Void, String>{

    private Context context;

    public PostNewUser(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("Name", strings[0])
                    .add("User", strings[1])
                    .add("Password", strings[2])
                    .add("Image", strings[3])
                    .build();

        } catch (Exception e) {
            Log.d("24MayV1", "e doIn ==> " + e.toString());
        }

        return null;
    }
}   // Main Class
