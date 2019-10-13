package pl.jansmi.scheduler;

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

public class MealDayFragment extends Fragment {

    private RecyclerView recycler;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recycler = getActivity().findViewById(R.id.meal_day_fragment_recycler);
        return inflater.inflate(R.layout.fragment_meal_day, container, false);
    }

    public class MealDayRecyclerViewAdapter extends RecyclerView.Adapter<MealDayRecyclerViewAdapter.ViewHolder> {

        public MealDayRecyclerViewAdapter() {

        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView mealTitle;
            TextView mealCategory;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                mealTitle = itemView.findViewById(R.id.meal_listitem_title);
                mealCategory = itemView.findViewById(R.id.meal_listitem_category);
            }
        }


    }

}
