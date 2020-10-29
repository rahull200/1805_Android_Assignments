package codebind.example.assign11_1805;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter resadapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Data data = new Data();
        addData(data); //Add values

        ArrayList<Layout> layout = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();

        names = data.getNames();

        for(int i=0;i<names.size();i++){
            layout.add(new Layout(names.get(i),""));
        }

        recyclerView = (RecyclerView)findViewById(R.id.resview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        resadapter = new LayoutAdapter(layout,this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(resadapter);

    }


    private void addData(Data data) {
        data.setNames("Ajay");
        data.setNames("Viijay");
        data.setNames("Sara");
        data.setNames("Jay");
        data.setNames("Richa");
        data.setNames("Sara");
        data.setNames("Viraj");
        data.setNames("Luven");
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
    }

}