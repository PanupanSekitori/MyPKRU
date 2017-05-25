package appewtc.masterung.mypkru;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private EditText userEditText, passwordEditText;
    private TextView textView;
    private Button button;
    private String userString, passwordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial View
        initialView();

        //Controller
        controller();


    }   // Main Method

    private void controller() {
        textView.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    private void initialView() {
        userEditText = (EditText) findViewById(R.id.edtUser);
        passwordEditText = (EditText) findViewById(R.id.edtPassword);
        textView = (TextView) findViewById(R.id.txtNewRegister);
        button = (Button) findViewById(R.id.btnLogin);
    }

    @Override
    public void onClick(View view) {

        //For TextView
        if (view == textView) {
            //Intent to NewRegister
            Intent intent = new Intent(MainActivity.this, NewRegisterActivity.class);
            startActivity(intent);
        }

        //For Button
        if (view == button) {

            userString = userEditText.getText().toString().trim();
            passwordString = passwordEditText.getText().toString().trim();

            if (userString.equals("") || passwordString.equals("")) {
                //Have Space
                MyAlert myAlert = new MyAlert(this);
                myAlert.myDialog(getResources().getString(R.string.titleHaveSpace),
                        getResources().getString(R.string.messageHaveSpace));
            } else {
                //No Space
                checkUserAnPass();
            }

        }


    }

    private void checkUserAnPass() {

        String urlPHP = "http://swiftcodingthai.com/pkru/GetUserMaster.php";

        try {

            GetAllData getAllData = new GetAllData(this);
            getAllData.execute(urlPHP);

            String strJSON = getAllData.get();
            Log.d("25MayV1", "JSON ==> " + strJSON);

        } catch (Exception e) {
            Log.d("25MayV1", "e checkUser ==> " + e.toString());
        }



    }
}   // Main Class นี่คือ คลาสหลัก
