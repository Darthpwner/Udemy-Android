package matthewallenlinsoftware.downloadingimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity {

    ImageView downloadImg;

    public void downloadImage(View view) {
        //https://www.google.com/imgres?imgurl=https://upload.wikimedia.org/wikipedia/commons/b/b9/Steve_Jobs_Headshot_2010-CROP.jpg&imgrefurl=https://en.wikipedia.org/wiki/Steve_Jobs&h=3194&w=3259&tbnid=5CFMSgvDaxjJsM:&docid=cvmgKjU7_-0ZBM&ei=UgWBVoyZK8PKjwPIhbC4Bw&tbm=isch&client=safari&ved=0ahUKEwiMiuWbo_7JAhVD5WMKHcgCDHcQMwgxKAAwAA

        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;

        try {
            myImage = task.execute("https://www.google.com/imgres?imgurl=https://upload.wikimedia.org/wikipedia/commons/b/b9/Steve_Jobs_Headshot_2010-CROP.jpg&imgrefurl=https://en.wikipedia.org/wiki/Steve_Jobs&h=3194&w=3259&tbnid=5CFMSgvDaxjJsM:&docid=cvmgKjU7_-0ZBM&ei=UgWBVoyZK8PKjwPIhbC4Bw&tbm=isch&client=safari&ved=0ahUKEwiMiuWbo_7JAhVD5WMKHcgCDHcQMwgxKAAwAA").get();

            downloadImg.setImageBitmap(myImage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.i("Interaction", "Button Tapped");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadImg = (ImageView) findViewById(R.id.imageView);

    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]); //Creating it from the url we pass

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream inputStream = connection.getInputStream();

                Bitmap myBitMap = BitmapFactory.decodeStream(inputStream);

                return myBitMap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;    //In case of an error
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
}
