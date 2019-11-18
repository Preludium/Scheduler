package pl.jansmi.scheduler.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.NewStudyRecyclerViewAdapter;
import pl.jansmi.scheduler.dbstructure.entities.Subject;

public class NewStudyFragment extends Fragment {

    private Subject selectedSubject;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    public NewStudyFragment(Subject selectedSubject) {
        this.selectedSubject = selectedSubject;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_select_meals, container, false);

        this.recycler = root.findViewById(R.id.select_meals_fragment_recycler);

        this.manager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(manager);
        this.adapter = new NewStudyRecyclerViewAdapter(getContext(), selectedSubject);
        recycler.setAdapter(adapter);

        return root;
    }

    public Subject getSelectedSubject() {
        selectedSubject = ((NewStudyRecyclerViewAdapter) adapter).getSelectedSubject();
        return selectedSubject;
    }
}
