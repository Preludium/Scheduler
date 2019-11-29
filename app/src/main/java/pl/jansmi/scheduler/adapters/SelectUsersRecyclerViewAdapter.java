package pl.jansmi.scheduler.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.activities.SelectUsersActivity;
import pl.jansmi.scheduler.adapters.holders.SelectListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.User;

public class SelectUsersRecyclerViewAdapter extends RecyclerView.Adapter<SelectListItemViewHolder> {

    private Context context;
    private List<User> allUsers;
    private List<User> selectedUsers;

    public SelectUsersRecyclerViewAdapter(Context context, List<User> selectedUsers) {
        this.context = context;
        this.selectedUsers = selectedUsers;        // przepisuje userow ale ...
        this.allUsers = App.db.users().getAll();
        this.allUsers.remove(App.db.users().getById(App.session.getUserId()));  //nie usuwa aktualnie zalogowanego usera
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
        User user = allUsers.get(position);
        holder.title.setText(user.getName());
        holder.desc.setText("");

        holder.checkBox.setClickable(false);
        if(selectedUsers != null && selectedUsers.contains(user))       // nie zaznacza userow ktorzy przyszli z activity
            holder.checkBox.setChecked(true);
        else
            holder.checkBox.setChecked(false);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.checkBox.isChecked())     // wpierdala duplikaty userow z innymi ID
                    selectedUsers.remove(user);
                else
                    selectedUsers.add(user);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return allUsers.size();
    }

    public List<User> getSelectedUsers() {
        return selectedUsers;
    }
}
