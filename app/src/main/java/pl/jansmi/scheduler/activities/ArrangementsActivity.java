package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.adapters.ArrangementsRecyclerViewAdapter;
import pl.jansmi.scheduler.dbstructure.entities.Arrangement;

public class ArrangementsActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private TextView infoBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recycler = findViewById(R.id.main_content_recycler);

        this.infoBox = findViewById(R.id.main_content_info_text);

        FloatingActionButton fab = findViewById(R.id.main_activity_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddArrangementActivity.class);
                intent.putExtra("arrangementId", (String) null); // no update
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter = new ArrangementsRecyclerViewAdapter(this);
        recycler.setAdapter(adapter);

        manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);

        if (adapter.getItemCount() == 0)
            infoBox.setVisibility(View.VISIBLE);
        else
            infoBox.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_meals:
                meals();
                return true;

            case R.id.action_disciplines:
                disciplines();
                return true;

            case R.id.action_subjects:
                subjects();
                return true;

            case R.id.action_profile:
                profile();
                return true;

            case R.id.action_tags:
                tags();
                return true;

            case R.id.action_settings:
                settings();
                return true;

            case R.id.action_logout:
                logout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void meals() {
        startActivity(new Intent(getApplicationContext(), MealsActivity.class));
    }

    private void disciplines() {
        startActivity(new Intent(getApplicationContext(), DisciplinesActivity.class));
    }

    private void subjects() {
        startActivity(new Intent(getApplicationContext(), SubjectsActivity.class));
    }

    private void profile() {
        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
    }

    private void tags() {
        startActivity(new Intent(getApplicationContext(), TagsActivity.class));
    }

    private void settings() {
        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
    }

    private void logout() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*if (requestCode == ADD_ARRANGEMENT_RC && resultCode == RESULT_OK) {
            // insert data to db

            // EXAMPLE OF PASSING DATA THROUGH INTENT
            Intent intent = new Intent(this, AddArrangementActivity.class);

            ContentValues content = new ContentValues();
            content.put("id", "1234");

            intent.putExtra("Key", content);

            setResult(Activity.RESULT_OK, intent);
            finish();

        }*/

    }
}
