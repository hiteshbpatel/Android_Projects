package in.dailytalent.www.rajasthangk.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import in.dailytalent.www.rajasthangk.R;

/**
 * Created by Shankar Raopura on 12/06/2016.
 */

public class SplashScreen extends Activity {
    private static final long DELAY = 2000;
    private static final int MSG_CONTINUE = 1234;
    private final Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SplashScreen.MSG_CONTINUE /*1234*/:
                    SplashScreen.this._continue();
                    return;
                default:
                    return;
            }
        }
    };

    protected void onCreate(Bundle args) {
        super.onCreate(args);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_splash);
        this.mHandler.sendEmptyMessageDelayed(MSG_CONTINUE, DELAY);
    }

    protected void onDestroy() {
        this.mHandler.removeMessages(MSG_CONTINUE);
        super.onDestroy();
    }

    private void _continue() {
        startActivity(new Intent(this, IndexActivity.class));
        finish();
    }
}