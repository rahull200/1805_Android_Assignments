package codebind.example.viewpagerandfragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


public class Register extends Fragment {

    public Register() {
    }

    // TODO: Rename and change types and number of parameters
    public static Register newInstance() {
        Register fragment = new Register();
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
        return inflater.inflate(R.layout.fragment_register, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextInputEditText name = (TextInputEditText)view.findViewById(R.id.name);
        TextInputEditText num = (TextInputEditText)view.findViewById(R.id.num);
        TextInputEditText em = (TextInputEditText)view.findViewById(R.id.email);
        TextInputEditText pass1 = (TextInputEditText)view.findViewById(R.id.pass1);
        TextInputEditText pass2 = (TextInputEditText)view.findViewById(R.id.pass2);
        Profile profile = new Profile();
        name.setText(profile.getName());
        num.setText(profile.getNumber());
        em.setText(profile.getEmail());
        pass1.setText(profile.getPass1());
        pass2.setText(profile.getPass2());
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        ((AppCompatActivity)getActivity()).getMenuInflater().inflate(R.menu.action_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}