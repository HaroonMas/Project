package com.haroonmasjidi.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.haroonmasjidi.project.Data.DatabaseHandler;
import com.haroonmasjidi.project.Model.Team;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateTeam extends AppCompatActivity {
    private EditText team1;
    private EditText owner;
    private EditText location;
    private EditText against;
    private EditText time;
    private Button submit;
    private String selected;
    private Bundle bundle;
    private Team team;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_team);

        submit = findViewById(R.id.submitBTN);
        team1 = findViewById(R.id.teamName);
        owner = findViewById(R.id.ownerName);
        location = findViewById(R.id.locationName);
        time = findViewById(R.id.timeName);
        against = findViewById(R.id.againstName);

        final Spinner spinner = findViewById(R.id.league_spinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.league, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view, int position, long id) {
                selected = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        team = new Team();
        bundle = getIntent().getExtras();
        if(bundle!=null){
            Gson gson = new Gson();
            team =gson.fromJson(bundle.getString("teamInfo"),Team.class);


            team1.setText(team.getTeam());
            owner.setText(team.getOwner());
            against.setText(team.getAgainst());
            location.setText(team.getLocation());
            time.setText(team.getTime());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHandler db = new DatabaseHandler(UpdateTeam.this);
                    team.setTeam(team1.getText().toString());
                    team.setAgainst(against.getText().toString());
                    team.setLocation(location.getText().toString());
                    team.setOwner(owner.getText().toString());
                    team.setTime(time.getText().toString());
                    team.setLeague(selected);
                    db.updateTeam(team);
                    Toast.makeText(view.getContext(), "the match has been modified", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    startActivity(intent);

            }
        });}




        }
    }

