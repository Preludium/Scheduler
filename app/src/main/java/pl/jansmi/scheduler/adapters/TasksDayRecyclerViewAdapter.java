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
import pl.jansmi.scheduler.activities.NewTaskActivity;
import pl.jansmi.scheduler.adapters.holders.MainListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Task;
import pl.jansmi.scheduler.dialogs.DeletePromptDialog;

public class TasksDayRecyclerViewAdapter extends RecyclerView.Adapter<MainListItemViewHolder> {
    private Activity activity;
    private List<Task> selectedTasks;

    public TasksDayRecyclerViewAdapter(Activity activity, List<Task> selectedTasks) {
        this.activity = activity;
        this.selectedTasks = selectedTasks;
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
        Task task = selectedTasks.get(position);

        holder.title.setText(task.getName());
        holder.desc.setText("");
        // TODO: holder.itemView listener implementation (startActivity NewStudyingActivity and pass
        //  current study for update
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, NewTaskActivity.class);
                intent.putExtra("task", task);
                activity.startActivityForResult(intent, 3);
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
                        App.db.tasks().delete(task);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return selectedTasks.size();
    }

    private void deleteItem(int position) {
        selectedTasks.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, selectedTasks.size());
        notifyDataSetChanged();
    }
}