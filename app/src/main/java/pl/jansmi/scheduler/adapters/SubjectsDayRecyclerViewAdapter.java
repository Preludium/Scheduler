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
import pl.jansmi.scheduler.dbstructure.entities.Subject;

public class SubjectsDayRecyclerViewAdapter extends RecyclerView.Adapter<MainListItemViewHolder> {
    private Context context;
    List<Subject> subjects;

        public SubjectsDayRecyclerViewAdapter(Context context, List<String> selectedSubjects) {
        this.context = context;
        this.subjects = new ArrayList<>();
        for (String id : selectedSubjects)
            subjects.add(App.db.subjects().getById(id));
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
        Subject subject = subjects.get(position);

        holder.title.setText(subject.getName());

        holder.menuBtn.setVisibility(View.INVISIBLE);
        holder.menuBtn.setEnabled(false);

    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }
}
