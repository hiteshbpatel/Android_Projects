package in.dailytalent.www.rajasthangk.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.View;

import in.dailytalent.www.rajasthangk.R;


public class CategoryActivity extends Activity {
    private static final String DB_NMAE = MyPersonalData.dbname();
    SQLiteDatabase database;
    String fr;
    int subcatnumber;
    String title;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

       /* Bundle extras = getIntent().getExtras();
        ListView listView = (ListView) findViewById(R.id.categorylist);
        listView.setAdapter(new CatCursorAdapterNew(this, R.layout.onecat, this.database.rawQuery("SELECT *,subcategory._id AS catid, subcategory.subcatname AS catnamea, COUNT(questions._id) AS qucount   FROM subcategory INNER JOIN  questions  ON(subcategory._id=questions.subcatid) group by subcategory._id", null), 0));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent in = new Intent(CategoryActivity.this, QuestionlistActivity.class);
                Cursor catidfinder = CategoryActivity.this.database.rawQuery("select subcategory._id  from subcategory  LIMIT 1 OFFSET " + position, null);
                catidfinder.moveToFirst();
                CategoryActivity.this.subcatnumber = catidfinder.getInt(0);
                catidfinder.close();
                in.putExtra("subcatnumber", CategoryActivity.this.subcatnumber);
                Log.i(getClass().toString(), "Trying to add intent...............");
                CategoryActivity.this.startActivity(in);
            }
        }); */
    }

    public void sharenow(View v) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
        intent.putExtra("android.intent.extra.SUBJECT", "Rajasthan GK App");
        intent.putExtra("android.intent.extra.TEXT", this.title + "\n" + this.fr + "\n Find at - \n https://play.google.com/store/apps/developer?id=SHANKARRAOPURA");
        startActivity(Intent.createChooser(intent, "How do you want to share?"));
    }

    public void gohome(View v) {
        Intent intent = new Intent(this, IndexActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
