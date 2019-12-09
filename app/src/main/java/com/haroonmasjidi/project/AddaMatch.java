package com.haroonmasjidi.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class AddaMatch extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        addMatchFrag teamFrag = new addMatchFrag();
        ft.add(R.id.addFrag, teamFrag);
        ft.commit();



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item_add:
                startActivity(new Intent(AddaMatch.this, AddaMatch.class));
                return true;
            case R.id.menuitem_matches:
                startActivity(new Intent (AddaMatch.this,MainActivity.class));
                return true;
            default:
                //not recognize so invoke superclass
                return super.onOptionsItemSelected(item);
        }
    }
}
