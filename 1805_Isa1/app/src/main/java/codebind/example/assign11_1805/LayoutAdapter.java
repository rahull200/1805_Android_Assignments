package codebind.example.assign11_1805;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        public Button del,editbtn;
        public TextView gender;

        public LayoutviewHolder(@NonNull View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.sender_name);
            gender = itemView.findViewById(R.id.gender);
            del = itemView.findViewById(R.id.del);
            editbtn = itemView.findViewById(R.id.edit);
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
        holder.gender.setText(currentlayout.getGender());

        holder.del.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                data = new Data();
                final String tmp = data.getdataPos(position); //Store value deleted (Name)
                final String tmpgender = data.getdataPos(position); //Store value deleted (Gender)

                data.delete(position); //delete value from arraylist at position
                data.deleteGender(position); //delete gender from arraylist at position

                tmpview = mlayout.get(position); // store layout deleted to restore
                mlayout.remove(position); // remove layout

                notifyItemRemoved(position); //notify adapter that item has been removed
                notifyItemRangeChanged(position, mlayout.size()); //notifyrange changed
                notifyDataSetChanged();

                //add snackbar
                Snackbar snackBar = Snackbar.make(holder.del, addImg(tmp+" Deleted"), Snackbar.LENGTH_LONG) .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //restore stored data onclick
                        data.add(position,tmp);
                        data.addGender(position,tmpgender);

                        mlayout.add(position,tmpview);
                        notifyItemInserted(position);
                        notifyItemRangeChanged(position, mlayout.size());
                        notifyDataSetChanged();
                    }
                });

                snackBar.show();
            }
        });



        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx,EditPerson.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("pos",position);
                ctx.startActivity(intent);
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
