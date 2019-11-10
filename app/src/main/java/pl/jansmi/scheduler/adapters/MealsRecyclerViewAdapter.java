package pl.jansmi.scheduler.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.dbstructure.entities.Category;
import pl.jansmi.scheduler.dbstructure.entities.Meal;

public class MealsRecyclerViewAdapter extends RecyclerView.Adapter<ListItemViewHolder> {

    private Context context;
    private List<Meal> meals;

    public MealsRecyclerViewAdapter(Context context, Category category) {
        this.context = context;
        this.meals = App.db.meals().getByCategoryId(category.getId());
        // TODO: sort
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
