package pl.jansmi.scheduler.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pl.jansmi.scheduler.App;
import pl.jansmi.scheduler.R;
import pl.jansmi.scheduler.dbstructure.entities.User;
import pl.jansmi.scheduler.dialogs.DeleteAccountPromptDialog;

public class SettingsActivity extends AppCompatActivity {
    private EditText cur_pass;
    private EditText new_pass;
    private EditText conf_pass;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.cur_pass =findViewById(R.id.settings_content_pass1);
        this.new_pass =findViewById(R.id.settings_content_pass2);
        this.conf_pass =findViewById(R.id.settings_content_pass3);

        user = App.db.users().getById(App.session.getUserId());

    }

    public void onDeleteClick(View view) {
        DeleteAccountPromptDialog.show(this, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                App.db.users().delete(App.db.users().getById(App.session.getUserId()));
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    public void onChangePasswordClick(View view) {
        if(cur_pass.getText().toString().isEmpty() || new_pass.getText().toString().isEmpty() || conf_pass.getText().toString().isEmpty())
            Toast.makeText(getApplicationContext(), "Fill all boxes", Toast.LENGTH_LONG).show();
        else if(!new_pass.getText().toString().equals(conf_pass.getText().toString()))
            Snackbar.make(view, "New password and confirm password differ.", Snackbar.LENGTH_LONG).show();
        else if(new_pass.getText().toString().length() < 5)
            Snackbar.make(view, "New password too short.", Snackbar.LENGTH_LONG).show();
        else if(!cur_pass.getText().toString().equals(user.getPassword()))
            Snackbar.make(view, "Current password is incorrect.", Snackbar.LENGTH_LONG).show();
        else if(new_pass.getText().toString().equals(cur_pass.getText().toString()))
            Snackbar.make(view, "Cannot change to current password.", Snackbar.LENGTH_LONG).show();
        else {
            user.setPassword(new_pass.getText().toString());
            App.db.users().update(user);

            cur_pass.setText("");
            new_pass.setText("");
            conf_pass.setText("");
            Toast.makeText(getApplicationContext(), "Password has been changed.", Toast.LENGTH_LONG).show();
        }
    }
}
