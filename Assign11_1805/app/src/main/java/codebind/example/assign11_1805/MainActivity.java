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
        addData(data);

        ArrayList<Layout> layout = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();

        names = data.getNames();

        for(int i=0;i<names.size();i++){
            layout.add(new Layout(names.get(i),""));
        }

        recyclerView = (RecyclerView)findViewById(R.id.resview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        resadapter = new LayoutAdapter(layout);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(resadapter);

        setData();
    }

    private void setData() {
        Button del = (Button)findViewById(R.id.msg);
    }

    private void addData(Data data) {
        data.setNames("Ajay");
        data.setNames("Viijay");
        data.setNames("Sujay");
        data.setNames("jay");
    }

}