package pl.jansmi.scheduler.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.holders.SelectListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Category;
import pl.jansmi.scheduler.dbstructure.entities.Ingredient;
import pl.jansmi.scheduler.dbstructure.entities.Meal;
import pl.jansmi.scheduler.dbstructure.relations.IngredientMealJoin;

public class SelectMealsRecyclerViewAdapter extends RecyclerView.Adapter<SelectListItemViewHolder> {

    private Context context;
    private List<Meal> meals;
    private Meal selectedMeal;

    private int lastCheckedPosition = -1;

    public SelectMealsRecyclerViewAdapter(Context context, Category category, Meal selectedMeal) {
        this.context = context;
        this.meals = App.db.meals().getByCategoryId(category.getId());
        // TODO: sort
        this.selectedMeal = selectedMeal;
    }

    @NonNull
    @Override
    public SelectListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listitem_select, null);
        return new SelectListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectListItemViewHolder holder, int position) {
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

        if (selectedMeal != null && meal.getId().equals(selectedMeal.getId()))
            lastCheckedPosition = position;

        holder.checkBox.setChecked(position == lastCheckedPosition);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyItemChanged(lastCheckedPosition);

                if (position == lastCheckedPosition) {
                    lastCheckedPosition = -1;
                    selectedMeal = null;

                } else {
                    lastCheckedPosition = position;
                    selectedMeal = meal;

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public Meal getSelectedMeal() {
        return selectedMeal;
    }
}
