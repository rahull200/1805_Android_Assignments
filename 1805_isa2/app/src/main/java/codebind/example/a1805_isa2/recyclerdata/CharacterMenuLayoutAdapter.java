package codebind.example.a1805_isa2.recyclerdata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import codebind.example.a1805_isa2.CharacterMenu;
import codebind.example.a1805_isa2.EditNickname;
import codebind.example.a1805_isa2.R;

public class CharacterMenuLayoutAdapter extends RecyclerView.Adapter<CharacterMenuLayoutAdapter.LayoutviewHolder> {

    private ArrayList<Layout> mlayout;
    private Context ctx;
    public static class LayoutviewHolder extends RecyclerView.ViewHolder{
        TextView name,nickname,portrayed;
        ImageView img;
        Button editbtn;

        public LayoutviewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            nickname = itemView.findViewById(R.id.nickname);
            portrayed = itemView.findViewById(R.id.portrayed);
            img = itemView.findViewById(R.id.img);
            editbtn = itemView.findViewById(R.id.editbtn);


        }
    }

    public CharacterMenuLayoutAdapter(ArrayList<Layout> character, Context applicationContext){
        mlayout = character;
        ctx = applicationContext;
    }

    @NonNull
    @Override
    public LayoutviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_layout,parent,false);
        LayoutviewHolder lvh = new LayoutviewHolder(v);
        return lvh;
    }

    @Override
    public void onBindViewHolder(@NonNull LayoutviewHolder holder, int position) {
        final Layout currentlayout =mlayout.get(position);
        holder.name.setText(currentlayout.getName()); //set name value
        holder.nickname.setText(currentlayout.getNickname()); //set nickname value
        holder.portrayed.setText(currentlayout.getPortrayed()); //set portrayed value

        //set Image
        Glide.with(ctx)
                .load(currentlayout.getImg())
                .into(holder.img);

        //set listener to edit btn
        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, EditNickname.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("nickname",currentlayout.getNickname());
                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlayout.size();
    }
}
