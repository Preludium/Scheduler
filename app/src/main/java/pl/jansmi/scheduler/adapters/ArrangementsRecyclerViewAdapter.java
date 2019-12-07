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
import pl.jansmi.scheduler.adapters.holders.MainListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Arrangement;
import pl.jansmi.scheduler.dialogs.DeletePromptDialog;

public class ArrangementsRecyclerViewAdapter extends RecyclerView.Adapter<MainListItemViewHolder> {

    private Context context;
    private List<Arrangement> arrangementList;

    public ArrangementsRecyclerViewAdapter(Context context) {
        this.context = context;
        this.arrangementList = App.db.arrangements().getByUserId(App.session.getUserId());
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
        Arrangement arrangement = arrangementList.get(position);

        Date date = arrangement.getCreated();
        @SuppressLint("DefaultLocale")
        String dateHandler = String.format("%d/%d/%d", date.getDate(), date.getMonth() + 1, date.getYear()+1900);

        holder.title.setText(arrangement.getName());
        holder.desc.setText("Created: " + dateHandler);

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
                        // TODO: show infoBox, if 'arrangements' is empty
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
