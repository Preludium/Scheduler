package pl.jansmi.scheduler.adapters.holders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pl.jansmi.scheduler.R;

public class MainListItemViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView desc;
    public ImageButton menuBtn;

    public MainListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.main_listitem_title);
        desc = itemView.findViewById(R.id.main_listitem_desc);
        menuBtn = itemView.findViewById(R.id.main_listitem_menu);
    }

}
