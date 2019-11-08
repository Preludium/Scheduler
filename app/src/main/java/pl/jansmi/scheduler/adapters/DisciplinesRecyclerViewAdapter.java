package pl.jansmi.scheduler.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.activities.DisciplinesActivity;
import pl.jansmi.scheduler.dbstructure.entities.Discipline;

public class DisciplinesRecyclerViewAdapter extends RecyclerView.Adapter<ListItemViewHolder> {

    private Context context;
    private List<Discipline> disciplines;

    public DisciplinesRecyclerViewAdapter(Context context) {
        this.context = context;
        this.disciplines = App.db.disciplines().getAll();
        // TODO: sort by discipline order
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listitem_main, null);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        Discipline discipline = disciplines.get(position);
        holder.title.setText(discipline.getName());
        holder.desc.setText(String.valueOf(discipline.getKcalPerMinute()));
        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirm delete");
                builder.setMessage("Are you sure you want to delete this record?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteItem(position);
                        // TODO: show infoBox, if 'disciplines' is empty
                        App.db.disciplines().delete(discipline);
                    }
                });
                builder.setNegativeButton("No", null);
                builder.create().show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return disciplines.size();
    }

    private void deleteItem(int position) {
        disciplines.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, disciplines.size());
        notifyDataSetChanged();
    }
}
