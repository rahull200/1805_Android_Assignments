package codebind.example.viewpagerandfragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class ViewUser extends Fragment implements View.OnClickListener {
    Toolbar tb;
    TextInputEditText calender;
    public ViewUser() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ViewUser newInstance() {
        ViewUser fragment = new ViewUser();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_user, container, false);
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.mal:
                if (checked)
                    Toast.makeText(getActivity(),"hi",Toast.LENGTH_LONG);
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tb = (Toolbar)view.findViewById(R.id.action);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setSpinner(view);

        calender = (TextInputEditText)view.findViewById(R.id.calender);
        calender.setOnClickListener(this);

        super.onViewCreated(view, savedInstanceState);
    }

    private void setSpinner(View view) {
        Spinner sp = (Spinner)view.findViewById(R.id.spin);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(getActivity(),R.array.dist,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.calender){
            DatePickerDialog db = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                }
            }, 2020, 9, 25);
            db.show();
        }
    }
}