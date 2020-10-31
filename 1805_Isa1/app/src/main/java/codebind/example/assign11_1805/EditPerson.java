package codebind.example.assign11_1805;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditPerson extends AppCompatActivity implements View.OnClickListener {
    int position;
    String pos;
    EditText editname,editgender;
    Button editbtn;
    Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            position = bundle.getInt("pos");

        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editgender = (EditText)findViewById(R.id.editgender);
        editname = (EditText)findViewById(R.id.editnm);
        editbtn = (Button)findViewById(R.id.edit);

        editbtn.setOnClickListener(this);

        setData(position);


    }

    private void setData(int position) {
        data = new Data();
        editname.setText(data.getdataPos(position));
        editgender.setText(data.getGenderPos(position));

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.edit){
            String newname = data.EditName(position,editname.getText().toString());
            editname.setText(newname);
        }
    }
}