package pl.jansmi.scheduler.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.activities.NewInvitationActivity;
import pl.jansmi.scheduler.activities.ReceivedInvitationDecisionActivity;
import pl.jansmi.scheduler.adapters.holders.MainWithoutBinListItemViewHolder;
import pl.jansmi.scheduler.dbstructure.entities.Invitation;
import pl.jansmi.scheduler.dbstructure.entities.Task;

public class ReceivedInvitationsRecyclerViewAdapter extends RecyclerView.Adapter<MainWithoutBinListItemViewHolder> {
    private List<Invitation> invitations;
    private Context context;

    public ReceivedInvitationsRecyclerViewAdapter(Context context) {
        this.context = context;
        this.invitations = App.db.invitations().getReceivedByUserId(App.session.getUserId());
    }

    @NonNull
    @Override
    public MainWithoutBinListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listitem_main_without_bin, null);
        return new MainWithoutBinListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainWithoutBinListItemViewHolder holder, int position) {
        Invitation inv = invitations.get(position);
        Task task = App.db.tasks().getTaskById(inv.getTaskId());

        holder.title.setText(task.getName());
        String state = null;
        switch (inv.getState()) {
            case 0:
                state = "RECEIVED";
                break;
            case 1:
                state = "ACCEPTED";
                break;
            case 2:
                state = "REFUSED";
                break;
        }

        holder.desc.setText(state);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReceivedInvitationDecisionActivity.class);
                intent.putExtra("invite", inv);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
