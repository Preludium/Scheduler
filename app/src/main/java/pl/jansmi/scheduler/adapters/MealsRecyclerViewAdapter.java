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
import pl.jansmi.scheduler.activities.AddMealActivity;
import pl.jansmi.scheduler.adapters.holders.MainListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Category;
import pl.jansmi.scheduler.dbstructure.entities.Ingredient;
import pl.jansmi.scheduler.dbstructure.entities.Meal;
import pl.jansmi.scheduler.dbstructure.relations.IngredientMealJoin;
import pl.jansmi.scheduler.dialogs.DeletePromptDialog;

public class MealsRecyclerViewAdapter extends RecyclerView.Adapter<MainListItemViewHolder> {

    private Context context;
    private List<Meal> meals;

    public MealsRecyclerViewAdapter(Context context, Category category) {
        this.context = context;
        this.meals = App.db.meals().getByCategoryId(category.getId());
        // TODO: sort
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
        Meal meal = meals.get(position);

        // counting sum of calories by ingredients
        int kcalSum = 0;
        List<IngredientMealJoin> joins = App.db.ingredientMealJoin().getIngredientsByMealId(meal.getId());

        for (IngredientMealJoin join : joins) {
            Ingredient ing = App.db.ingredients().getById(join.getIngredientId());
            kcalSum += ing.getKcal() * join.getQuantity();
        }

        holder.title.setText(meal.getName());
        holder.desc.setText("Kcal: " + kcalSum);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddMealActivity.class);
                intent.putExtra("mealId", meal.getId());
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
                        // TODO: show infoBox, if 'meals' is empty

                        List<IngredientMealJoin> joins =
                                App.db.ingredientMealJoin().getIngredientsByMealId(meal.getId());
                        for (IngredientMealJoin join : joins)
                            App.db.ingredientMealJoin().delete(join);

                        App.db.meals().delete(meal);
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    private void deleteItem(int position) {
        meals.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, meals.size());
        notifyDataSetChanged();
    }

}
