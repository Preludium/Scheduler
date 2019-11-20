package pl.jansmi.scheduler.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.SelectMealsRecyclerViewAdapter;
import pl.jansmi.scheduler.dbstructure.entities.Category;
import pl.jansmi.scheduler.dbstructure.entities.Meal;
import pl.jansmi.scheduler.fragments.MealCategoryFragment;

public class SelectMealsFragment extends Fragment {

    private Category category;
    private Meal selectedMeal;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    public SelectMealsFragment(Category category, Meal selectedMeal) {
        this.category = category;
        this.selectedMeal = selectedMeal;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_select_meals, container, false);

        this.recycler = root.findViewById(R.id.select_meals_fragment_recycler);

        this.manager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(manager);
        this.adapter = new SelectMealsRecyclerViewAdapter(getContext(), category, selectedMeal);
        recycler.setAdapter(adapter);

        return root;
    }

    public Meal getSelectedMeal() {
        selectedMeal = ((SelectMealsRecyclerViewAdapter) adapter).getSelectedMeal();
        return selectedMeal;
    }

}
