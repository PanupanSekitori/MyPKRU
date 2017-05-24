package appewtc.masterung.mypkru;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class NewRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private EditText nameEditText, userEditText, passwordEditText;
    private ImageView backImageView, humanImageView, cameraImageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_register);

        //Initial View
        initialView();

        //Controller
        controller();

    }   // Main Method

    private void controller() {
        backImageView.setOnClickListener(this);
        humanImageView.setOnClickListener(this);
        cameraImageView.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    private void initialView() {

        nameEditText = (EditText) findViewById(R.id.edtName);
        userEditText = (EditText) findViewById(R.id.edtUser);
        passwordEditText = (EditText) findViewById(R.id.edtPassword);
        backImageView = (ImageView) findViewById(R.id.btnBack);
        humanImageView = (ImageView) findViewById(R.id.imvHumen);
        cameraImageView = (ImageView) findViewById(R.id.imvCamera);
        button = (Button) findViewById(R.id.btnRegister);

    }

    @Override
    public void onClick(View view) {

        //For Back
        if (view == backImageView) {
            finish();
        }

    }
}   // Main Class
