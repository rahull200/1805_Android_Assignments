package codebind.example.assign11_1805;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.LayoutviewHolder> {
    public int pos;
    private ArrayList<Layout> mlayout;

    public static class LayoutviewHolder extends RecyclerView.ViewHolder{ 

        public TextView sender;
        public TextView msg;

        public LayoutviewHolder(@NonNull View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.sender_name);
            msg = itemView.findViewById(R.id.msg);
        }
    }

    public LayoutAdapter(ArrayList<Layout> layout){
        mlayout = layout;
    }

    @NonNull
    @Override
    public LayoutviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutviewHolder lvh=null;
        View v=null;
        if(viewType==0){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message,parent,false);
        }else{
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message,parent,false);
        }

        lvh = new LayoutviewHolder(v);
        return lvh;
    }


    @Override
    public int getItemViewType(int position) {

        Messages messages = new Messages();
        ArrayList<String> nm = new ArrayList<String>();
        nm = messages.getName();

        if((nm.get(position)).equals(messages.getCurrname())){
            return 0;
        }
        return 1;
    }

    @Override
    public void onBindViewHolder(@NonNull LayoutviewHolder holder, int position) {
        pos = position;
        Layout currentlayout =mlayout.get(position);
        holder.sender.setText("hii");
        holder.msg.setText(currentlayout.getSender_msg());
        LinearLayout parent = (LinearLayout) ((ViewGroup) holder.sender.getParent()).getParent();
    }

    @Override
    public int getItemCount() {
        return mlayout.size();
    }
}
