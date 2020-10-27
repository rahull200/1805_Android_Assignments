package codebind.example.assign11_1805;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        LayoutviewHolder lvh = new LayoutviewHolder(v);
        return lvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final LayoutviewHolder holder, final int position) {
        Layout currentlayout =mlayout.get(position);
        holder.sender.setText(currentlayout.getSender_name());
        holder.msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = new Data();
                final String tmp = data.getdataPos(position);
                data.delete(position);

                tmpview = mlayout.get(position);
                mlayout.remove(position);

                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mlayout.size());
                notifyDataSetChanged();

                Snackbar snackBar = Snackbar.make(holder.msg, tmp+" Deleted", Snackbar.LENGTH_LONG) .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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

    @Override
    public int getItemCount() {
        return mlayout.size();
    }
}
