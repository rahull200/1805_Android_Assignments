package codebind.example.a1805_isa2.recyclerdata;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import codebind.example.a1805_isa2.CharacterMenu;
import codebind.example.a1805_isa2.R;
import codebind.example.a1805_isa2.recyclerdata.*;

public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.LayoutviewHolder> {

    private ArrayList<Season> mlayout;
    Context ctx;
    public static class LayoutviewHolder extends RecyclerView.ViewHolder{
        TextView season_no;
        LinearLayout mainlayout;


        public LayoutviewHolder(@NonNull View itemView) {
            super(itemView);
            season_no = itemView.findViewById(R.id.season_no);
            mainlayout = itemView.findViewById(R.id.mainlayout);

        }
    }

    public LayoutAdapter(ArrayList<Season> character, Context ctx){
        mlayout = character;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public LayoutviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.season_data,parent,false);
        LayoutviewHolder lvh = new LayoutviewHolder(v);
        return lvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final LayoutviewHolder holder, final int position) {
        Season currentlayout =mlayout.get(position);
        holder.season_no.setText(currentlayout.getSeason_no().toString());
        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, CharacterMenu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("season_no",holder.season_no.getText().toString());

                ctx.startActivity(intent);
            }
        });
    //    Log.e("fname",currentlayout.getFirst_name());

    }

    @Override
    public int getItemCount() {
        return mlayout.size();
    }
}
