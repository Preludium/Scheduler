package pl.jansmi.scheduler.adapters.holders;

import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pl.jansmi.scheduler.R;

public class TargetSwitchListItemViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public Switch switchBtn;
    public TextView deadline;

    public TargetSwitchListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.target_switch_listitem_title);
        deadline = itemView.findViewById(R.id.target_switch_listitem_deadline_date);
        switchBtn = itemView.findViewById(R.id.target_switch_listitem_switch);
    }
}
