package pl.jansmi.scheduler.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.holders.SelectListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Subject;

public class SelectSubjectRecyclerViewAdapter extends RecyclerView.Adapter<SelectListItemViewHolder> {

    private Context context;
    private List<Subject> subjects;
    private int selectedPosition = -1;
    private Subject selectedSubject;

    public SelectSubjectRecyclerViewAdapter(Context context) {
        this.context = context;
        this.subjects = App.db.subjects().getAll();
    }

    @NonNull
    @Override
    public SelectListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listitem_select, null);
        return new SelectListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectListItemViewHolder holder, int position) { //
        Subject subject = subjects.get(position);

        holder.title.setText(subject.getName());
        holder.desc.setText("");
        holder.checkBox.setChecked(selectedPosition == position);

//        holder.checkBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                selectedPosition = holder.getAdapterPosition();
//                adapter.notifyDatasetChanged();
//
//            }
//        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyItemChanged(selectedPosition);

                if(selectedPosition == position){
                    selectedPosition = -1;
                    selectedSubject = null;
//                    holder.checkBox.setChecked(true);
                }
                else{
//                    holder.checkBox.setChecked(false);
                    selectedPosition = position;
                    selectedSubject = subject;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public Subject getSelectedSubject() {
        return selectedSubject;
    }
}
