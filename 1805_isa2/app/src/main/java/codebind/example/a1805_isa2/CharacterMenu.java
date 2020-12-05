package codebind.example.a1805_isa2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import codebind.example.a1805_isa2.Data.CharacterData;
import codebind.example.a1805_isa2.recyclerdata.CharacterMenuLayoutAdapter;
import codebind.example.a1805_isa2.recyclerdata.Layout;
import codebind.example.a1805_isa2.recyclerdata.LayoutAdapter;

public class CharacterMenu extends AppCompatActivity {
    static ArrayList<Layout> characters;
    CharacterData characterData;
    static String season_no;
    static ArrayList<Layout> currseasonData;

    RecyclerView recyclerView;
    private RecyclerView.Adapter resadapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_menu);

        //Get season number
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            season_no = bundle.getString("season_no");
        }

        getSupportActionBar().setTitle("Season "+season_no+" Cast");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //object of class
        characterData = new CharacterData();
        //get data received from server
        characters = characterData.getCharacterData();

        setContent();


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);



    }

    private void setContent() {
        Layout singleChar; //Character class
        String season; //season value

        currseasonData = new ArrayList<>();

        for(int i=0;i<characters.size();i++){
            singleChar = characters.get(i);
            season = singleChar.getAppearance();
            //check if character if present in particular season
            if(season.contains(season_no)){
                currseasonData.add(new Layout(singleChar.getName(),singleChar.getImg(),
                        singleChar.getNickname(),singleChar.getAppearance(),
                        singleChar.getPortrayed()));
            }

        }
        //set recycler view
        recyclerView = (RecyclerView)findViewById(R.id.char_resview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        resadapter = new CharacterMenuLayoutAdapter(currseasonData,getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(resadapter);
    }
}