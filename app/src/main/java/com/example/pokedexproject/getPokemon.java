package com.example.pokedexproject;

import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

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


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link getPokemon#newInstance} factory method to
 * create an instance of this fragment.
 */
public class getPokemon extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public getPokemon() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment getPokemon.
     */
    // TODO: Rename and change types and number of parameters
    public static getPokemon newInstance(String param1, String param2) {
        getPokemon fragment = new getPokemon();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        //TextView textView = (TextView) getView().findViewById(R.id.textView);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        listaPokemonAdapter = new ListaPokemonAdapter();
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getData();
    }

    private void getData() {
        PokeApiService service = retrofit.create(PokeApiService.class);
        Call<PokeRespuesta> pokeRespuestaCall = service.obtenerPokemon();

        pokeRespuestaCall.enqueue(new Callback<PokeRespuesta>() {
            @Override
            public void onResponse(Call<PokeRespuesta> call, Response<PokeRespuesta> response) {
                if (response.isSuccessful()) {
                    PokeRespuesta pokeRespuesta = response.body();
                    ArrayList<Pokemon> listaPokemon = pokeRespuesta.getResults();

                    listaPokemonAdapter.setListPokemon(listaPokemon);
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
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_pokemon, container, false);
    }
}
