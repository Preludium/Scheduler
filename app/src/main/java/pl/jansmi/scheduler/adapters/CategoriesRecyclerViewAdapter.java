package pl.jansmi.scheduler.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Category;

public class CategoriesRecyclerViewAdapter extends RecyclerView.Adapter<ListItemViewHolder> {

    private Context context;
    private List<Category> categories;

    public CategoriesRecyclerViewAdapter(Context context) {
        this.context = context;
        this.categories = App.db.categories().getAll();
        // TODO: sort by category order
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listitem_main, null);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.title.setText(category.getName());
        holder.desc.setText(String.valueOf(category.getOrder()));
        holder.menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirm delete");
                builder.setMessage("Are you sure you want to delete this record?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteItem(position);
                        // TODO: show infoBox, if 'categories' is empty
                        App.db.categories().delete(category);
                    }
                });
                builder.setNegativeButton("No", null);
                builder.create().show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    private void deleteItem(int position) {
        categories.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, categories.size());
        notifyDataSetChanged();
    }

}
