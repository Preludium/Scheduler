package pl.jansmi.scheduler.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.TrainingDayRecyclerViewAdapter;
import pl.jansmi.scheduler.dbstructure.entities.Practice;

public class TrainingDayFragment extends Fragment {

    private List<Practice> selectedPractices;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    public TrainingDayFragment(List<Practice> selectedPractices) {
        this.selectedPractices = selectedPractices;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training_day, container, false);

        recycler = view.findViewById(R.id.trainig_day_fragment_recycler);

        adapter = new TrainingDayRecyclerViewAdapter(getActivity(), selectedPractices);
        recycler.setAdapter(adapter);

        manager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(manager);

        return view;
    }
}