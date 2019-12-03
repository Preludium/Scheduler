package pl.jansmi.scheduler.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.activities.TargetActivity;
import pl.jansmi.scheduler.dbstructure.entities.Target;

public class TargetsRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Target> targets;

    public TargetsRecyclerViewAdapter(Context context) {
        this.context = context;
        this.targets = App.db.targets().getAll();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
