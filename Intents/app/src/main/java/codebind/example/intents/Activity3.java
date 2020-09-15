package codebind.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity3 extends AppCompatActivity implements View.OnClickListener {
    Button b3;
    EditText e3;
    String txt3 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        Bundle bundle = getIntent().getExtras();
        txt3 = bundle.getString("text2");

        b3 = (Button) findViewById(R.id.b3);
        e3 = (EditText) findViewById(R.id.et3);

        e3.setText(txt3);
        b3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.b3) {
            if (e3.getText().toString() != "") {
                txt3 = e3.getText().toString();
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("text3", txt3);
                startActivity(intent);
            }
        }
    }
}