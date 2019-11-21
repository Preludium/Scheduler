package pl.jansmi.scheduler.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.holders.MainListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Category;
import pl.jansmi.scheduler.dbstructure.entities.Ingredient;
import pl.jansmi.scheduler.dbstructure.entities.Meal;
import pl.jansmi.scheduler.dbstructure.relations.IngredientMealJoin;

public class MealDayRecyclerViewAdapter extends RecyclerView.Adapter<MainListItemViewHolder> {

    private Context context;
    List<Meal> meals;

    public MealDayRecyclerViewAdapter(Context context, List<String> selectedMeals) {
        this.context = context;
        this.meals = new ArrayList<>();
        for (String id : selectedMeals)
            meals.add(App.db.meals().getById(id));
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

        Category cat = App.db.categories().getById(meal.getCategoryId());

        holder.title.setText(meal.getName());
        holder.desc.setText(cat.getName() + ", kcal: " + kcalSum);

        holder.menuBtn.setVisibility(View.INVISIBLE);
        holder.menuBtn.setEnabled(false);

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
}
