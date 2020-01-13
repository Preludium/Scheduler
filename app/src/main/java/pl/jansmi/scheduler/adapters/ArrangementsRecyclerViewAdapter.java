package pl.jansmi.scheduler.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.activities.AddArrangementActivity;
import pl.jansmi.scheduler.adapters.holders.ArrangementListItemViewHolder;
import pl.jansmi.scheduler.adapters.holders.MainListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Arrangement;
import pl.jansmi.scheduler.dbstructure.entities.Tag;
import pl.jansmi.scheduler.dialogs.DeletePromptDialog;

public class ArrangementsRecyclerViewAdapter extends RecyclerView.Adapter<ArrangementListItemViewHolder> {

    private Context context;
    private List<Arrangement> arrangementList;

    public ArrangementsRecyclerViewAdapter(Context context) {
        this.context = context;
        this.arrangementList = App.db.arrangements().getByUserId(App.session.getUserId());
    }

    @NonNull
    @Override
    public ArrangementListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listitem_arrangement, null);
        return new ArrangementListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArrangementListItemViewHolder holder, int position) {
        Arrangement arrangement = arrangementList.get(position);

        Date date = arrangement.getCreated();
        @SuppressLint("DefaultLocale")
        String dateHandler = String.format("%d/%d/%d", date.getDate(), date.getMonth() + 1, date.getYear()+1900);

        holder.title.setText(arrangement.getName());
        holder.desc.setText("Created: " + dateHandler);

        StringBuilder tags = new StringBuilder();
        List<Tag> tagList = App.db.tags().getByArrangementId(arrangementList.get(position).getId());
        for (Tag t : tagList)
            tags.append("#").append(t.getName()).append(" ");

        holder.tags.setText(tags.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddArrangementActivity.class);
                intent.putExtra("arrangementId", arrangement.getId());
                context.startActivity(intent);
            }
        });

        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeletePromptDialog.show(context, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteItem(position);
                        App.db.arrangements().delete(arrangement);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrangementList.size();
    }

    private void deleteItem(int position) {
        arrangementList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, arrangementList.size());
        notifyDataSetChanged();
    }
}
