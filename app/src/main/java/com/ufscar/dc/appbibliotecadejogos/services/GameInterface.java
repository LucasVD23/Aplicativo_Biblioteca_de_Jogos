package com.ufscar.dc.appbibliotecadejogos.services;

import com.ufscar.dc.appbibliotecadejogos.models.Game;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GameInterface {

    String token = "uq3p45d39n1x1hj620yxymzulr3u32";

    @Headers({
            "Client-ID: 2f0ln0qc2lt8z0a6viczwbyrp585re",
            "Content-Type: text/plain",
            "Authorization: Bearer " + token,
            "Accept: application/json"
    })
    @POST("games")
    Call<List<Game>> searchGame(@Body String fields);

    @Headers({
            "Client-ID: 2f0ln0qc2lt8z0a6viczwbyrp585re",
            "Content-Type: text/plain",
            "Authorization: Bearer " + token,
            "Accept: application/json"
    })
    @POST("games")
    Call<List<Game>> searchRecommends(@Body String fields);
}
