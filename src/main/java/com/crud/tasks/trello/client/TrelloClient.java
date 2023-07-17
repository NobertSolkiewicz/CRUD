package com.crud.tasks.trello.client;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;

@Component
@RequiredArgsConstructor
public class TrelloClient {

    private final RestTemplate restTemplate;

   /* HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/members/{id}/boards")
            .header("Accept", "application/json")
            .queryString("key", "APIKey")
            .queryString("token", "APIToken")
            .asJson();*/
}
