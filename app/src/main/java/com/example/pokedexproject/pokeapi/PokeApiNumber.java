package com.example.pokedexproject.pokeapi;

import com.example.pokedexproject.models.PokeRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeApiNumber {
    @GET("pokemon/{id}")
    Call<PokeRespuesta> getPokeNumber(@Path("pokemon") int pokeNumber);
}
