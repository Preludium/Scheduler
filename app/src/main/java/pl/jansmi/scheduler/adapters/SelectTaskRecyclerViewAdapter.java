package pl.jansmi.scheduler.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.holders.SelectListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Arrangement;
import pl.jansmi.scheduler.dbstructure.entities.Task;

public class SelectTaskRecyclerViewAdapter extends RecyclerView.Adapter<SelectListItemViewHolder> {

    private Context context;
    private List<Task> allTasks;
    private Task selectedTask;

    public SelectTaskRecyclerViewAdapter(Context context, Task selectedTask) {
        this.context = context;
        this.selectedTask = selectedTask;
        allTasks = new ArrayList<>();
        List<Arrangement> arrs = App.db.arrangements().getByUserId(App.session.getUserId());
        for (Arrangement arr : arrs)
            allTasks.addAll(App.db.tasks().getByArrangementId(arr.getId()));
        if(this.selectedTask == null && allTasks != null)
            this.selectedTask = allTasks.get(0);
    }

    @NonNull
    @Override
    public SelectListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listitem_select, null);
        return new SelectListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectListItemViewHolder holder, int position) {
        Task task = allTasks.get(position);
        holder.title.setText(task.getName());
        holder.desc.setText("");

        holder.checkBox.setClickable(false);
        if(selectedTask != null && selectedTask == task)
            holder.checkBox.setChecked(true);
        else
            holder.checkBox.setChecked(false);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!holder.checkBox.isChecked())
                    selectedTask = task;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }

    public Task getSelectedTask() {
        return selectedTask;
    }
}
