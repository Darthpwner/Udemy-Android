package matthewallenlinsoftware.locationdemomatt;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

//Allows our class to use all the methods in LocationListener
public class MainActivity extends Activity implements LocationListener {

    LocationManager locationManager;    //There is something similar in iOS and lets us access info
    String provider;    //Stores the name of location provider

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Use GPS or Wi-Fi locations (GPS is preferred since it is more accurate)
        provider = locationManager.getBestProvider(new Criteria(), false);   //Default Criteria is fine
        //We generally check ourselves if the provider is available

        //Add permission to the Location
        Location location = locationManager.getLastKnownLocation(provider);

        if(location != null) {
            Log.i("Location Info", "Location achieved!");
        } else {
            Log.i("Location Info", "No location :(");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Happens when the app is put into the background and is reactivated
    @Override
    protected void onResume() {
        super.onResume();

        //provider is a String
        //400 milliseconds for minTime (commonly used)
        //number of meters between updates
        //App context (this)
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        locationManager.removeUpdates(this);
    }

    //Main one we will use i.e. tracking a user's location on a map
    @Override
    public void onLocationChanged(Location location) {
        Double lat = location.getLatitude();    //Gets the user's latitude on the map
        Double lng = location.getLongitude();   //Gets the user's longitude on the map

        Log.i("Latitude", lat.toString());
        Log.i("Longitude", lng.toString());
    }

    //Called when the user's location becomes available after it hasn't been for a while
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    //Use to know when the GPS location is available
    @Override
    public void onProviderEnabled(String provider) {

    }

    //Use to know when the GPS location is UNavailable
    @Override
    public void onProviderDisabled(String provider) {

    }
}
