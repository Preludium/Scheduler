package pl.jansmi.scheduler.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.holders.IngredientListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Ingredient;

public class MealIngredientsRecyclerViewAdapter extends RecyclerView.Adapter<IngredientListItemViewHolder> {

    private Context context;
    private List<Ingredient> ingredients;
    private HashMap<String, Integer> counts;
//    private List<Integer> counts;

    public MealIngredientsRecyclerViewAdapter(Context context, HashMap<String, Integer> selectedIngredients) {
        this.context = context;
        this.ingredients = App.db.ingredients().getAll();
        this.counts = selectedIngredients;
    }

    @NonNull
    @Override
    public IngredientListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listitem_ingredient, null);
        return new IngredientListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientListItemViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);

        Integer c = counts.get(ingredient.getId());
        holder.count.setText(String.valueOf(c != null ? c : 0));
        holder.title.setText(ingredient.getName());
        holder.desc.setText(ingredient.getDesc());

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counts.containsKey(ingredient.getId()))
                    counts.replace(ingredient.getId(), counts.get(ingredient.getId()) + 1);
                else
                    counts.put(ingredient.getId(), 1);

                holder.count.setText(String.valueOf(counts.get(ingredient.getId())));
            }
        });

        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counts.containsKey(ingredient.getId()))
                    counts.replace(ingredient.getId(), cutZero(counts.get(ingredient.getId()) - 1));

                holder.count.setText(String.valueOf(counts.get(ingredient.getId())));
            }
        });

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    private int cutZero(int n) {
        return n >= 0 ? n : 0;
    }

    public HashMap<String, Integer> getSelectedIngredients() {
        return counts;
    }

    public void updateQuery(String newText) {
        this.ingredients = App.db.ingredients().getAllLike(newText);
        notifyDataSetChanged();
    }

}
