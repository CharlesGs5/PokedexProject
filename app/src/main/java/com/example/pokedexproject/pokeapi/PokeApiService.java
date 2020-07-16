package com.example.pokedexproject.pokeapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.example.pokedexproject.models.PokeRespuesta;

public interface PokeApiService {

    @GET("pokemon/{pokemon}")
    Call<PokeRespuesta> obtenerPokemon(@Path("pokemon") String pokemonName);
}
