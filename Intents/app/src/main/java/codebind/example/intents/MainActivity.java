package codebind.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1;
    EditText e1;
    String txt="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.b1);
        e1 = (EditText)findViewById(R.id.et1);

        Bundle bundle = getIntent().getExtras();
        if(bundle!= null){
            String txt2 = bundle.getString("text3");
            e1.setText(txt2);
        }

        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.b1){
            if(e1.getText().toString()!=""){
                txt=e1.getText().toString();
                Intent intent = new Intent(this,Activity2.class);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("text",txt);
                startActivity(intent);
            }else{

            }

        }
    }
}