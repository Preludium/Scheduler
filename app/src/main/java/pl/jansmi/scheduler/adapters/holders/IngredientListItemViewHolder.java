package pl.jansmi.scheduler.adapters.holders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pl.jansmi.scheduler.R;

public class IngredientListItemViewHolder extends RecyclerView.ViewHolder {

    public TextView count, title, desc;
    public Button addBtn, delBtn;

    public IngredientListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        count = itemView.findViewById(R.id.ingredient_listitem_count);
        title = itemView.findViewById(R.id.ingredient_listitem_title);
        desc = itemView.findViewById(R.id.ingredient_listitem_desc);
        addBtn = itemView.findViewById(R.id.ingredient_listitem_add);
        delBtn = itemView.findViewById(R.id.ingredient_listitem_del);
    }
}
