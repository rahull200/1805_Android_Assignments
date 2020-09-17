package codebind.example.inflator_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout views;
    LayoutInflater l;
    ScrollView scrollView;
    Button red,yellow,blue,green;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        red = (Button)findViewById(R.id.red);
        yellow = (Button)findViewById(R.id.yellow);
        green = (Button)findViewById(R.id.green);
        blue = (Button)findViewById(R.id.blue);
        scrollView = (ScrollView)findViewById(R.id.scroll);

        red.setOnClickListener(this);
        yellow.setOnClickListener(this);
        green.setOnClickListener(this);
        blue.setOnClickListener(this);

        l = (LayoutInflater) getApplicationContext().getSystemService(getApplicationContext().LAYOUT_INFLATER_SERVICE);
        views = (LinearLayout)findViewById(R.id.views);

        //Get height of the mobile screen
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;

        //Set height of scrollview as height(phone) - 330 for the buttons to be seen
        ViewGroup.LayoutParams params = scrollView.getLayoutParams();
        params.height = height-330;
        scrollView.setLayoutParams(params);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.red){
            View pview = l.inflate(R.layout.red,null);
            views.addView(pview,0);
        }
        if(v.getId()==R.id.yellow){
            View pview = l.inflate(R.layout.yellow,null);
            views.addView(pview,0);
        }
        if(v.getId()==R.id.green){
            View pview = l.inflate(R.layout.green,null);
            views.addView(pview,0);
        }
        if(v.getId()==R.id.blue){
            View pview = l.inflate(R.layout.blue,null);
            views.addView(pview,0);
        }
    }
}