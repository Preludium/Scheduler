package pl.jansmi.scheduler.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.Invitation;
import pl.jansmi.scheduler.fragments.InvitationsReceivedFragment;
import pl.jansmi.scheduler.fragments.InvitationsSentFragment;

public class InvitationsActivity extends AppCompatActivity {

    private static final int SENT_RC = 1;
    private static final int RECV_RC = 2;

    private BottomNavigationView navView;
    private int selectedFragment;

    InvitationsReceivedFragment receivedFragment;
    InvitationsSentFragment sentFragment;

    private List<Invitation> sentInvitations;
    private List<Invitation> receivedInvitations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitations);

        selectedFragment = RECV_RC;
        navView = findViewById(R.id.invitations_activity_nav_view);
        navView.setSelectedItemId(R.id.navigation_received);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_received:
                        selectedFragment = RECV_RC;
                        return loadFragment(receivedFragment);
                    case R.id.navigation_sent:
                        selectedFragment = SENT_RC;
                        return loadFragment(sentFragment);
                }
                return false;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewInvitationActivity.class);
                intent.putExtra("invite", (Invitation) null);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // init fragments
        this.receivedFragment = new InvitationsReceivedFragment();
        this.sentFragment = new InvitationsSentFragment();

        // set starting fragment
        switch (selectedFragment) {
            case RECV_RC:
                navView.setSelectedItemId(R.id.navigation_received);
                loadFragment(receivedFragment);
                break;
            case SENT_RC:
                navView.setSelectedItemId(R.id.navigation_sent);
                loadFragment(sentFragment);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.invitation_activity_fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RECV_RC:
                    break;

                case SENT_RC:
                    break;
            }
        }
    }

}
