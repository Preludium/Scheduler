package pl.jansmi.scheduler.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pl.jansmi.scheduler.R;

class IngredientListItemViewHolder extends RecyclerView.ViewHolder {

    TextView count, title, desc;
    Button addBtn, delBtn;

    IngredientListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        count = itemView.findViewById(R.id.ingredient_listitem_count);
        title = itemView.findViewById(R.id.ingredient_listitem_title);
        desc = itemView.findViewById(R.id.ingredient_listitem_desc);
        addBtn = itemView.findViewById(R.id.ingredient_listitem_add);
        delBtn = itemView.findViewById(R.id.ingredient_listitem_del);
    }
}
