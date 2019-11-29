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
import pl.jansmi.scheduler.adapters.TasksDayRecyclerViewAdapter;
import pl.jansmi.scheduler.dbstructure.entities.Task;

public class TasksDayFragment extends Fragment {

    private List<Task> selectedTask;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    public TasksDayFragment(List<Task> selectedTask) {
        this.selectedTask = selectedTask;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks_day, container, false);

        recycler = view.findViewById(R.id.tasks_day_fragment_recycler);

        adapter = new TasksDayRecyclerViewAdapter(getActivity(), selectedTask);
        recycler.setAdapter(adapter);

        manager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(manager);

        return view;
    }
}
