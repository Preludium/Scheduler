package pl.jansmi.scheduler.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Category;
import pl.jansmi.scheduler.dbstructure.entities.Ingredient;
import pl.jansmi.scheduler.dbstructure.entities.Meal;
import pl.jansmi.scheduler.dbstructure.relations.IngredientMealJoin;
import pl.jansmi.scheduler.fragments.SelectMealsFragment;

public class SelectMealsRecyclerViewAdapter extends RecyclerView.Adapter<SelectListItemViewHolder> {

    private Context context;
    private List<Meal> meals;
    private List<Boolean> checked;

    public SelectMealsRecyclerViewAdapter(Context context, Category category, HashMap<String, Boolean> selectedMeals) {
        this.context = context;
        this.meals = App.db.meals().getByCategoryId(category.getId());
        // TODO: sort

        this.checked = new ArrayList<>(Collections.nCopies(meals.size(), false));
        if (selectedMeals != null)
            for (int i=0; i < meals.size(); ++i)
                checked.set(i, selectedMeals.getOrDefault(meals.get(i).getId(), false));
    }

    @NonNull
    @Override
    public SelectListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_select_meals, null);
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

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                checked.set(position, b);
            }
        });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public HashMap<String, Boolean> getSelectedMeals() {
        HashMap<String, Boolean> resultMap = new HashMap<>();

        for (int i=0; i<meals.size(); ++i) {
            if (checked.get(i))
                resultMap.put(meals.get(i).getId(), true);
        }

        return resultMap;
    }
}
