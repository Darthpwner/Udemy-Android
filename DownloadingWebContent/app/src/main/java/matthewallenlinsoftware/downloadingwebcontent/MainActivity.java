package matthewallenlinsoftware.downloadingwebcontent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity {


    /*Class is a collection of methods and variables
    This class is used just like a method for downloading web content
    AsyncTask is a way of running your thread on a different one
    Generally good idea to run code that takes a while to run on different threads from the main thread
    */

    //We might have a separate method on a separate progress bar
    //3rd variable type will be returned as a String
    public class DownloadTask extends AsyncTask<String, Void, String> {
        /*protected can be accessed anywhere in the package, the backup thread
        can be accessed anywhere in the app and the last bit of strangeness is
        the ...
        Analogous to an array
         */
        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;    //Needs to be in the right format to use it as a URL
            HttpURLConnection urlConnection = null; //Similar to a browser and use it to fetch contents of URL

            try {
                url = new URL(urls[0]); //Take the first URL, if it was malformed

                urlConnection = (HttpURLConnection) url.openConnection();   //Type case

                InputStream in = urlConnection.getInputStream();    //Stream to hold input of data as it comes in

                InputStreamReader reader = new InputStreamReader(in);

                //Build up one character at a time
                int data = reader.read();

                //data counts through and it should terminate when it gets -1
                while(data != -1) {
                    char current = (char) data; //Gets the current character being downloaded

                    result += current;  //Continually building characters from the reader

                    data = reader.read();
                }

                return result;
            } catch(Exception e) {
                e.printStackTrace();

                return "Failed";
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask(); //Initialize the instance
        String result = null;

        try {
            result = task.execute("https://www.facebook.com").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch(ExecutionException e) {
            e.printStackTrace();
        }

        Log.i("Contents of URL: ", result);
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
