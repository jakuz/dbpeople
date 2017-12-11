package pl.lawendowadolina.dbpeople;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Kamil on 2017-12-05.
 */

public class DBPeopleOperations {

    private DBHelper dbHelper;
    private String PEOPLE_COLUMNS[] = {DBHelper.COL_ID, DBHelper.COL_NAME, DBHelper.COL_SURNAME, DBHelper.COL_CITY};
    private SQLiteDatabase dbPeople;

    public DBPeopleOperations (Context context){
        dbHelper = new DBHelper(context);
    }


    public void open() throws SQLException{
        dbPeople = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbPeople.close();
    }

    public void addPerson(String name, String surname, String city){
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COL_NAME, name);
        cv.put(DBHelper.COL_SURNAME, surname);
        cv.put(DBHelper.COL_CITY, city);
        dbPeople.insert(DBHelper.TABLE_NAME, null, cv);
    }

    public Cursor getPersons(){
        return dbPeople.query(dbHelper.TABLE_NAME, PEOPLE_COLUMNS, null, null, null, null, DBHelper.COL_SURNAME);
    }

    public void deletePerson(int id){
        dbPeople.delete(DBHelper.TABLE_NAME, dbHelper.COL_ID + " = " + id, null);
    }

}
