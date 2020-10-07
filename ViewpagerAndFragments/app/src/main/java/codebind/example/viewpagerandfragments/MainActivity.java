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

public class MainActivity extends AppCompatActivity {
    ViewPager vp;
    Toolbar tb;
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
                return ViewUser.newInstance();
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