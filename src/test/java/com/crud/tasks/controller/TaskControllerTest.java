package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @Mock
    private DbService dbService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void shouldGetTaskEmptyList() throws Exception {
        // Given
        List<Task> tasks = List.of(new Task(1L, "Test", "Test description"));
        List<TaskDto> tasksList = List.of(new TaskDto(1L, "Test list", "Test Content"));

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksList);
        when(taskController.getTasks());

        //When & Then
        mockMvc
                .perform(get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").doesNotExist());
    }

    @Test
    void shouldGetTask() throws Exception {
        //Given
        Long taskId = 1L;
        Task task = new Task(taskId, "Test Task", "Test Content");
        TaskDto taskDto = new TaskDto(taskId, "Test Task", "Test Content");

        when(dbService.getTask(taskId)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(taskController.getTasks());

        //When & Then
        mockMvc.perform(get("/v1/tasks/{taskId}", taskId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(taskId.intValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Task"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("Test Content"));
    }

    @Test
    void shouldDeleteTask() throws Exception {
        //Given
        Long taskId = 1L;
        List<Task> tasks = new ArrayList<>();
        Task task = new Task(taskId, "Test Task", "Test Content");
        tasks.add(task);

        when(taskController.deleteTask(task.getId()));

        //When & Then
        mockMvc.perform(delete("/v1/tasks/{taskId}", taskId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldPostTask() throws Exception {
        Long taskId = 1L;
        TaskDto taskDto = new TaskDto(taskId, "Test Task", "Test Content");

        when(taskController.createTask(taskDto));


        //When & Then
        mockMvc.perform(post("/v1/tasks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldPutTask() throws Exception {
        Long taskId = 1L;
        Task task = new Task(taskId, "Test Task", "Test Content");
        TaskDto taskDto = new TaskDto(taskId, "Test Task", "Test Content");

        when(dbService.getTask(taskId)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(taskController.updateTask(taskDto));

        //When & Then
        mockMvc.perform(put("/v1/tasks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }
}