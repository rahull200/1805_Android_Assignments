package codebind.example.assign_12_webclient.recyclerdata;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import codebind.example.assign_12_webclient.R;

public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.LayoutviewHolder> {

    private ArrayList<Layout> mlayout;
    public static class LayoutviewHolder extends RecyclerView.ViewHolder{

        public TextView empcode,lname,salary;
        public TextView fname;

        public LayoutviewHolder(@NonNull View itemView) {
            super(itemView);
            empcode = itemView.findViewById(R.id.emp_code);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            salary = itemView.findViewById(R.id.salary);
        }
    }

    public LayoutAdapter(ArrayList<Layout> layout){
        mlayout = layout;
    }

    @NonNull
    @Override
    public LayoutviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.person,parent,false);
        LayoutviewHolder lvh = new LayoutviewHolder(v);
        return lvh;
    }

    @Override
    public void onBindViewHolder(@NonNull LayoutviewHolder holder, int position) {
        Layout currentlayout =mlayout.get(position);
    //    Log.e("fname",currentlayout.getFirst_name());
        holder.fname.setText(currentlayout.getFirst_name());
        holder.empcode.setText(currentlayout.getEmp_code().toString());
        holder.lname.setText(currentlayout.getLast_name());
        holder.salary.setText(currentlayout.getSalary().toString());

    }

    @Override
    public int getItemCount() {
        return mlayout.size();
    }
}
