package codebind.example.assign11_1805;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter resadapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<String> names;
    ArrayList<String> gender;
    Data data;
    ArrayList<Layout> layout;
    Integer sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sum = 0;

        data = new Data();

        if(data.getNames().size()<=0){
            addData(data); //Add values
        }


        layout = new ArrayList<>();//for layouts
        names = new ArrayList<>();//for names
        gender = new ArrayList<>();//for gender

        names = data.getNames();
        gender = data.getGender();

        recyclerView = (RecyclerView)findViewById(R.id.resview);
        setRecycler(); //set recycler


        getScholarshipAmount(); //Get scholarship amount

    }
//get scolarship
    private void getScholarshipAmount() {

        sum = 0;
                sum = data.getScolarshipAmount();
        Toast.makeText(this,"Total Scholarship Amount is "+sum,Toast.LENGTH_LONG).show();
    }

    private void setRecycler() {
    //    Toast.makeText(this,"Hi"+names.size(),Toast.LENGTH_SHORT).show();
        for(int i=0;i<names.size();i++){
            layout.add(new Layout(names.get(i),gender.get(i)));
        }

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        resadapter = new LayoutAdapter(layout,this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(resadapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.reload:
                //Reset Data
                data.resetNames();
                data.resetGender();

                finish();
                startActivity(getIntent());
                break;

            case R.id.getamt:
                //Get amt
                getScholarshipAmount(); //Get scholarship amount
                break;
        }
        return super.onOptionsItemSelected(item);
    }

//Add Data
    private void addData(Data data) {
        data.setNames("Ajay");
        data.setNames("Viijay");
        data.setNames("Sara");
        data.setNames("Jay");
        data.setNames("Richa");
        data.setNames("Sara");
        data.setNames("Viraj");
        data.setNames("Raaj");
        data.setNames("Suraj");
        data.setNames("Rohan");
        data.setNames("Amit");
        data.setNames("Sujit");
        data.setNames("Aman");
        data.setNames("Brijesh");
        data.setNames("Darshan");
        data.setNames("Jay");
        data.setNames("Rohit");
        data.setNames("Rohit");
        data.setNames("Karan");
        data.setNames("Varun");


        data.setGender("Male");
        data.setGender("Male");
        data.setGender("Female");
        data.setGender("Male");
        data.setGender("Female");
        data.setGender("Female");
        data.setGender("Male");
        data.setGender("Male");
        data.setGender("Male");
        data.setGender("Male");
        data.setGender("Male");
        data.setGender("Male");
        data.setGender("Male");
        data.setGender("Male");
        data.setGender("Male");
        data.setGender("Male");
        data.setGender("Male");
        data.setGender("Male");
        data.setGender("Male");
        data.setGender("Male");
    }

}