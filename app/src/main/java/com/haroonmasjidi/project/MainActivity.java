package com.haroonmasjidi.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.haroonmasjidi.project.Adapter.RecyclerViewAdapter;
import com.haroonmasjidi.project.Data.DatabaseHandler;
import com.haroonmasjidi.project.Model.Team;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Team> teamArrayList;
    private ArrayAdapter<String> arrayAdapter;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private final int REQUEST_CODE_DISPLAY = 5;
    private EditText edittext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext = findViewById(R.id.search);
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());

            }
        });

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);


        //Team team = new Team();
        teamArrayList = new ArrayList<>();
/*
      db.addTeam(new Team("PSG", "Muhammad Al Jaazeer", "Leon", "Ligue 1", "Paris Stadium", "2019-12-05","3:45 pm"));
       db.addTeam(new Team("Manchester United", "Michael Knighton", "Chelsea", "Premier League", "Chelsea Stadium", "2019-12-06","2:45 pm"));
      db.addTeam(new Team("Juventus", "Mario Bros", "Inter Milan", "Serie A", "Juventus Stadium", "2019-12-07","3:00 pm"));
        db.addTeam(new Team("Milan", "Pizzaria ", "Liverpool", "Champions League", "Milan Stadium", "2019-12-05","1:45 pm"));
        db.addTeam(new Team("Manchester United", "Michael Knighton ", "Manchester City", "Premier league", "Etihad Stadium", "2019-12-04","1:45 pm"));
       db.addTeam(new Team("Barcelona", "Christiano Ronaldo ", "Real Madrid", "La Liga", "Barca Stadium", "2019-12-01","1:45 pm"));
        db.addTeam(new Team("Liverpool", "Lebron", "Barcelona", "Champions League", "Paris Stadium", "2019-12-08","3:45 pm"));
*/

// db.deleteTeam(new Team(20));
//        db.updateTeam(new Team(16,"Milan", "Pizzaria ", "Liverpool", "Champions League", "Milan Stadium", "2019-12-05","4:45 pm"));
//        db.updateTeam(new Team(17,"Chelsea", "Rampararain", "Manchester City", "Premier league", "Etihad Stadium", "2020-01-10","1:45 pm" ));
//        db.updateTeam(new Team(18,"Manchester United", "Michael Knighton ", "Manchester City", "Champions league", "Etihad Stadium", "2019-12-30","2:45 pm"));

        final List<Team> teamList = db.getAllTeams();

       for(Team team: teamList)
        {
            teamArrayList.add(new Team(team.getTeam(),team.getOwner(),team.getAgainst(),team.getLeague(),team.getLocation(),team.getDate(),team.getTime()));
        }


        recyclerView = findViewById(R.id.recycleTeam);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, teamList);
        recyclerView.setAdapter(recyclerViewAdapter);



    }

    private void filter(String text)
    {
        ArrayList<Team> filteredList = new ArrayList<>();

        for(Team item : teamArrayList){
            if(item.getTeam().toLowerCase().contains(text.toLowerCase()) || item.getAgainst().toLowerCase().contains(text.toLowerCase()) || item.getLeague().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);

            }
        }
        recyclerViewAdapter.filterList(filteredList);
    }


        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.options_menu, menu);
            return true;

        }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item_add:
                startActivity(new Intent (MainActivity.this, AddaMatch.class));
                return true;
            case R.id.menuitem_matches:
                startActivity(new Intent (MainActivity.this,MainActivity.class));
                return true;
            default:
                //not recognize so invoke superclass
                return super.onOptionsItemSelected(item);
        }
    }


}

