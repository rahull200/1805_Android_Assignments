package codebind.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Home extends AppCompatActivity implements View.OnClickListener {
    Button tom,jerry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.e("hii","hello");

        tom = (Button)findViewById(R.id.tom);
        jerry = (Button)findViewById(R.id.jerry);

        tom.setOnClickListener(this);
        jerry.setOnClickListener(this);
      //  Toast.makeText(getApplicationContext(),"hii",Toast.LENGTH_LONG);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tom){
            Intent intent = new Intent(this,MainActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("name","tom");
            startActivity(intent);
        }
        if(v.getId()==R.id.jerry){
            Intent intent = new Intent(this,MainActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("name","jerry");
            startActivity(intent);
        }
    }
}