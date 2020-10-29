package codebind.example.assign11_1805;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.LayoutviewHolder> {

    private ArrayList<Layout> mlayout;
    Data data;
    Layout tmpview;
    Context ctx;

    public static class LayoutviewHolder extends RecyclerView.ViewHolder{

        public TextView sender;
        public TextView msg;

        public LayoutviewHolder(@NonNull View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.sender_name);
            msg = itemView.findViewById(R.id.del);

        }
    }

    public LayoutAdapter(ArrayList<Layout> layout,Context ct){
        mlayout = layout;
        this.ctx = ct;

    }

    @NonNull
    @Override
    public LayoutviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        LayoutviewHolder lvh = new LayoutviewHolder(v);
        return lvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final LayoutviewHolder holder, final int position) {
        Layout currentlayout =mlayout.get(position);
        holder.sender.setText(currentlayout.getSender_name());
        holder.msg.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                data = new Data();
                final String tmp = data.getdataPos(position); //Store value deleted
                data.delete(position); //delete value from arraylist at position

                tmpview = mlayout.get(position); // store layout deleted to restore
                mlayout.remove(position); // remove layout

                notifyItemRemoved(position); //notify adapter that item has been removed
                notifyItemRangeChanged(position, mlayout.size()); //notifyrange changed
                notifyDataSetChanged();

                //add snackbar
                Snackbar snackBar = Snackbar.make(holder.msg, addImg(tmp+" Deleted"), Snackbar.LENGTH_LONG) .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //restore stored data onclick
                        data.add(position,tmp);
                        mlayout.add(position,tmpview);
                        notifyItemInserted(position);
                        notifyItemRangeChanged(position, mlayout.size());
                        notifyDataSetChanged();
                    }
                });

                snackBar.show();
            }
        });

    }

    //Add drawable to the snackbar
    private SpannableStringBuilder addImg(String txt){
        SpannableStringBuilder str = new SpannableStringBuilder();
        str.append(" ");
        ImageSpan imageSpan = new ImageSpan(ctx,R.drawable.ic_baseline_check_circle_24);
        str.setSpan(imageSpan,str.length()-1,str.length(),0);
        str.append("  ");
        str.append(txt);
        return str;
    }

    @Override
    public int getItemCount() {
        return mlayout.size();
    }



}
