package pl.jansmi.scheduler.adapters;

import android.app.AlertDialog;
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
import pl.jansmi.scheduler.activities.AddIngredientActivity;
import pl.jansmi.scheduler.dbstructure.entities.Ingredient;
import pl.jansmi.scheduler.dialogs.DeletePromptDialog;

public class IngredientsRecyclerViewAdapter extends RecyclerView.Adapter<ListItemViewHolder> {

    private Context context;
    private List<Ingredient> ingredients;

    public IngredientsRecyclerViewAdapter(Context context) {
        this.context = context;
        this.ingredients = App.db.ingredients().getAll();
        // TODO: sort by ingredient favour
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listitem_main, null);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);

        holder.title.setText(ingredient.getName());
        holder.desc.setText(ingredient.getDesc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddIngredientActivity.class);
                intent.putExtra("ingredientId", ingredient.getId());
                context.startActivity(intent);
            }
        });

        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeletePromptDialog.show(context, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteItem(position);
                        // TODO: show infoBox, if 'ingredients' is empty
                        App.db.ingredients().delete(ingredient);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    private void deleteItem(int position) {
        ingredients.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, ingredients.size());
        notifyDataSetChanged();
    }
}
