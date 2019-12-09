package com.haroonmasjidi.project.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.haroonmasjidi.project.Model.Team;
import com.haroonmasjidi.project.R;
import com.haroonmasjidi.project.Util.LeagueUtil;

import java.util.ArrayList;
import java.util.List;

import static com.haroonmasjidi.project.Util.LeagueUtil.KEY_DATE;
import static com.haroonmasjidi.project.Util.LeagueUtil.KEY_ID;
import static com.haroonmasjidi.project.Util.LeagueUtil.KEY_LEAGUE;
import static com.haroonmasjidi.project.Util.LeagueUtil.KEY_LOCATION;
import static com.haroonmasjidi.project.Util.LeagueUtil.KEY_TEAM_AGAINST;
import static com.haroonmasjidi.project.Util.LeagueUtil.KEY_TEAM_NAME;
import static com.haroonmasjidi.project.Util.LeagueUtil.KEY_TEAM_OWNER;
import static com.haroonmasjidi.project.Util.LeagueUtil.KEY_TIME;
import static com.haroonmasjidi.project.Util.LeagueUtil.TABLE_NAME;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, LeagueUtil.DATABASE_NAME, null, LeagueUtil.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LEAGUE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_TEAM_NAME + " TEXT, "
                + KEY_TEAM_OWNER + " TEXT, "
                + KEY_TEAM_AGAINST + " TEXT, "
                + KEY_LEAGUE + " TEXT,"
                + KEY_LOCATION + " TEXT, "
                + KEY_DATE + " DATE, "
                + KEY_TIME + " TEXT)";
        db.execSQL(CREATE_LEAGUE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE = String.valueOf(R.string.db_drop);
        db.execSQL(DROP_TABLE, new String []{LeagueUtil.DATABASE_NAME});

        onCreate(db);

    }

    public void addTeam(Team team)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEAM_NAME, team.getTeam());
        values.put(KEY_TEAM_OWNER, team.getOwner());
        values.put(KEY_TEAM_AGAINST, team.getAgainst());
        values.put(KEY_LEAGUE, team.getLeague());
        values.put(KEY_LOCATION, team.getLocation());
        values.put(KEY_DATE, team.getDate());
        values.put(KEY_TIME, team.getTime());

        db.insert(TABLE_NAME, null, values);
        Log.d("test", "addTeam: " + "item added");
        db.close();
    }

    public void deleteTeam(Team team)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(team.getId())});
        db.close();
    }

    public boolean updateTeam(Team team){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID,team.getId());
        values.put(KEY_TEAM_NAME, team.getTeam());
        values.put(KEY_TEAM_OWNER, team.getOwner());
        values.put(KEY_TEAM_AGAINST, team.getAgainst());
        values.put(KEY_LEAGUE, team.getLeague());
        values.put(KEY_LOCATION, team.getLocation());
        values.put(KEY_DATE, team.getDate());
        values.put(KEY_TIME, team.getTime());

        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[]{String.valueOf(team.getId())});
        return true;
    }
    public  Team getTeam(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, LeagueUtil.KEY_TEAM_NAME, LeagueUtil.KEY_TEAM_OWNER, LeagueUtil.KEY_TEAM_AGAINST, LeagueUtil.KEY_LEAGUE, LeagueUtil.KEY_LOCATION, LeagueUtil.KEY_DATE, LeagueUtil.KEY_TIME},
                KEY_ID+ "=?", new String[]{String.valueOf(id)}, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
            Team team = new Team();

            team.setId(Integer.parseInt(cursor.getString(0)));
            team.setTeam(cursor.getString(1));
            team.setOwner(cursor.getString(2));
            team.setAgainst(cursor.getString(3));
            team.setLeague(cursor.getString(4));
            team.setLocation(cursor.getString(5));
            team.setDate(cursor.getString(6));
            team.setTime(cursor.getString(7));

            return team;
        }
        else{
            return null;
        }
    }
    public Team getTeam(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE" + KEY_TEAM_AGAINST + " = '" + name + "' OR " + KEY_TEAM_NAME + " ='" + name + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            cursor.moveToFirst();
            Team team = new Team();

            team.setId(Integer.parseInt(cursor.getString(0)));
            team.setTeam(cursor.getString(1));
            team.setOwner(cursor.getString(2));
            team.setAgainst(cursor.getString(3));
            team.setLeague(cursor.getString(4));
            team.setLocation(cursor.getString(5));
            team.setDate(cursor.getString(6));
            team.setTime(cursor.getString(7));

            return team;
        } else {
            return null;

        }
    }

    public List<Team> teamList = new ArrayList<>();

    public List<Team> getAllTeams(){
        List<Team> teamList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM " + TABLE_NAME+ " ORDER BY " +  KEY_DATE +" ASC";
        Cursor cursor = db.rawQuery(selectAll, null);

        if(cursor.moveToFirst())
        {
            do{
                Team team = new Team();

                team.setId(Integer.parseInt(cursor.getString(0)));
                team.setTeam(cursor.getString(1));
                team.setOwner(cursor.getString(2));
                team.setAgainst(cursor.getString(3));
                team.setLeague(cursor.getString(4));
                team.setLocation(cursor.getString(5));
                team.setDate(cursor.getString(6));
                team.setTime(cursor.getString(7));

                teamList.add(team);
            }while (cursor.moveToNext());
        }
        db.close();
        return teamList;
    }
}
