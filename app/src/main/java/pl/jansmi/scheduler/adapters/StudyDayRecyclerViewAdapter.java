package pl.jansmi.scheduler.adapters;

import android.app.Activity;
import android.content.Context;
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
import pl.jansmi.scheduler.activities.NewStudyingActivity;
import pl.jansmi.scheduler.adapters.holders.MainListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Study;
import pl.jansmi.scheduler.dbstructure.entities.Subject;
import pl.jansmi.scheduler.dialogs.DeletePromptDialog;

public class StudyDayRecyclerViewAdapter extends RecyclerView.Adapter<MainListItemViewHolder> {
    private Activity activity;
    private List<Study> selectedStudies;

        public StudyDayRecyclerViewAdapter(Activity activity, List<Study> selectedStudies) {
        this.activity = activity;
        this.selectedStudies = selectedStudies;
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
        Study study = selectedStudies.get(position);
        Subject subject = App.db.subjects().getById(study.getSubjectId());

        holder.title.setText(study.getTitle());
        if (subject != null)
            holder.desc.setText(subject.getName());
        else {
            holder.desc.setText("");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, NewStudyingActivity.class);
                intent.putExtra("study", study);
                activity.startActivityForResult(intent, 4);
            }
        });

        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeletePromptDialog.show(activity, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteItem(position);
                        App.db.studies().delete(study);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return selectedStudies.size();
    }

    private void deleteItem(int position) {
        selectedStudies.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, selectedStudies.size());
        notifyDataSetChanged();
    }
}
