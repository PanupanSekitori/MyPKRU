package appewtc.masterung.mypkru;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceActivity extends AppCompatActivity {

    private TextView textView;
    private ListView listView;
    private String[] loginStrings;
    private LocationManager locationManager;
    private Criteria criteria;
    private double latADouble = 7.909936, lngADouble = 98.388240;
    private String urlString = "http://swiftcodingthai.com/pkru/editLatLngMaster.php";


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

    @Override
    protected void onResume() {
        super.onResume();

        Location networkLocation = myFindLocation(LocationManager.NETWORK_PROVIDER);
        if (networkLocation != null) {
            latADouble = networkLocation.getLatitude();
            lngADouble = networkLocation.getLongitude();
        }

        Location gpsLocation = myFindLocation(LocationManager.GPS_PROVIDER);
        if (gpsLocation != null) {
            latADouble = gpsLocation.getLatitude();
            lngADouble = gpsLocation.getLongitude();
        }

        Log.d("26MayV2", "Lat ==> " + latADouble);
        Log.d("26MayV2", "Lng ==> " + lngADouble);

        try {

            EditLatLng editLatLng = new EditLatLng(this);
            editLatLng.execute(loginStrings[0],
                    Double.toString(latADouble),
                    Double.toString(lngADouble),
                    urlString);
            if (Boolean.parseBoolean(editLatLng.get())) {
                Toast.makeText(ServiceActivity.this, "Update Location Finish",
                        Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            Log.d("26MayV2", "e resume ==> " + e.toString());
        }


    }

    @Override
    protected void onStop() {
        super.onStop();

        locationManager.removeUpdates(locationListener);

    }

    public Location myFindLocation(String strProvider) {

        Location location = null;

        if (locationManager.isProviderEnabled(strProvider)) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            locationManager.requestLocationUpdates(strProvider, 1000, 10, locationListener);

        }

        return location;
    }


    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latADouble = location.getLatitude();
            lngADouble = location.getLongitude();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };


    private void showName() {

        //Get Value From Intent
        loginStrings = getIntent().getStringArrayExtra("Login");

        textView.setText(loginStrings[1]);

    }

    private void createListView() {

        try {

            String urlJSON = "http://swiftcodingthai.com/pkru/GetUserMaster.php";
            GetAllData getAllData = new GetAllData(this);
            getAllData.execute(urlJSON);

            String strJSON = getAllData.get();
            Log.d("26MayV1", "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            final String[] nameStrings = new String[jsonArray.length()];
            final String[] imageStrings = new String[jsonArray.length()];
            final String[] latStrings = new String[jsonArray.length()];
            final String[] lngStrings = new String[jsonArray.length()];

            for (int i=0;i<jsonArray.length();i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStrings[i] = jsonObject.getString("Name");
                imageStrings[i] = jsonObject.getString("Image");
                latStrings[i] = jsonObject.getString("Lat");
                lngStrings[i] = jsonObject.getString("Lng");
                Log.d("26MayV1", "Result (" + i + ") ==> " + nameStrings[i] + " : " + imageStrings[i]);

            }   // for

            FriendAdapter friendAdapter = new FriendAdapter(this, nameStrings, imageStrings);
            listView.setAdapter(friendAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent intent = new Intent(ServiceActivity.this, DetailActivity.class);
                    intent.putExtra("Login", loginStrings);
                    intent.putExtra("Lat", latStrings[i]);
                    intent.putExtra("Lng", lngStrings[i]);
                    intent.putExtra("Name", nameStrings[i]);
                    intent.putExtra("Image", imageStrings[i]);
                    startActivity(intent);


                }
            });




        } catch (Exception e) {
            Log.d("26MayV1", "e createListView ==> " + e.toString());
        }

    }

    private void initialView() {
        textView = (TextView) findViewById(R.id.txtNameLogin);
        listView = (ListView) findViewById(R.id.livFriend);

        //Setup
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);

    }

}   // Main Class
