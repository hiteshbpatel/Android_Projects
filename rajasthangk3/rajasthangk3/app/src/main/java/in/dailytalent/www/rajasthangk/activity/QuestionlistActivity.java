package in.dailytalent.www.rajasthangk.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import in.dailytalent.www.rajasthangk.R;
import in.dailytalent.www.rajasthangk.adapter.QuCursorAdapterNew;
import in.dailytalent.www.rajasthangk.dbhelper.ExternalDbOpenHelper;

/**
 * Created by Shankar Raopura on 12/06/2016.
 */

public class QuestionlistActivity extends Activity {
    private static final String DB_NAME = MyPersonalData.dbname();
    private SQLiteDatabase database;
    String fr;
    int maincatnumber;
    int subcatnumber;
    String title;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
        this.database = new ExternalDbOpenHelper(this, DB_NAME).openDataBase();
        Bundle extras = getIntent().getExtras();
        this.maincatnumber = extras.getInt("maincatnumber");
        this.subcatnumber = extras.getInt("subcatnumber");
        Cursor chapCursor = this.database.rawQuery("SELECT *, subcategory.subcatname AS catname FROM  subcategory  WHERE subcategory._id=" + this.subcatnumber + " LIMIT 1", null);
        chapCursor.moveToFirst();
        String catname = chapCursor.getString(1);
        chapCursor.close();
        ((Button) findViewById(R.id.buttonhome)).setText(catname);
        ListView listView = (ListView) findViewById(R.id.categorylist);
        listView.setAdapter(new QuCursorAdapterNew(this, R.layout.onequ, this.database.rawQuery("SELECT  *, questions._id AS quid ,questions.qu AS quname FROM questions INNER JOIN subcategory ON (subcategory._id=questions.subcatid) WHERE subcategory._id= " + this.subcatnumber, null), 0));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent in = new Intent(QuestionlistActivity.this, QuestionAnswerActivity.class);
                int pa = position;
                Cursor catidfinder = QuestionlistActivity.this.database.rawQuery("select questions._id  from questions WHERE questions.subcatid=" + QuestionlistActivity.this.subcatnumber + "  LIMIT 1 OFFSET " + pa, null);
                catidfinder.moveToFirst();
                int qunumber = catidfinder.getInt(0);
                catidfinder.close();
                in.putExtra("subcatnumber", QuestionlistActivity.this.subcatnumber);
                in.putExtra("qunumber", qunumber);
                in.putExtra("pa", pa);
                Log.i(getClass().toString(), "Trying to add intent...............");
                QuestionlistActivity.this.startActivity(in);
            }
        });
    }

    public void sharenow(View v) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
        intent.putExtra("android.intent.extra.SUBJECT", "Rajasthan GK App");
        intent.putExtra("android.intent.extra.TEXT", this.title + "\n" + this.fr + "\n Find at - \n https://play.google.com/store/apps/developer?id=SHANKARRAOPURA");
        startActivity(Intent.createChooser(intent, "How do you want to share?"));
    }

    @SuppressLint({"InlinedApi"})
    public void gohome(View v) {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
