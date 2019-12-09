package com.haroonmasjidi.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MatchDetails extends AppCompatActivity {

    public TextView location;
    public TextView team;
    public TextView owner;
    public TextView against;
    public TextView league;
    public TextView date;
    public TextView time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        location = findViewById(R.id.location);
        team = findViewById(R.id.Team);
        against = findViewById(R.id.AgainstTeam);
        league = findViewById(R.id.league);
        date = findViewById(R.id.datelog);
        time = findViewById(R.id.timelog);

        Bundle bundle = getIntent().getExtras();
        if( bundle != null)
        {
            team.setText(bundle.getString("team"));
            against.setText(bundle.getString("against"));
            league.setText(bundle.getString("league"));
            date.setText(bundle.getString("date"));
            time.setText(bundle.getString("time"));
            location.setText(bundle.getString("location"));
        }


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item_add:
                startActivity(new Intent(MatchDetails.this, AddaMatch.class));
                return true;
            case R.id.menuitem_matches:
                startActivity(new Intent (MatchDetails.this,MainActivity.class));
                return true;
            default:
                //not recognize so invoke superclass
                return super.onOptionsItemSelected(item);
        }
    }
}
