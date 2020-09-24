package codebind.example.assignment6_material_ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar tb;
    TextInputEditText calender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb = (Toolbar)findViewById(R.id.action);
        setSupportActionBar(tb);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setSpinner();

        calender = (TextInputEditText)findViewById(R.id.calender);
        calender.setOnClickListener(this);

    }

    private void setSpinner() {
        Spinner sp = (Spinner)findViewById(R.id.spin);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.dist,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.mal:
                if (checked)
                    Toast.makeText(getApplicationContext(),"hi",Toast.LENGTH_LONG);
                    // Pirates are the best
                    break;
            case R.id.fem:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.oth:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.calender){
            DatePickerDialog db = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                }
            }, 2020, 9, 25);
            db.show();
        }
    }
}