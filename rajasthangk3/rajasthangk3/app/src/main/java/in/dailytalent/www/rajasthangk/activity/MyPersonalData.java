package in.dailytalent.www.rajasthangk.activity;

import android.content.Context;

/**
 * Created by Shankar Raopura on 12/06/2016.
 */

public class MyPersonalData {
    private static final String DB_NAME = "rajasthangk01012016.sqlite";
    static Context context;

    public static String dbname() {
        return DB_NAME;
    }
}
