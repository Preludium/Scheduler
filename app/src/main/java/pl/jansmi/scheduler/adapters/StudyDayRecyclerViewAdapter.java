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
import pl.jansmi.scheduler.dbstructure.entities.Study;
import pl.jansmi.scheduler.dbstructure.entities.Subject;

public class StudyDayRecyclerViewAdapter extends RecyclerView.Adapter<MainListItemViewHolder> {
    private Context context;
    List<Study> selectedStudies;

        public StudyDayRecyclerViewAdapter(Context context, List<Study> selectedStudies) {
        this.context = context;
        this.selectedStudies = selectedStudies;
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
        Study study = selectedStudies.get(position);
        Subject subject = App.db.subjects().getById(study.getSubjectId());

        holder.title.setText(study.getTitle());
        holder.desc.setText(subject.getName());

        // TODO: holder.itemView listener implementation (startActivity NewStudyingActivity and pass
        //  current study for update

        // TODO: holder.menuBtn listener implementation (delete record)

    }

    @Override
    public int getItemCount() {
        return selectedStudies.size();
    }
}
