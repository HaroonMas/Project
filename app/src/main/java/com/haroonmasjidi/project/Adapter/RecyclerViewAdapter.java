package com.haroonmasjidi.project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.haroonmasjidi.project.AddaMatch;
import com.haroonmasjidi.project.Data.DatabaseHandler;
import com.haroonmasjidi.project.MainActivity;
import com.haroonmasjidi.project.MatchDetails;
import com.haroonmasjidi.project.Model.Team;
import com.haroonmasjidi.project.R;
import com.haroonmasjidi.project.UpdateTeam;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Team> teamList;
    private Team t;


    public RecyclerViewAdapter(Context context, List<Team> contactList) {
        this.context = context;
        this.teamList = contactList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.league_match,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Team t= teamList.get(position);

        holder.Team.setText(t.getTeam());
        holder.against.setText(t.getAgainst());
        holder.date.setText(t.getDate());
        holder.league.setText(t.getLeague());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent = new Intent(context, MatchDetails.class);
                intent.putExtra("team", t.getTeam());
                intent.putExtra("against", t.getAgainst());
                intent.putExtra("league", t.getLeague());
                intent.putExtra("location", t.getLocation());
                intent.putExtra("date", t.getDate());
                intent.putExtra("time", t.getTime());
                context.startActivity(intent);

            }
        });



        holder.updateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateTeam.class);
                Gson gson = new Gson();
                String teamString = gson.toJson(t);
                intent.putExtra("teamInfo", teamString);
                context.startActivity(intent);


            }
        });

        holder.removeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(context);
                db.deleteTeam(t);

                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent    );
            }
        });

    }

    @Override
    public int getItemCount() { return teamList.size();}

    public void filterList(ArrayList<Team> filteredList)
    {
        teamList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView Team;
            public TextView against;
            public TextView date;
            public TextView league;
            public TextView time;
            public TextView location;
            public TextView owner;
            public CardView cardView;

            private Button updateBTN;
            private Button removeBTN;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            Team= itemView.findViewById(R.id.Team);
            against = itemView.findViewById(R.id.AgainstTeam);
            time = itemView.findViewById(R.id.timelog);
            date = itemView.findViewById(R.id.datelog);
            league = itemView.findViewById(R.id.league);
            location = itemView.findViewById(R.id.location);
            cardView = itemView.findViewById(R.id.cardId);
            removeBTN= itemView.findViewById(R.id.removeBTN);
            updateBTN= itemView.findViewById(R.id.updateBTN);



        }

    }


}
