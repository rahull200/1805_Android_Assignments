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
    static String name,num,em,pass1,pass2;
    public Register() {
    }

    // TODO: Rename and change types and number of parameters
    public static Register newInstance(String iname,String inum,String iem,String ip1,String ip2) {
        Register fragment = new Register();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        name = iname;
        num = inum;
        em = iem;
        pass1 = ip1;
        pass2 =ip2;
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
        TextInputEditText ipname = (TextInputEditText)view.findViewById(R.id.name);
        TextInputEditText ipnum = (TextInputEditText)view.findViewById(R.id.num);
        TextInputEditText ipem = (TextInputEditText)view.findViewById(R.id.email);
        TextInputEditText ippass1 = (TextInputEditText)view.findViewById(R.id.pass1);
        TextInputEditText ippass2 = (TextInputEditText)view.findViewById(R.id.pass2);
        ipname.setText(name);
        ipem.setText(em);
        ipnum.setText(num);
        ippass1.setText(pass1);
        ippass2.setText(pass2);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        ((AppCompatActivity)getActivity()).getMenuInflater().inflate(R.menu.action_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}