package matthewallenlinsoftware.timerdemo;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //Timer code
//        final Handler handler = new Handler();    //Allows chunks of code to be put on in a delayed way
//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//                //Insert code ot be run every second
//                Log.i("Runnable has run!", "a second must have passed");
//
//                handler.postDelayed(this, 1000);
//            }
//        };
//
//        handler.post(run);

        //Countdown Timer
        new CountDownTimer(10000, 1000) {
            public void onTick(long millisecondsUntilDone) {    //This method will give us a variable to use
                //Countdown is counting down (every second)
                Log.i("Seconds left", String.valueOf(millisecondsUntilDone / 1000));    //convert long -> string
            }

            public void onFinish() {
                //Counter is finished! (after 10 seconds)
                Log.i("Done!", "Countdown Timer Finished");
                //Countdown object is destroyed when onFinish is called
            }
        }.start();
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
}
