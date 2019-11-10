package pl.jansmi.scheduler.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Category;
import pl.jansmi.scheduler.dbstructure.entities.Meal;

public class MealCategoryFragment extends Fragment {

    private Context context;
    private List<Meal> meals;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    public MealCategoryFragment(Context context, Category category) {
        this.context = context;
        this.meals = App.db.meals().getByCategoryId(category.getId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_category, container, false);
    }

}
