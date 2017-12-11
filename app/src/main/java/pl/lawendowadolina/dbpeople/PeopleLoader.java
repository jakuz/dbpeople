package pl.lawendowadolina.dbpeople;

//import android.content.Context;
//import android.content.CursorLoader;
//import android.database.Cursor;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

/**
 * Created by Kamil on 2017-12-07.
 */

public class PeopleLoader extends CursorLoader {

    DBPeopleOperations dbase;

    public PeopleLoader(Context context, DBPeopleOperations b){
        super(context);
        dbase = b;
    }

    @Override
    public Cursor loadInBackground() {
        return dbase.getPersons();
    }
}
