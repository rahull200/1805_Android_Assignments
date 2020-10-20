package codebind.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter resadapter;
    private RecyclerView.LayoutManager layoutManager;
    Messages messages;
    ArrayList<Layout> layout;
    EditText typedmsg;
    Button send;
    String name;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bundle = getIntent().getExtras();
        name = bundle.getString("name");
        getSupportActionBar().setTitle(name);

        layout = new ArrayList<>();
        messages = new Messages();

        //Set name in action bar
        messages.setCurrname(name);

        setMessages();

        typedmsg = (EditText)findViewById(R.id.typed_msg);
        send = (Button)findViewById(R.id.send);
        send.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.send){
            messages.setMsgs(typedmsg.getText().toString());
            messages.setName(name);

            setMessages();
            typedmsg.setText("");
        }
    }
}