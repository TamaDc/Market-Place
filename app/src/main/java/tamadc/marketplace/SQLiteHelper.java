package tamadc.marketplace;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by Asus A455L on 18/12/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public  void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public  void insertData(String name, String price, byte[] image , String keterangan, String kualitas){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO HANDMADE VALUES (NULL, ?, ?, ?, ?, ? )";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);
        statement.bindString(4, keterangan);
        statement.bindString(5, kualitas);


        statement.executeInsert();
    }

    public void UpdateData(String name, String price, byte[] image , String keterangan, String kualitas, int id){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE HANDMADE SET name = ?, price =?, image = ?, keterangan = ?, kualitas=? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindBlob(3, image);
        statement.bindString(4, keterangan);
        statement.bindString(5, kualitas);
        statement.bindDouble(6, (double)id);


        statement.execute();
        database.close();

    }

    public  void deleteData(int id){
        SQLiteDatabase database =getWritableDatabase();
        String sql = "DELETE FROM HANDMADE WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1,(double)id);

        statement.execute();
        database.close();
    }


    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
