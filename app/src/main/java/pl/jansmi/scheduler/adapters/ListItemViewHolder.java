package pl.jansmi.scheduler.adapters;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pl.jansmi.scheduler.R;

class ListItemViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView desc;
    ImageButton menuBtn;

    ListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.main_listitem_title);
        desc = itemView.findViewById(R.id.main_listitem_desc);
        menuBtn = itemView.findViewById(R.id.main_listitem_menu);
    }

}
