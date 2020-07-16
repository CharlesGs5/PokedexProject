package com.example.pokedexproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pokedexproject.Adapters.ListaPokemonAdapter;
import com.example.pokedexproject.models.PokeRespuesta;
import com.example.pokedexproject.models.Pokemon;
import com.example.pokedexproject.pokeapi.PokeApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class SearchByName extends AppCompatActivity {

    Button myBtn;
    EditText myEditText;
    Retrofit retrofit;
    ListaPokemonAdapter listaPokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name);

        myBtn = (Button)findViewById(R.id.button4);
        myEditText = (EditText)findViewById(R.id.editText);
        listaPokemonAdapter = new ListaPokemonAdapter();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }

        });
    }

    private void getData() {
        String myText = myEditText.getText().toString();
        PokeApiService service = retrofit.create(PokeApiService.class);
        Call<PokeRespuesta> pokeRespuestaCall = service.obtenerPokemon(myText);

        pokeRespuestaCall.enqueue(new Callback<PokeRespuesta>() {
            @Override
            public void onResponse(Call<PokeRespuesta> call, Response<PokeRespuesta> response) {
                if (response.isSuccessful()) {
                    PokeRespuesta pokeRespuesta = response.body();
                    ArrayList<Pokemon> listaPokemon = pokeRespuesta.getResults();

                    //listaPokemonAdapter.setListPokemon(listaPokemon);
                    //Log.v(TAG, "Pokemon: " + response.toString());
                    Toast.makeText(getApplicationContext(), "Pokemon " + response.toString(), Toast.LENGTH_LONG).show();
                    /*for (int i = 0; i < listaPokemon.size(); i++) {
                        Pokemon pokemon = listaPokemon.get(i);
                        Log.i(TAG, " Pokemon: " + pokemon.getName());
                    }*/

                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokeRespuesta> call, Throwable t) {
                Log.e(TAG, " onFailure: "+ t.getMessage());
                Toast.makeText(getApplicationContext(), "Error!" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
