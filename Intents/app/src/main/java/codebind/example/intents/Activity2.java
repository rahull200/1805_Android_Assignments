package codebind.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity2 extends AppCompatActivity implements View.OnClickListener {
    Button b2;
    EditText e2;
    String txt2="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        b2 = (Button)findViewById(R.id.b2);
        e2 = (EditText)findViewById(R.id.et2);

        Bundle bundle = getIntent().getExtras();
        txt2 = bundle.getString("text");

        e2.setText(txt2);

        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.b2){
            if(e2.getText().toString()!=""){
                txt2=e2.getText().toString();
                Intent intent = new Intent(this,Activity3.class);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("text2",txt2);
                startActivity(intent);
            }else{

            }

        }
    }
}