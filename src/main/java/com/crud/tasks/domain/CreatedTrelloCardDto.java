package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreatedTrelloCardDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("shortUrl")
    private String shortUrl;

    public void getBadges() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode trello = objectMapper.createObjectNode();
        trello.put("board", 2);
        trello.put("card", 2);

        ObjectNode attachmentsByType = objectMapper.createObjectNode();
        attachmentsByType.put("type1", 5);
        attachmentsByType.put("type2", 10);
        attachmentsByType.set("trello", trello);

        ObjectNode badges = objectMapper.createObjectNode();
        badges.put("votes", 1);
        badges.set("attachmentsByType", attachmentsByType);
    }

}
