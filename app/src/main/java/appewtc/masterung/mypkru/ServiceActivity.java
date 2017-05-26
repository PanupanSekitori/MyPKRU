package appewtc.masterung.mypkru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    private TextView textView;
    private ListView listView;
    private String[] loginStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //Initial View
        initialView();

        //Show Name
        showName();

        //Create ListView
        createListView();


    }   // Main Method

    private void showName() {

        //Get Value From Intent
        loginStrings = getIntent().getStringArrayExtra("Login");

        textView.setText(loginStrings[1]);

    }

    private void createListView() {

    }

    private void initialView() {
        textView = (TextView) findViewById(R.id.txtNameLogin);
        listView = (ListView) findViewById(R.id.livFriend);
    }

}   // Main Class
