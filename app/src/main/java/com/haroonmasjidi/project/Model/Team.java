package com.haroonmasjidi.project.Model;

import java.sql.Date;

public class Team {
    private int id;
    private String team;
    private String owner;
    private String against;
    private String league;
    private String location;
    private String date;
    private String time;

    public Team()
    {

    }
    public Team(int id)
    {
        this.id= id;

    }

    public Team(String team, String owner, String against, String league, String location, String date, String time) {

        this.team = team;
        this.owner = owner;
        this.against = against;
        this.league = league;
        this.location = location;
        this.date = date;
        this.time = time;
    }
    public Team(int id,String team, String owner, String against, String league, String location, String date, String time) {
        this.id = id;
        this.team = team;
        this.owner = owner;
        this.against = against;
        this.league = league;
        this.location = location;
        this.date = date;
        this.time = time;
    }


    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAgainst() {
        return against;
    }

    public void setAgainst(String against) {
        this.against = against;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
