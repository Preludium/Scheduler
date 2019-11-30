package pl.jansmi.scheduler.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.holders.SelectListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Tag;

public class SubmitArrangementRecyclerViewAdapter extends RecyclerView.Adapter<SelectListItemViewHolder> {

    private Context context;
    private List<Tag> tagList;
    private List<Integer> selectedTags;

    public SubmitArrangementRecyclerViewAdapter(Context context) {
        this.context = context;
        this.tagList = App.db.tags().getAll();
        this.selectedTags = new ArrayList<>();
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
        Tag tag = tagList.get(position);

        holder.title.setText(tag.getName());

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.checkBox.isChecked())
                    selectedTags.add(position);
                else
                    selectedTags.remove(new Integer(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return tagList.size();
    }
}
