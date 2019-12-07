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
    private User selectedUser;

    public SelectUsersRecyclerViewAdapter(Context context, User selectedUser) {
        this.context = context;
        this.selectedUser = selectedUser;
        this.allUsers = App.db.users().getAllExceptOne(App.session.getUserId());
        if(this.selectedUser == null)
            this.selectedUser = allUsers.get(0);
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
        if(selectedUser != null && selectedUser == user)
            holder.checkBox.setChecked(true);
        else
            holder.checkBox.setChecked(false);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!holder.checkBox.isChecked())
                    selectedUser = user;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return allUsers.size();
    }

    public User getSelectedUser() {
        return selectedUser;
    }
}
