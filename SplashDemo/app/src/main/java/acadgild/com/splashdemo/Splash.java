package acadgild.com.splashdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Hitesh-PC on 7/6/2016.
 */
public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        new Handler().postDelayed(new Runnable() {

            // Using handler with postDelayed called runnable run method

            @Override
            public void run() {
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, 5*1000); // wait for 5 seconds


    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }
}
