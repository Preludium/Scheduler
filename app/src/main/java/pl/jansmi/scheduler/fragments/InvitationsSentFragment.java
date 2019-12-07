package pl.jansmi.scheduler.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.SentInvitationsRecyclerViewAdapter;


public class InvitationsSentFragment extends Fragment {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private TextView infoBox;

    public InvitationsSentFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_invitations_sent, container, false);

        this.recycler = root.findViewById(R.id.invitations_sent_fragment_content_recycler);
        this.infoBox = root.findViewById(R.id.invitations_sent_fragment_content_info);

        this.manager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(manager);

        this.adapter = new SentInvitationsRecyclerViewAdapter(getContext());
        recycler.setAdapter(adapter);

        if (App.db.invitations().getSentByUserId(App.session.getUserId()).size() ==  0)
            infoBox.setVisibility(View.VISIBLE);
        else
            infoBox.setVisibility(View.INVISIBLE);

        return root;
    }
}
