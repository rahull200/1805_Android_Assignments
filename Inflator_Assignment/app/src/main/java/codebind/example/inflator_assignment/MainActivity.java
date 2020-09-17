package codebind.example.inflator_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout views;
    LayoutInflater l;
    Button red,yellow,blue,green;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        red = (Button)findViewById(R.id.red);
        yellow = (Button)findViewById(R.id.yellow);
        green = (Button)findViewById(R.id.green);
        blue = (Button)findViewById(R.id.blue);

        red.setOnClickListener(this);
        yellow.setOnClickListener(this);
        green.setOnClickListener(this);
        blue.setOnClickListener(this);

        views = (LinearLayout)findViewById(R.id.views);
        l=getLayoutInflater();



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