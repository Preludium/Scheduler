package pl.jansmi.scheduler.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import pl.jansmi.scheduler.R;

public class MealDayFragment extends Fragment {

    private RecyclerView recycler;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recycler = getActivity().findViewById(R.id.meal_day_fragment_recycler);
        return inflater.inflate(R.layout.fragment_meal_day, container, false);
    }

}
