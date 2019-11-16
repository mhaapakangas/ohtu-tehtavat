package ohtu;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);

        System.out.println("Players from FIN " + new Date() + "\n");
        Arrays.stream(players).filter(p -> "FIN".equals(p.getNationality()))
            .forEach(System.out::println);
    }

}
