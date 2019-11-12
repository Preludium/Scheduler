package pl.jansmi.scheduler.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Ingredient;

public class MealIngredientsRecyclerViewAdapter extends RecyclerView.Adapter<IngredientListItemViewHolder> {

    private Context context;
    private List<Ingredient> ingredients;
    private List<Integer> counts;

    public MealIngredientsRecyclerViewAdapter(Context context) {
        this.context = context;
        this.ingredients = App.db.ingredients().getAll();
        // TODO: sort by favour
        this.counts = new ArrayList<>(Collections.nCopies(ingredients.size(), 0));
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
        holder.count.setText(String.valueOf(counts.get(position)));
        holder.title.setText(ingredients.get(position).getName());
        holder.desc.setText(ingredients.get(position).getDesc());

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counts.set(position, counts.get(position) + 1);
                holder.count.setText(String.valueOf(counts.get(position)));
            }
        });

        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counts.set(position, cutZero(counts.get(position) - 1));
                holder.count.setText(String.valueOf(counts.get(position)));
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

    public List<Integer> getCounts() {
        return counts;
    }
}
