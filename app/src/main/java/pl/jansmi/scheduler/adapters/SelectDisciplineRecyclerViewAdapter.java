package pl.jansmi.scheduler.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.holders.SelectListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Discipline;

public class SelectDisciplineRecyclerViewAdapter  extends RecyclerView.Adapter<SelectListItemViewHolder> {

    private Context context;
    private List<Discipline> disciplines;
    private int selectedPosition = -1;
    private Discipline selectedDiscipline;

    public SelectDisciplineRecyclerViewAdapter(Context context) {
        this.context = context;
        this.disciplines = App.db.disciplines().getAll();
    }

    @NonNull
    @Override
    public SelectListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listitem_select, null);
        return new SelectListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectListItemViewHolder holder, int position) {
        Discipline discipline = disciplines.get(position);
        holder.title.setText(discipline.getName());
        holder.desc.setText("");
        holder.checkBox.setChecked(selectedPosition == position);
        holder.checkBox.setClickable(false);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition = position;
                selectedDiscipline = discipline;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return disciplines.size();
    }

    public Discipline getSelectedDiscipline() {
        return selectedDiscipline;
    }
}
