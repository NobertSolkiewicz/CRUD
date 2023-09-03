package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

public class TrelloMapperTest {

    @InjectMocks
    private TrelloMapper trelloMapper;



    @Test
    public void testMapToBoards() {
        // Given
        TrelloListDto list1 = new TrelloListDto("list1", "List 1", false);
        TrelloListDto list2 = new TrelloListDto("list2", "List 2", true);

        TrelloBoardDto board1 = new TrelloBoardDto("board1", "Board 1", Arrays.asList(list1, list2));
        TrelloBoardDto board2 = new TrelloBoardDto("board2", "Board 2", null);

        List<TrelloBoardDto> trelloBoardDtos = Arrays.asList(board1, board2);

        // When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        // Then
        assertEquals(2, trelloBoards.size());
    }

    @Test
    public void testMapToBoardsDtoTest() {
        //Given
        TrelloList list1 = new TrelloList("list1", "List 1", false);
        TrelloList list2 = new TrelloList("list2", "List 2", true);

        TrelloBoard board1 = new TrelloBoard("board1", "Board 1", Arrays.asList(list1, list2));
        TrelloBoard board2 = new TrelloBoard("board2", "Board 2", null);

        List<TrelloBoard> trelloBoards = Arrays.asList(board1, board2);

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        // Then
        assertEquals(2, trelloBoardDtos.size());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto listDto1 = new TrelloListDto("list1", "List 1", false);
        TrelloListDto listDto2 = new TrelloListDto("list2", "List 2", true);

        List<TrelloListDto> trelloListDtos = Arrays.asList(listDto1, listDto2);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        //Then
        assertEquals(2, trelloLists.size());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList list1 = new TrelloList("list1", "List 1", false);
        TrelloList list2 = new TrelloList("list2", "List 2", true);

        List<TrelloList> trelloLists = Arrays.asList(list1, list2);

        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(2, trelloListDtos.size());
    }

    @Test
    public void testMapToCard() {
        TrelloCardDto trelloCardDto = new TrelloCardDto("card1", "Card 1", "top", "1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("card1", trelloCard.getName());
        assertEquals("Card 1", trelloCard.getDescription());
        assertEquals("top", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());

    }

    @Test
    public void testMapToCardDto() {
        TrelloCard trelloCard = new TrelloCard("card1", "Card 1", "top", "1");

        //When
        TrelloCardDto trelloCardto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("card1", trelloCardto.getName());
        assertEquals("Card 1", trelloCardto.getDescription());
        assertEquals("top", trelloCardto.getPos());
        assertEquals("1", trelloCardto.getListId());
    }
}
