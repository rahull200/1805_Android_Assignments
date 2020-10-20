package codebind.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener {
    Button tom,jerry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tom = (Button)findViewById(R.id.tom);
        jerry = (Button)findViewById(R.id.jerry);

        tom.setOnClickListener(this);
        jerry.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tom){
            Intent intent = new Intent(this, TomMain.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.jerry){
            Intent intent = new Intent(this,JerryMain.class);
            startActivity(intent);
        }
    }
}