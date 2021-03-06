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
import pl.jansmi.scheduler.activities.AddTagActivity;
import pl.jansmi.scheduler.adapters.holders.MainListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Tag;
import pl.jansmi.scheduler.dialogs.DeletePromptDialog;

public class TagsRecyclerViewAdapter extends RecyclerView.Adapter<MainListItemViewHolder> {

    private Context context;
    private List<Tag> tags;

    public TagsRecyclerViewAdapter(Context context) {
        this.context = context;
        this.tags = App.db.tags().getAll();
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
        Tag tag = tags.get(position);

        holder.title.setText(tag.getName());
        holder.desc.setText("");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddTagActivity.class);
                intent.putExtra("tadId", tag.getId());
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
                        App.db.tags().delete(tag);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    private void deleteItem(int position) {
        tags.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, tags.size());
        notifyDataSetChanged();
    }
}
