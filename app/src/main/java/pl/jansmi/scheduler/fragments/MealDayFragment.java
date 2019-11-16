package pl.jansmi.scheduler.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.MealDayRecyclerViewAdapter;

public class MealDayFragment extends Fragment {

    private List<String> selectedMeals;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    public MealDayFragment(List<String> selectedMeals) {
        this.selectedMeals = selectedMeals;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_day, container, false);

        recycler = view.findViewById(R.id.meal_day_fragment_recycler);

        adapter = new MealDayRecyclerViewAdapter(getContext(), selectedMeals);
        recycler.setAdapter(adapter);

        manager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(manager);

        return view;
    }

}
