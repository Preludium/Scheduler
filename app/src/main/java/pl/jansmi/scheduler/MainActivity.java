package pl.jansmi.scheduler;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_ARRANGEMENT_RC = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.main_activity_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), AddArrangementActivity.class), ADD_ARRANGEMENT_RC);
            }
        });
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

            case R.id.action_invitations:
                invitations();
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

    private void settings() {
        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
    }

    private void disciplines() {
        startActivity(new Intent(getApplicationContext(), DisciplinesActivity.class));
    }

    private void invitations() {
        startActivity(new Intent(getApplicationContext(), InvitationsActivity.class));
    }

    private void logout() {
//        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ARRANGEMENT_RC && resultCode == RESULT_OK) {
            // TODO: insert data to db

            // EXAMPLE OF PASSING DATA THROUGH INTENT
            /*Intent intent = new Intent(this, AddArrangementActivity.class);

            ContentValues content = new ContentValues();
            content.put("id", "1234");

            intent.putExtra("Key", content);

            setResult(Activity.RESULT_OK, intent);
            finish();*/

        }

    }
}
