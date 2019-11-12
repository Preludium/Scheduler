package pl.jansmi.scheduler.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.activities.AddDisciplineActivity;
import pl.jansmi.scheduler.dbstructure.entities.Discipline;
import pl.jansmi.scheduler.dialogs.DeletePromptDialog;

public class DisciplinesRecyclerViewAdapter extends RecyclerView.Adapter<MainListItemViewHolder> {

    private Context context;
    private List<Discipline> disciplines;

    public DisciplinesRecyclerViewAdapter(Context context) {
        this.context = context;
        this.disciplines = App.db.disciplines().getAll();
        // TODO: sort by discipline favour
    }

    @NonNull
    @Override
    public MainListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listitem_main, null);
        return new MainListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainListItemViewHolder holder, int position) {
        Discipline discipline = disciplines.get(position);

        holder.title.setText(discipline.getName());
        holder.desc.setText("Kcal: " + String.valueOf(discipline.getKcalPerMinute()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddDisciplineActivity.class);
                intent.putExtra("disciplineId", discipline.getId());
                context.startActivity(intent);
            }
        });

        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DeletePromptDialog.show(context, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteItem(position);
                        // TODO: show infoBox, if 'disciplines' is empty
                        App.db.disciplines().delete(discipline);
                    }
                });

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
