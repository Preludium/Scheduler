package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.SelectUsersRecyclerViewAdapter;
import pl.jansmi.scheduler.dbstructure.entities.User;

public class SelectUsersActivity extends AppCompatActivity {

    private User selectedUser;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_users);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.recycler = findViewById(R.id.select_users_content_recycler);
        this.selectedUser = (User) getIntent().getSerializableExtra("user");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = ((SelectUsersRecyclerViewAdapter)
                        Objects.requireNonNull(recycler.getAdapter())).getSelectedUser();
                Intent intent = new Intent(getApplicationContext(), NewInvitationActivity.class);
                intent.putExtra("user", user);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        adapter = new SelectUsersRecyclerViewAdapter(this, selectedUser);
        recycler.setAdapter(adapter);
    }

}
