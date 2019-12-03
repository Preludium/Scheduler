package pl.jansmi.scheduler.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.holders.SelectListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Arrangement;
import pl.jansmi.scheduler.dbstructure.entities.Tag;

public class SubmitArrangementRecyclerViewAdapter extends RecyclerView.Adapter<SelectListItemViewHolder> {

    private Context context;
    private List<Tag> tagList;
    private List<Boolean> selectedTags;

    public SubmitArrangementRecyclerViewAdapter(Context context, Arrangement arrangement) {
        this.context = context;
        this.tagList = App.db.tags().getAll();
        this.selectedTags = new ArrayList<>(Collections.nCopies(tagList.size(), false));

        List<Tag> arrangementTags = App.db.tags().getByArrangementId(arrangement.getId());
        for (Tag t: arrangementTags) {
            for (int i=0; i<tagList.size(); ++i) {
                if (t.getId().equals(tagList.get(i).getId()))
                    selectedTags.set(i, true);
            }
        }
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

        holder.checkBox.setChecked(selectedTags.get(position));
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedTags.set(position, !selectedTags.get(position));
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tagList.size();
    }

    public List<Tag> getSelectedTags() {
        List<Tag> result = new ArrayList<>();

        for (int i=0; i<tagList.size(); ++i) {
            if (selectedTags.get(i))
                result.add(tagList.get(i));
        }

        return result;
    }
}
