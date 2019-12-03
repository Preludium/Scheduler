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
import pl.jansmi.scheduler.activities.NewTargetActivity;
import pl.jansmi.scheduler.activities.TargetActivity;
import pl.jansmi.scheduler.adapters.holders.TargetSwitchListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Target;
import pl.jansmi.scheduler.dialogs.DeletePromptDialog;

public class TargetsRecyclerViewAdapter extends RecyclerView.Adapter<TargetSwitchListItemViewHolder> {

    private Context context;
    private List<Target> targets;

    public TargetsRecyclerViewAdapter(Context context) {
        this.context = context;
        this.targets = App.db.targets().getAll();
    }

    @NonNull
    @Override
    public TargetSwitchListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listitem_target_switch, null);
        return new TargetSwitchListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TargetSwitchListItemViewHolder holder, int position) {
        Target target = targets.get(position);

        holder.title.setText(target.getName());
        if(target.getDeadline() != null)
            holder.deadline.setText(String.format("%d/%d/%d", target.getDeadline().getDate(), target.getDeadline().getMonth() + 1, target.getDeadline().getYear()));
        else
            holder.deadline.setText("");
        holder.switchBtn.setChecked(target.isAchieved());
        holder.switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                target.setAchieved(!target.isAchieved());
                App.db.targets().update(target);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                DeletePromptDialog.show(context, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteItem(position);
                        App.db.targets().delete(target);
                    }
                });
                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewTargetActivity.class);
                intent.putExtra("target", target);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return targets.size();
    }

    private void deleteItem(int position) {
        targets.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, targets.size());
        notifyDataSetChanged();
    }
}
