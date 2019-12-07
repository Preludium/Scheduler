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
import pl.jansmi.scheduler.activities.MeasureActivity;
import pl.jansmi.scheduler.activities.NewMeasureActivity;
import pl.jansmi.scheduler.adapters.holders.MainListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Measure;
import pl.jansmi.scheduler.dialogs.DeletePromptDialog;

public class MeasuresRecyclerViewAdapter extends RecyclerView.Adapter<MainListItemViewHolder> {
    private Context context;
    private List<Measure> measures;

    public MeasuresRecyclerViewAdapter(Context context) {
        this.context = context;
        this.measures = App.db.measures().getAll();
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
        Measure measure = measures.get(position);

        holder.desc.setText("");
        holder.title.setText(String.format("%d/%d/%d", measure.getDate().getDate(),  measure.getDate().getMonth() + 1,  measure.getDate().getYear()));
        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeletePromptDialog.show(context, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteItem(position);
                        App.db.measures().delete(measure);
                    }
                });
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewMeasureActivity.class);
                intent.putExtra("measure", measure);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return measures.size();
    }

    private void deleteItem(int position) {
        measures.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, measures.size());
        notifyDataSetChanged();
    }
}
