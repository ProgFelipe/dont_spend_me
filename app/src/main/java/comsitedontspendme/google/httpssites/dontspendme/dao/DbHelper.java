package comsitedontspendme.google.httpssites.dontspendme.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Felipe on 25/07/2015.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "spends.sqlite";
    public static final int SCHEME_VERSION = 1;

    public DbHelper(Context context){
        super(context, DB_NAME, null ,SCHEME_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbManager.CREATE_TABLE_SPENDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
