package pl.jansmi.scheduler.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Ingredient;

public class IngredientsRecyclerViewAdapter extends RecyclerView.Adapter<ListItemViewHolder> {

    private Context context;
    private List<Ingredient> ingredients;

    public IngredientsRecyclerViewAdapter(Context context) {
        this.context = context;
        this.ingredients = App.db.ingredients().getAll();
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

        String desc = String.valueOf(ingredient.getQuantity());
        desc += ingredient.getUnit() + ", ";
        desc += ingredient.getKcal() + "kcal";

        holder.title.setText(ingredient.getName());
        holder.desc.setText(ingredient.getQuantity() );
        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }
}
