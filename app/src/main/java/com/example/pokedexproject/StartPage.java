package com.example.pokedexproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class StartPage extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        Button button = (Button) findViewById(R.id.button);
        Button button1 = (Button) findViewById(R.id.button2);
        Button button2 = (Button) findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchByName();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchByNumber();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchByType();
            }
        });
    }

    public void searchByName() {
        FragmentManager fm = getSupportFragmentManager();
        getPokemon gp = new getPokemon();

        fm.beginTransaction().replace(R.id.container, gp).commit();

    }

    public void searchByNumber() {
        Toast.makeText(this, "Search By Number", Toast.LENGTH_SHORT).show();
    }

    public void searchByType() {
        Toast.makeText(this, "Search By Type", Toast.LENGTH_SHORT).show();
    }
}
