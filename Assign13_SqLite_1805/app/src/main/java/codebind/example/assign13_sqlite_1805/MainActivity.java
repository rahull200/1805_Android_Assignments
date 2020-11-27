package codebind.example.assign13_sqlite_1805;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    ViewPager vp;
    Toolbar tb;
    String name,num,em,pass1,pass2,gender,birthday,district;
    DBClient dbClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb = (Toolbar)findViewById(R.id.action);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vp = (ViewPager)findViewById(R.id.vp);
        FragmentManager fm = getSupportFragmentManager();
        FragmentPagerAdapter fpa = new MyPagerAdapter(fm);
        vp.setAdapter(fpa);

        dbClient = new DBClient(this);
        dbClient.open();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_tick:
                insertData();
                break;
            case R.id.action_view:
                ViewUsers();
        }
        return super.onOptionsItemSelected(item);
    }

    private void ViewUsers() {
        Cursor data = dbClient.getUsers();
        if(data.getCount()>0){
            StringBuffer buffer = new StringBuffer();
            while (data.moveToNext()) {
                buffer.append("ID :"+ data.getString(0)+"\n");
                buffer.append("Name :"+ data.getString(1)+"\n");
                buffer.append("Number :"+ data.getString(2)+"\n");
                buffer.append("Email :"+ data.getString(3)+"\n");
                buffer.append("Password :"+ data.getString(4)+"\n\n");
            }
            // Show all data
            showMessage("User Data",buffer.toString());
        }else{
            Toast.makeText(this,"No data present",Toast.LENGTH_SHORT).show();
        }

    }

    private void insertData() {
        TextView nametxt = (TextView)findViewById(R.id.name);
        TextView numtxt = (TextView)findViewById(R.id.num);
        TextView emailtxt = (TextView)findViewById(R.id.email);
        TextView passtxt = (TextView)findViewById(R.id.pass1);
        TextView confirmpasstxt = (TextView)findViewById(R.id.pass2);
        String name = nametxt.getText().toString();
        String num = numtxt.getText().toString();
        String email = emailtxt.getText().toString();
        String pass1 = passtxt.getText().toString();
        String pass2 = confirmpasstxt.getText().toString();
        if(!name.isEmpty()&&!num.isEmpty()&&!email.isEmpty()&&!pass1.isEmpty()&&!pass2.isEmpty()){
            if(pass1.equals(pass2)){
                dbClient.addUser(name,num,email,pass1);
                Toast.makeText(getApplicationContext(),"Data added successfully",Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(getApplicationContext(),"Passwords from both the fields do not match",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Please fill all the fields",Toast.LENGTH_LONG).show();
        }

    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return Register.newInstance();
            }
            else if(position==1){
                return ViewUser.newInstance(name,gender,birthday,district);
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0){
                return "Register";
            }
            else if(position==1){
                return "View";
            }
            return null;
        }
    }

    //Display Data
    private void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}