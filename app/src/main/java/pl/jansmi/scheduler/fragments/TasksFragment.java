package pl.jansmi.scheduler.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Task;

public class TasksFragment extends Fragment {
    private TaskDayFragmentPagerAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabs;
    private List<TasksDayFragment> tasksDayFragmentList;
    private List<List<Task>> selectedTasks;

    public TasksFragment(List<List<Task>> selectedTasks) {
        this.selectedTasks = selectedTasks;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);

        this.tasksDayFragmentList = new ArrayList<>();
        for (int i = 0; i < 7; ++i)
            tasksDayFragmentList.add(new TasksDayFragment(selectedTasks.get(i)));

        adapter = new TaskDayFragmentPagerAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.tasks_fragment_pager);
        viewPager.setAdapter(adapter);

        tabs = view.findViewById(R.id.tasks_fragment_tabs);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabs.setupWithViewPager(viewPager);

        return view;
    }


    private class TaskDayFragmentPagerAdapter extends FragmentPagerAdapter {
        TaskDayFragmentPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return tasksDayFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Monday";
                case 1:
                    return "Tuesday";
                case 2:
                    return "Wednesday";
                case 3:
                    return "Thursday";
                case 4:
                    return "Friday";
                case 5:
                    return "Saturday";
                case 6:
                    return "Sunday";
            }
            return null;
        }
    }

    public int getCurrentDay() {
        return viewPager.getCurrentItem();
    }

}