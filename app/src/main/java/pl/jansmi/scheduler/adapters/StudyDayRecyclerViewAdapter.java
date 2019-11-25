package pl.jansmi.scheduler.adapters;

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
    private Context context;
    private List<Study> selectedStudies;

        public StudyDayRecyclerViewAdapter(Context context, List<Study> selectedStudies) {
        this.context = context;
        this.selectedStudies = selectedStudies;
    }

    @NonNull
    @Override
    public MainListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
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

        // TODO: holder.itemView listener implementation (startActivity NewStudyingActivity and pass
        //  current study for update
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewStudyingActivity.class);
                intent.putExtra("study", study);
                context.startActivity(intent);
            }
        });

        // TODO: holder.menuBtn listener implementation (delete record)
        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeletePromptDialog.show(context, new DialogInterface.OnClickListener() {
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
