package com.example.pokedexproject.pokeapi;

import retrofit2.Call;
import retrofit2.http.GET;

import com.example.pokedexproject.models.PokeRespuesta;

public interface PokeApiService {

    @GET("pokemon")
    Call<PokeRespuesta> obtenerPokemon();
}
