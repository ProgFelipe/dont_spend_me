package comsitedontspendme.google.httpssites.dontspendme.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Felipe on 25/07/2015.
 */
public class DbManager {
    public static final String TABLE_SPENDS = "spends";
    public static final String TABLE_CATEGORIES = "category";

    // TABLE_SPENDS COLUMNS
    public static final String CN_SPEND_ID = "_id";
    public static final String CN_SPEND_VALUE = "value";
    public static final String CN_SPEND_DATE = "date";
    public static final String CN_SPEND_CATEGORY = "category";


    // SQLITE SQL SENTENCE TABLE_SPENDS
    public static final String CREATE_TABLE_SPENDS = " create table "+TABLE_SPENDS+" ("
            +CN_SPEND_ID+" integer primary key not null,"
            +CN_SPEND_VALUE+" integer not null,"
            +CN_SPEND_DATE+" integer not null,"
            +CN_SPEND_CATEGORY+" integer );";

    // TABLE_CATEGORIES COLUMNS
    public static final String CN_CATEGORY_ID = "_id";
    public static final String CN_CATEGORY_NAME = "name";
    public static final String CN_CATEGORY_DESCRIPTION = "expend";

    // SQLITE SQL SENTENCE TABLE_CATEGORIES
    public static final String CREATE_TABLE_CATEGORIES = " create table "+TABLE_CATEGORIES+" ("
            +CN_CATEGORY_ID+" integer primary key not null,"
            +CN_CATEGORY_NAME +" text not null,"
            +CN_CATEGORY_DESCRIPTION +" text );";

    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public DbManager(Context context){
                /*
        Data base creation and instance
         */
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ContentValues generateSpend(int id, Double value, String date, int categoty){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CN_SPEND_ID, id);
        contentValues.put(CN_SPEND_VALUE, value);
        contentValues.put(CN_SPEND_DATE, date);
        contentValues.put(CN_SPEND_CATEGORY, categoty);
        return  contentValues;
    }

    public ContentValues generateCategory(int id, String name, String description){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CN_SPEND_ID, id);
        contentValues.put(CN_SPEND_VALUE, name);
        contentValues.put(CN_SPEND_DATE, description);
        return  contentValues;
    }
    /*
        *  CRUD OPERATIONS
    */
    /*
    * INSERTS V CREATE
     */
    public void insertSpend(int id, Double value, String date, int category_id){
        // insert (String table, String nullColumnHack, ContentValues values)
        db.insert(TABLE_SPENDS, null, generateSpend(id, value, date, category_id));
    }
    public void insertSpends(int [] ids, double [] expended, String [] date, int [] id_category){
        for(int i = 0; i < ids.length; i++){
            db.insert(TABLE_SPENDS, null, generateSpend(ids[i], expended[i], date[i], id_category[i]));
        }
    }
    /*
    * Updates
    */
    public void updateSpend(int id, Double value, String date, int category_id){
        //For more than one value "IN (?, ?)"
        db.update(TABLE_SPENDS,generateSpend(id, value, date, category_id),CN_SPEND_ID+" =? ", new String[]{Integer.toString(id).trim()});
    }
    public void updateCategory(int id, String category_name, String desciption){
        //For more than one value "IN (?, ?)"
        db.update(TABLE_CATEGORIES,generateCategory(id, category_name, desciption),CN_CATEGORY_ID+" =? ", new String[]{Integer.toString(id).trim()});
    }
    /*
    * DELETS
    */
    public void deletSpend(String id){
        db.delete(TABLE_SPENDS, CN_SPEND_ID + " =? ", new String[]{id});
    }
    public void deletCategory(String id){
        db.delete(TABLE_CATEGORIES,CN_CATEGORY_ID+" =? ", new String[]{id});
    }
    /*
    * GET DATA
     */
    public Cursor getSpends(){
        String [] columns = new String [] {CN_SPEND_ID, CN_SPEND_VALUE, CN_SPEND_DATE };
        return db.query(TABLE_SPENDS, columns, null, null, null, null, null);
    }
    public Cursor getCategories(){
        String [] columns = new String[]{CN_CATEGORY_ID, CN_CATEGORY_NAME, CN_CATEGORY_DESCRIPTION};
        return db.query(TABLE_CATEGORIES, columns, null, null, null, null, null);
    }
}
