package codebind.example.assign13_sqlite_1805;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBClient {
    UserDBHelper userDBHelper;
    Context context;
    SQLiteDatabase database;

    public DBClient(Context context) {
        this.context = context;
    }

    //open Database
    public void open(){
        userDBHelper = new UserDBHelper(context);
        database = userDBHelper.getWritableDatabase();
    }

    //close Database
    public void close(){
        userDBHelper.close();
    }

    //Add user
    public void addUser(String name,String num,String email,String pass,String gender,String district,String dob){
        ContentValues params = new ContentValues();
        params.put("name",name);
        params.put("num",num);
        params.put("email",email);
        params.put("pass",pass);
        params.put("gender",gender);
        params.put("district",district);
        params.put("dob",dob);
        database.insert("users",null,params);
    }

    public Cursor getUsers() {
        Cursor res = database.rawQuery("select * from users",null);
        return res;
    }
}
