package pl.jansmi.scheduler.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Subject;

public class NewStudyRecyclerViewAdapter extends RecyclerView.Adapter<SelectListItemViewHolder> {

    private Context context;
    private List<Subject> subjects;
    private Subject selectedSubject;

    private int lastCheckedPosition = -1;

    public NewStudyRecyclerViewAdapter(Context context, Subject selectedSubject) {
        this.context = context;
        this.selectedSubject = selectedSubject;
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
        Subject subject = subjects.get(position);

        holder.title.setText(subject.getName());

        if (subject.equals(selectedSubject))
            lastCheckedPosition = position;

        holder.checkBox.setChecked(position == lastCheckedPosition);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyItemChanged(lastCheckedPosition);

                if (position == lastCheckedPosition) {
                    lastCheckedPosition = -1;
                    selectedSubject = null;

                } else {
                    lastCheckedPosition = position;
                    selectedSubject = subject;

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public Subject getSelectedSubject() {
        return selectedSubject;
    }
}
