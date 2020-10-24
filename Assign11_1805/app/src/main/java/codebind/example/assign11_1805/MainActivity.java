package codebind.example.assign11_1805;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter resadapter;
    private RecyclerView.LayoutManager layoutManager;
    Messages messages;
    ArrayList<Layout> layout;
    EditText typedmsg;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = new ArrayList<>();
        messages = new Messages();

        //Set name in action bar
        messages.setCurrname("tom");

        messages.setName("tom");

        setMessages();


    }



    private void setMessages() {
        ArrayList<String> msg = new ArrayList<String>();
        ArrayList<String> nm = new ArrayList<String>();
        msg = messages.getMsgs();
        nm = messages.getName();
        layout.clear();
        for(int i=0;i<msg.size();i++){
            layout.add(new Layout(nm.get(i),msg.get(i)));
        }
        recyclerView = (RecyclerView)findViewById(R.id.resview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        resadapter = new LayoutAdapter(layout);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(resadapter);
    }


}