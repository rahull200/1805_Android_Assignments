package codebind.example.a1805_isa2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import codebind.example.a1805_isa2.Data.CharacterData;
import codebind.example.a1805_isa2.recyclerdata.Layout;

public class EditNickname extends AppCompatActivity implements View.OnClickListener {
    EditText nickname;
    Button editbtn;
    CharacterData data;
    String name;

    ArrayList<Layout> cast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nickname);

        nickname = (EditText)findViewById(R.id.nicknametxt);
        editbtn = (Button)findViewById(R.id.editbtn);

        data = new CharacterData();
        //get nickname to be edited
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            name = bundle.getString("nickname");
            nickname.setText(name);
        }

        getSupportActionBar().setTitle("Edit "+name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.editbtn){
            Layout singleChar; //Character class
            String nicknametxt; //new nickname
            String newname = nickname.getText().toString();

            cast = data.getCharacterData();
            //check if nickname is the same as nickname to be edited
            for(int i=0;i<cast.size();i++){
                singleChar = cast.get(i);
                nicknametxt = singleChar.getNickname();
                //if same replace old nickname with new nickname
                if(nicknametxt.equals(name)){
                    singleChar.setNickname(newname);
                }
            }
            //update data retrieved from server
            data.setCharacterData(cast);
            Toast.makeText(getApplicationContext(),"Nickname edited successfully",Toast.LENGTH_LONG).show();
            //Go to CharacterMenu
            Intent intent = new Intent(this,CharacterMenu.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
            startActivity(intent);

        }
    }
}