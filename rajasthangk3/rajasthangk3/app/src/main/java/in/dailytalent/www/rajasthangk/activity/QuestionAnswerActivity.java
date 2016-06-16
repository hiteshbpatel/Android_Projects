package in.dailytalent.www.rajasthangk.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import in.dailytalent.www.rajasthangk.R;
import in.dailytalent.www.rajasthangk.dbhelper.ExternalDbOpenHelper;

public class QuestionAnswerActivity extends Activity {
    private static final String DB_NAME = MyPersonalData.dbname();
    String answer;
    String catname;
    private SQLiteDatabase database;
    int pa;
    int qucount;
    String quid;
    int qunumber;
    int subcatnumber;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quans);
        Bundle extras = getIntent().getExtras();
        this.subcatnumber = extras.getInt("subcatnumber");
        this.qunumber = extras.getInt("qunumber");
        this.pa = extras.getInt("pa");
        TextView tv = (TextView) findViewById(R.id.catname);
        TextView tv2 = (TextView) findViewById(R.id.questionno);
        TextView tv3 = (TextView) findViewById(R.id.answer);
        Button b12 = (Button) findViewById(R.id.buttonhome);
        this.database = new ExternalDbOpenHelper(this, DB_NAME).openDataBase();
        Cursor chapCursor = this.database.rawQuery("SELECT subcategory.subcatname AS catname, questions._id AS quid, questions.qu AS quest, questions.ans  AS answer FROM questions INNER JOIN subcategory ON subcategory._id=questions.subcatid WHERE  questions._id=" + this.qunumber, null);
        chapCursor.moveToFirst();
        this.catname = chapCursor.getString(0);
        this.quid = chapCursor.getString(1) + ". " + chapCursor.getString(2);
        this.answer = chapCursor.getString(3);
        chapCursor.close();
        b12.setText(this.catname);
        tv2.setText(this.quid);
        tv3.setText(this.answer);
        Cursor countNotes = this.database.rawQuery("SELECT COUNT(*) AS qucount FROM questions WHERE questions.subcatid= " + this.subcatnumber, null);
        countNotes.moveToFirst();
        this.qucount = countNotes.getInt(0);
        countNotes.close();
        ImageButton b1 = (ImageButton) findViewById(R.id.bt1);
        if (this.pa < 1) {
            b1.setVisibility(View.VISIBLE);
        }
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent in = new Intent(QuestionAnswerActivity.this.getBaseContext(), QuestionAnswerActivity.class);
                in.putExtra("qunumber", QuestionAnswerActivity.this.qunumber - 1);
                in.putExtra("subcatnumber", QuestionAnswerActivity.this.subcatnumber);
                in.putExtra("pa", QuestionAnswerActivity.this.pa - 1);
                QuestionAnswerActivity.this.startActivity(in);
            }
        });
        ((ImageButton) findViewById(R.id.bt2)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.SUBJECT", "Rajasthan GK App ");
                intent.putExtra("android.intent.extra.TEXT", QuestionAnswerActivity.this.catname + " :\n" + QuestionAnswerActivity.this.quid + "\n" + QuestionAnswerActivity.this.answer + "\nFrom Rajasthan GK App. \n https://play.google.com/store/apps/developer?id=SHANKARRAOPURA");
                QuestionAnswerActivity.this.startActivity(Intent.createChooser(intent, "Share with...."));
            }
        });
        ImageButton b3 = (ImageButton) findViewById(R.id.bt3);
        if (this.pa >= this.qucount - 1) {
            b3.setVisibility(View.VISIBLE);
        }
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent in = new Intent(QuestionAnswerActivity.this.getBaseContext(), QuestionAnswerActivity.class);
                in.putExtra("qunumber", QuestionAnswerActivity.this.qunumber + 1);
                in.putExtra("subcatnumber", QuestionAnswerActivity.this.subcatnumber);
                in.putExtra("pa", QuestionAnswerActivity.this.pa + 1);
                QuestionAnswerActivity.this.startActivity(in);
            }
        });
    }

    @SuppressLint({"InlinedApi"})
    public void gohomea(View v) {
        Intent intent = new Intent(this, QuestionlistActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("subcatnumber", this.subcatnumber);
        startActivity(intent);
    }

    @SuppressLint({"InlinedApi"})
    public void gohome(View v) {
        Intent intent = new Intent(this, QuestionlistActivity.class);
        intent.putExtra("subcatnumber", this.subcatnumber);
        startActivity(intent);
    }

}