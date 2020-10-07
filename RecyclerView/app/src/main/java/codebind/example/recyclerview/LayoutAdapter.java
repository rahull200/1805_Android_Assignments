package codebind.example.recyclerview;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.LayoutviewHolder> {

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message,parent,false);
        LayoutviewHolder lvh = new LayoutviewHolder(v);
        return lvh;
    }

    @Override
    public void onBindViewHolder(@NonNull LayoutviewHolder holder, int position) {
        Layout currentlayout =mlayout.get(position);
        holder.sender.setText(currentlayout.getSender_name());
        holder.msg.setText(currentlayout.getSender_msg());

        CardView parent = (CardView) ((ViewGroup) holder.sender.getParent()).getParent();
        if(currentlayout.getSender_name().equals("Tom")){
            parent.setBackgroundColor(Color.parseColor("#6C3FBD"));

            CardView.LayoutParams params = new CardView.LayoutParams(CardView.LayoutParams.WRAP_CONTENT, CardView.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.RIGHT;
            parent.setLayoutParams(params);
        }else{
            parent.setBackgroundColor(Color.parseColor("#595959"));
        }

    }

    @Override
    public int getItemCount() {
        return mlayout.size();
    }
}
