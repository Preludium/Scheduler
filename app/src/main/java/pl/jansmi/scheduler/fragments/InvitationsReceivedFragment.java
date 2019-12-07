package pl.jansmi.scheduler.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.MealsRecyclerViewAdapter;
import pl.jansmi.scheduler.adapters.ReceivedInvitationsRecyclerViewAdapter;

public class InvitationsReceivedFragment extends Fragment {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private TextView infoBox;

    public InvitationsReceivedFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_invitations_received, container, false);

        this.recycler = root.findViewById(R.id.invitations_received_fragment_content_recycler);
        this.infoBox = root.findViewById(R.id.invitations_received_fragment_content_info);

        this.manager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(manager);

        this.adapter = new ReceivedInvitationsRecyclerViewAdapter(getContext());
        recycler.setAdapter(adapter);

        if (App.db.invitations().getReceivedByUserId(App.session.getUserId()).size() ==  0)
            infoBox.setVisibility(View.VISIBLE);
        else
            infoBox.setVisibility(View.INVISIBLE);

        return root;
    }
}
