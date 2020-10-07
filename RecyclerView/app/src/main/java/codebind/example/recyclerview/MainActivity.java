package codebind.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter resadapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Layout> layout = new ArrayList<>();
        layout.add(new Layout("Tom","Hellooo"));
        layout.add(new Layout("Jerry","Hii"));
        layout.add(new Layout("Tom","Hellooo"));
        layout.add(new Layout("Jerry","Hii"));
        layout.add(new Layout("Tom","Hellooo"));
        layout.add(new Layout("Jerry","Hii"));
        layout.add(new Layout("Tom","Hellooo"));
        layout.add(new Layout("Jerry","Hii"));
        layout.add(new Layout("Tom","Hellooo"));
        layout.add(new Layout("Jerry","Hii"));
        layout.add(new Layout("Tom","Hellooo"));
        layout.add(new Layout("Jerry","Hii"));
        recyclerView = (RecyclerView)findViewById(R.id.resview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        resadapter = new LayoutAdapter(layout);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(resadapter);
    }
}