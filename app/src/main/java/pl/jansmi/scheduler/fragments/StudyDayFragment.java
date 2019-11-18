package pl.jansmi.scheduler.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.StudyDayRecyclerViewAdapter;
import pl.jansmi.scheduler.dbstructure.entities.Study;


public class StudyDayFragment extends Fragment {

    private List<Study> selectedStudies;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    public StudyDayFragment(List<Study> selectedStudies) {
        this.selectedStudies = selectedStudies;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_study_day, container, false);

        recycler = view.findViewById(R.id.study_day_fragment_recycler);

        adapter = new StudyDayRecyclerViewAdapter(getContext(), selectedStudies);
        recycler.setAdapter(adapter);

        manager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(manager);

        return view;
    }
}
