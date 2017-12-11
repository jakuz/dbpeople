package pl.lawendowadolina.dbpeople;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kamil on 2017-12-03.
 */

public class DBHelper extends SQLiteOpenHelper {


    public static String TABLE_NAME = "adresy";
    public static String COL_ID = "_id";
    public static String COL_NAME = "imie";
    public static String COL_SURNAME = "nazwisko";
    public static String COL_CITY = "miasto";

    private static String DB_NAME = "People.db";
    private static int DB_VERSION = 1;

    private static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COL_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, " + COL_SURNAME + " TEXT, " +
            COL_CITY + " TEXT" + ")";



//    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, DB_NAME, factory, DB_VERSION);
//    }

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
