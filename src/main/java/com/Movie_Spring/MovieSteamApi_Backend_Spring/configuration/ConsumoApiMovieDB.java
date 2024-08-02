package com.Movie_Spring.MovieSteamApi_Backend_Spring.configuration;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApiMovieDB {
    public String obtenerDatosApi(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1MDYxMjYyN2FhNzljZWI1MGZlMTYyMGRhMWZhNjVjMCIsIm5iZiI6MTcyMjMwMjMzNi40NDQ3ODQsInN1YiI6IjY2NGQyNjhjMGQzOGE1ZDc4Mzk5MWVkMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.XWX1aW-t0b7Lp4Y1ZahhclOxqaIuM3TYyDc6EdsfApc")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(response.body());
        //String json = response.body();
        //return json;
        return response.body();
    }

}
