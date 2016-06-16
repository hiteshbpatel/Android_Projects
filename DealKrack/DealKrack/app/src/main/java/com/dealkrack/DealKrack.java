package com.dealkrack;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class DealKrack extends ListActivity implements FetchDataListener {
    private ProgressDialog dialog;
    ApplicationAdapter adapter;
    AlertDialogManager alert = new AlertDialogManager();
    ConnectionDetector cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parser__deal_krack);
        cd = new ConnectionDetector(getApplicationContext());

        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            alert.showAlertDialog(DealKrack.this,
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }

        initView();

    }

    private void initView() {
        // show progress dialog
        dialog = ProgressDialog.show(this, "", "Loading...");

        String url = "http://tools.vcommission.com/api/coupons.php?apikey=23b96d55da4372eabd8f2f1831d37f29f0434e4397ae0658f26fa813e8b9e381";
        FetchDataTask task = new FetchDataTask(this);
        task.execute(url);
    }


    @Override
    public void onFetchComplete(List<Application> data) {
        // dismiss the progress dialog
        if (dialog != null)
            dialog.dismiss();
        // create new adapter
        adapter= new ApplicationAdapter(this, data);
        // set the adapter to list
        setListAdapter(adapter);

    }

    @Override
    public void onFetchFailure(String msg) {
        // dismiss the progress dialog
        if (dialog != null)
            dialog.dismiss();
        // show failure message
        Toast.makeText(this, "No data Found. Please Check Your Internet Connection.", Toast.LENGTH_LONG).show();
    }
}
