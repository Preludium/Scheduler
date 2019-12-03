package pl.jansmi.scheduler.activities;

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

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.SelectUsersRecyclerViewAdapter;
import pl.jansmi.scheduler.dbstructure.entities.User;

public class SelectUsersActivity extends AppCompatActivity {

    private List<User> selectedUsers;
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
        this.selectedUsers = (List<User>) getIntent().getSerializableExtra("users");

        if(selectedUsers != null) {

        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        List<User> lista = new ArrayList<>(App.db.users().getAll());    //nie zaznacza checkboxow userow, czaruje sobie jakis nowych z nowymi ID kurwa
        adapter = new SelectUsersRecyclerViewAdapter(this, lista);
        recycler.setAdapter(adapter);
    }

}
