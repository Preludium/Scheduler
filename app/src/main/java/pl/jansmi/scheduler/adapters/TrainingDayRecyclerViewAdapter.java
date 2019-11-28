package pl.jansmi.scheduler.adapters;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.activities.NewTrainingActivity;
import pl.jansmi.scheduler.adapters.holders.MainListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Discipline;
import pl.jansmi.scheduler.dbstructure.entities.Practice;
import pl.jansmi.scheduler.dialogs.DeletePromptDialog;

public class TrainingDayRecyclerViewAdapter extends RecyclerView.Adapter<MainListItemViewHolder> {
    private Activity activity;
    private List<Practice> selectedPractices;

    public TrainingDayRecyclerViewAdapter(Activity activity, List<Practice> selectedPractices) {
        this.activity = activity;
        this.selectedPractices = selectedPractices;
    }

    @NonNull
    @Override
    public MainListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.listitem_main, null);
        return new MainListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainListItemViewHolder holder, int position) {
        Practice practice = selectedPractices.get(position);
        Discipline discipline = App.db.disciplines().getById(practice.getDisciplineId());

        holder.title.setText(practice.getName());
        if (discipline != null)
            holder.desc.setText(discipline.getName());
        else {
            holder.desc.setText("");
        }

        // TODO: holder.itemView listener implementation (startActivity NewStudyingActivity and pass
        //  current study for update
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, NewTrainingActivity.class);
                intent.putExtra("practice", practice);
                activity.startActivityForResult(intent, 2);
            }
        });

        // TODO: holder.menuBtn listener implementation (delete record)
        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeletePromptDialog.show(activity, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteItem(position);
                        App.db.practices().delete(practice);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return selectedPractices.size();
    }

    private void deleteItem(int position) {
        selectedPractices.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, selectedPractices.size());
        notifyDataSetChanged();
    }
}
