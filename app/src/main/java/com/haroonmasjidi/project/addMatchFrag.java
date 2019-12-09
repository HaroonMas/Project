package com.haroonmasjidi.project;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.haroonmasjidi.project.Data.DatabaseHandler;
import com.haroonmasjidi.project.Model.Team;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class addMatchFrag extends Fragment {

    private EditText team1;
    private EditText owner;
    private EditText location;
    private EditText against;
    private CalendarView date;
    private EditText time;
    private Button submit;
    private String selected;




    public addMatchFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_match, container, false);
        // Inflate the layout for this fragment
        final Spinner spinner = view.findViewById(R.id.league_spinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
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

        submit = view.findViewById(R.id.submitBTN);

        team1 = view.findViewById(R.id.teamName);
        owner = view.findViewById(R.id.ownerName);
        location = view.findViewById(R.id.locationName);
        time = view.findViewById(R.id.timeName);
        against = view.findViewById(R.id.againstName);
        date = view.findViewById(R.id.calender);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        final String selectedDate = sdf.format(new Date(date.getDate()));





                            submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String name = team1.getText().toString();
                                     String ownerName = owner.getText().toString();
                                     String locationName = location.getText().toString();
                                     String opponentName = against.getText().toString();
                                     String timelog = time.getText().toString();

                                    if(name.length() > 0 && ownerName.length() > 0 && locationName.length() > 0 && opponentName.length() > 0 && timelog.length() > 0) {
                                        if (ownerName.matches("[a-zA-Z ]+")) {


                                            DatabaseHandler db = new DatabaseHandler(getActivity());
                                            Team team = new Team();

                                            team.setTeam(team1.getText().toString());
                                            team.setLeague(selected);
                                            team.setAgainst(against.getText().toString());
                                            team.setLocation(location.getText().toString());
                                            team.setTime(time.getText().toString());
                                            team.setDate(selectedDate);
                                            team.setOwner(owner.getText().toString());

                                            db.addTeam(team);
                                            Toast.makeText(view.getContext(), "the match has been added", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(view.getContext(), MainActivity.class);
                                            startActivity(intent);

                                        } else {
                                            Toast.makeText(view.getContext(), "ENTER ONLY ALPHABETICAL CHARACTER", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else{
                                        Toast.makeText(view.getContext(),"Please fill out all the forms",Toast.LENGTH_LONG).show();
                                    }
                                }});

        return view;
    }



    }

