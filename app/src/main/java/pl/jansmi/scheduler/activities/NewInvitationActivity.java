package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

import java.util.List;

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.User;

public class NewInvitationActivity extends AppCompatActivity {

    private final int SELECT_USERS_RC = 1;
    private final int SELECT_TASK_RC = 2;
    private List<User> selectedUsers;
    private EditText users;
    private EditText tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_invitation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        users = findViewById(R.id.new_invitation_content_select_user);
        tasks = findViewById(R.id.new_invitation_content_select_task);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void onSelectTask(View view) {
    }

    public void onSelectUsers(View view) {
        Intent intent = new Intent(getApplicationContext(), SelectUsersActivity.class);
        intent.putExtra("users", (User) null);
        startActivityForResult(intent, SELECT_USERS_RC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(RESULT_OK == resultCode) {
            switch (requestCode) {
                case SELECT_TASK_RC:
                    break;

                case SELECT_USERS_RC:
                    selectedUsers = (List<User>) data.getSerializableExtra("users");
                    if(selectedUsers != null) {
                        String output = "";
                        for (User user : selectedUsers) {
                            output.concat(user.getName()).concat("\n");
                        }
                        users.setText(output);
                    }
                    break;
            }
        }
    }
}
