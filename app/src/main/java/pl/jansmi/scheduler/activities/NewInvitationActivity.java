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
import android.widget.Toast;

import java.util.List;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Arrangement;
import pl.jansmi.scheduler.dbstructure.entities.Invitation;
import pl.jansmi.scheduler.dbstructure.entities.Task;
import pl.jansmi.scheduler.dbstructure.entities.User;

public class NewInvitationActivity extends AppCompatActivity {

    private final int SELECT_USERS_RC = 1;
    private final int SELECT_TASK_RC = 2;
    private User selectedUser;
    private Task selectedTask;
    private EditText user;
    private EditText task;
    private Invitation selectedInvitation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_invitation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = findViewById(R.id.new_invitation_content_select_user);
        task = findViewById(R.id.new_invitation_content_select_task);

        selectedUser = null;
        selectedTask = null;

        selectedInvitation = (Invitation) getIntent().getSerializableExtra("invite");

        if (selectedInvitation != null) {
            selectedUser = App.db.users().getById(selectedInvitation.getToId());
            user.setText(selectedUser.getName());
            selectedTask = App.db.tasks().getTaskById(selectedInvitation.getTaskId());
            task.setText(selectedTask.getName());
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedUser == null)
                    Toast.makeText(getApplicationContext(), "Choose User", Toast.LENGTH_LONG).show();
                else if (selectedTask == null)
                    Toast.makeText(getApplicationContext(), "Choose Task", Toast.LENGTH_LONG).show();
                else {
                    if (selectedInvitation != null) { // Update
                        selectedInvitation.setToId(selectedUser.getId());
                        selectedInvitation.setTaskId(selectedTask.getId());
                        selectedInvitation.setState(Invitation.STATE_SENT);
                        App.db.invitations().update(selectedInvitation);
                    } else { // Insert
                        selectedInvitation = new Invitation(App.session.getUserId(), selectedUser.getId(), selectedTask.getId());
                        App.db.invitations().insert(selectedInvitation);
                    }
                    finish();
                }
            }
        });
    }

    public void onSelectTask(View view) {
        int i = 0;
        List<Arrangement> arrs = App.db.arrangements().getByUserId(App.session.getUserId());
        for (Arrangement arr : arrs) {
            if (App.db.tasks().getByArrangementId(arr.getId()) != null) {
                i++;
                break;
            }
        }
        if (i == 0) {
            Snackbar.make(view, "You have no tasks. Add one to make invitation.", Snackbar.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(getApplicationContext(), SelectTaskActivity.class);
        intent.putExtra("task", selectedTask);
        startActivityForResult(intent, SELECT_TASK_RC);
    }

    public void onSelectUsers(View view) {
        if (App.db.users().getAll().size() == 1) {
            Snackbar.make(view, "There are no other users to send invitation to.", Snackbar.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(getApplicationContext(), SelectUsersActivity.class);
        intent.putExtra("user", selectedUser);
        startActivityForResult(intent, SELECT_USERS_RC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(RESULT_OK == resultCode) {
            switch (requestCode) {
                case SELECT_TASK_RC:
                    selectedTask = (Task) data.getSerializableExtra("task");
                    if(selectedTask != null) {
                        task.setText(selectedTask.getName());
                    }
                    break;

                case SELECT_USERS_RC:
                    selectedUser = (User) data.getSerializableExtra("user");
                    if(selectedUser != null) {
                        user.setText(selectedUser.getName());
                    }
                    break;
            }
        }
    }
}
