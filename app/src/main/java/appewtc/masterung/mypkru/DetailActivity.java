package appewtc.masterung.mypkru;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class DetailActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String[] loginStrings;
    private String latString, lngString, nameString, imageString;
    private ImageView backImageView, humanImageView;
    private TextView nameHumanTextView, nameLoginTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_detail_layout);

        //Initial View
        initialView();

        //Get Intent
        myGetIntent();

        //Show View
        showView();

        //Controller
        controller();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        aboutFragment();

    }   // Main Method

    private void controller() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showView() {
        nameHumanTextView.setText(nameString);
        nameLoginTextView.setText(loginStrings[1]);
        Picasso.with(this).load(imageString).into(humanImageView);
    }

    private void initialView() {
        backImageView = (ImageView) findViewById(R.id.btnBack);
        humanImageView = (ImageView) findViewById(R.id.imvHumen);
        nameHumanTextView = (TextView) findViewById(R.id.txtNameFriend);
        nameLoginTextView = (TextView) findViewById(R.id.txtNameLogin);
    }

    private void myGetIntent() {
        loginStrings = getIntent().getStringArrayExtra("Login");
        latString = getIntent().getStringExtra("Lat");
        lngString = getIntent().getStringExtra("Lng");
        nameString = getIntent().getStringExtra("Name");
        imageString = getIntent().getStringExtra("Image");
    }

    private void aboutFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng latLng = new LatLng(Double.parseDouble(latString), Double.parseDouble(lngString));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        mMap.addMarker(new MarkerOptions().position(latLng));


    }   // onMapReady

}   // Main Class
