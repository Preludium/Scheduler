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
import pl.jansmi.scheduler.activities.AddIngredientActivity;
import pl.jansmi.scheduler.adapters.holders.MainListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Ingredient;
import pl.jansmi.scheduler.dialogs.DeletePromptDialog;

public class IngredientsRecyclerViewAdapter extends RecyclerView.Adapter<MainListItemViewHolder> {

    private Context context;
    private List<Ingredient> ingredients;

    public IngredientsRecyclerViewAdapter(Context context) {
        this.context = context;
        this.ingredients = App.db.ingredients().getAll();

        // sort by favour descending
        this.ingredients.sort((ing1, ing2) -> Float.compare(ing2.getFavour(), ing1.getFavour()));
    }

    @NonNull
    @Override
    public MainListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listitem_main, null);
        return new MainListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainListItemViewHolder holder, int position) {
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
