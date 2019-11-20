package pl.jansmi.scheduler.adapters;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pl.jansmi.scheduler.R;

class SelectListItemViewHolder extends RecyclerView.ViewHolder {

    RadioButton checkBox;
    TextView title;
    TextView desc;

    SelectListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        checkBox = itemView.findViewById(R.id.select_listitem_radio);
        title = itemView.findViewById(R.id.select_listitem_title);
        desc = itemView.findViewById(R.id.select_listitem_desc);
    }
}