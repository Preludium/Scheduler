package pl.jansmi.scheduler.activities;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Invitation;
import pl.jansmi.scheduler.dbstructure.entities.Task;

public class ReceivedInvitationDecisionActivity extends AppCompatActivity {

    private TextView title;
    private TextView description;
    private Task task;
    private Invitation selectedInvitation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_invitation_decision);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = findViewById(R.id.received_invitation_content_title);
        description = findViewById(R.id.received_invitation_content_description);

        selectedInvitation = (Invitation) getIntent().getSerializableExtra("invite");

        task = App.db.tasks().getTaskById(selectedInvitation.getTaskId());
        title.setText(task.getName());
        if (task.getDescription() != null)
            description.setText(task.getDescription());
    }

    public void onAcceptBtnClick(View view) {
        selectedInvitation.setState(Invitation.STATE_ACCEPTED);
        App.db.invitations().update(selectedInvitation);
        finish();
    }

    public void onRefuseBtnClick(View view) {
        selectedInvitation.setState(Invitation.STATE_REFUSED);
        App.db.invitations().update(selectedInvitation);
        finish();
    }
}
