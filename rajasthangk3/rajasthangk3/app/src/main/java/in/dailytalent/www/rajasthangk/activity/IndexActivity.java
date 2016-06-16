package in.dailytalent.www.rajasthangk.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import in.dailytalent.www.rajasthangk.R;

public class IndexActivity extends Activity {
    private static final long delay = 2000;
    private Handler mExitHandler = new Handler();
    private Runnable mExitRunnable = new Runnable() {
        public void run() {
            IndexActivity.this.mRecentlyBackPressed = false;
        }
    };
    private boolean mRecentlyBackPressed = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homa);
    }
    private void gohome() {
        startActivity(new Intent(this, IndexActivity.class));
    }

    private void goabout() {
        startActivity(new Intent(this, AboutActivity.class));
    }

    public void goabouta(View v) {
        startActivity(new Intent(this, AboutActivity.class));
    }

  public void sharenow(View view) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
        intent.putExtra("android.intent.extra.SUBJECT", "Rajasthan GK");
        intent.putExtra("android.intent.extra.TEXT", "Very useful app for Rajasthan. You should try it \n https://play.google.com/store/apps/details?id=in.dailytalent.www.examsuccess");
        startActivity(Intent.createChooser(intent, "How do you want to share?"));
    }

    public void gocat(View v) {

        startActivity(new Intent(this, CategoryActivity.class));
    }

    public void goapps(View v) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id=SHANKARRAOPURA"));
        startActivity(intent);
    }

    public void gofb(View v) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://www.facebook.com/kushi.yadav.9619"));
        startActivity(intent);
    }

   private void goappsa() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id=SHANKARRAOPURA"));
        startActivity(intent);
    }

    public void sendmail(View v) {
        Intent i = new Intent("android.intent.action.SEND");
        i.setType("message/rfc822");
        i.putExtra("android.intent.extra.EMAIL", new String[]{"imrankhanonnet@gmail.com"});
        i.putExtra("android.intent.extra.SUBJECT", "Feedback from Rajasthan GK");
        i.putExtra("android.intent.extra.TEXT", "Please write your feedback here.......");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendmaila() {
        Intent i = new Intent("android.intent.action.SEND");
        i.setType("message/rfc822");
        i.putExtra("android.intent.extra.EMAIL", new String[]{"imrankhanonnet@gmail.com"});
        i.putExtra("android.intent.extra.SUBJECT", "Feedback from Rajasthan GK");
        i.putExtra("android.intent.extra.TEXT", "Please write your feedback here.......");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                gohome();
                return true;
            case R.id.about:
                goabout();
                return true;
            case R.id.more:
                goappsa();
                return true;
            case R.id.contact:
                sendmaila();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
        if (this.mRecentlyBackPressed) {
            this.mExitHandler.removeCallbacks(this.mExitRunnable);
            this.mExitHandler = null;
            super.onBackPressed();
            return;
        }
        this.mRecentlyBackPressed = true;
        Toast.makeText(this, "Press again to exit !", Toast.LENGTH_SHORT).show();
        this.mExitHandler.postDelayed(this.mExitRunnable, delay);
    }
}
