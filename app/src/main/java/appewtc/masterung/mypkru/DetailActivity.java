package appewtc.masterung.mypkru;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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



        //Get Intent
        myGetIntent();



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        aboutFragment();

    }   // Main Method

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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }   // onMapReady

}   // Main Class
