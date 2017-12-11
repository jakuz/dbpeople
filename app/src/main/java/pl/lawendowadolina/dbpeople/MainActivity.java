package pl.lawendowadolina.dbpeople;

//import android.content.Loader;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
//import android.app.LoaderManager.LoaderCallbacks;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {


    private DBPeopleOperations dbPeopleOperations;
    private SimpleCursorAdapter scAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonMainAdd).setOnClickListener(this);
        dbPeopleOperations = new DBPeopleOperations(this);
        dbPeopleOperations.open();
        scAdapter = new SimpleCursorAdapter(this, R.layout.list_item, null,
                new String[]{DBHelper.COL_NAME, DBHelper.COL_SURNAME, DBHelper.COL_CITY},
                new int[]{R.id.textListName, R.id.textListSurname, R.id.textListCity}, 0);
        ((ListView)findViewById(R.id.listMain)).setAdapter(scAdapter);
        getSupportLoaderManager().initLoader(1, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new PeopleLoader(this, dbPeopleOperations);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        scAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        scAdapter.swapCursor(null);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonMainAdd:
                dbPeopleOperations.addPerson(
                        ((EditText)findViewById(R.id.editMainName)).getText().toString(),
                        ((EditText)findViewById(R.id.editMainSurname)).getText().toString(),
                        ((EditText)findViewById(R.id.editCity)).getText().toString()
                );
                getSupportLoaderManager().getLoader(1).forceLoad();
                break;
        }

    }
}
