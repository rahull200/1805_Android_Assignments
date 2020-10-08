package codebind.example.viewpagerandfragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    ViewPager vp;
    Toolbar tb;
    String name,num,em,pass1,pass2,gender,birthday,district;
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

        Profile profile = new Profile();
        name = profile.getName();
        em = profile.getEmail();
        num = profile.getNumber();
        pass1 = profile.getPass1();
        pass2 = profile.getPass2();
        gender = profile.getGender();
        birthday = profile.getBirthday();
        district = profile.getDistrict();

        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_tick){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return Register.newInstance(name,num,em,pass1,pass2);
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

}