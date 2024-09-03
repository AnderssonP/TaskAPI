package com.example.TaskManager_api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.TaskManager_api.Repository.TaskRepository;
import com.example.TaskManager_api.Service.TaskService;
import com.example.TaskManager_api.Task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class TaskManagerApiApplicationTests {

	@Mock
	private TaskRepository taskRepository;

	@InjectMocks
	private TaskService taskService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllTasks() {
		
		Task task1 = new Task();
		task1.setId(1L);
		task1.setTitle("Title 1");
		task1.setDescription("Description 1");
		task1.setStatus("Status 1");
		task1.setStarting(Calendar.getInstance().getTime()); // Set starting to current date

		Task task2 = new Task();
		task2.setId(2L);
		task2.setTitle("Title 2");
		task2.setDescription("Description 2");
		task2.setStatus("Status 2");
		task2.setStarting(Calendar.getInstance().getTime()); // Set starting to current date

		List<Task> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);

		when(taskRepository.findAll()).thenReturn(tasks);

		
		List<Task> result = taskService.getAllTasks();

		
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("Title 1", result.get(0).getTitle());
		assertEquals("Title 2", result.get(1).getTitle());
		assertNotNull(result.get(0).getStarting());
		assertNotNull(result.get(1).getStarting());
	}

	@Test
	public void testCreateTask() {
		
		Task task = new Task();
		task.setId(1L);
		task.setTitle("New Task");
		task.setDescription("New Description");
		task.setStatus("New Status");
		task.setStarting(Calendar.getInstance().getTime()); // Set starting to current date

		when(taskRepository.save(any(Task.class))).thenReturn(task);

		
		Task result = taskService.createTask(task);

		
		assertNotNull(result);
		assertEquals("New Task", result.getTitle());
		assertNotNull(result.getStarting());
	}

	@Test
	public void testUpdateTask() {
		
		Task existingTask = new Task();
		existingTask.setId(1L);
		existingTask.setTitle("Old Title");
		existingTask.setDescription("Old Description");
		existingTask.setStatus("Old Status");
		existingTask.setStarting(Calendar.getInstance().getTime()); // Set starting to current date

		Task updatedTask = new Task();
		updatedTask.setId(1L);
		updatedTask.setTitle("New Title");
		updatedTask.setDescription("New Description");
		updatedTask.setStatus("New Status");
		updatedTask.setStarting(existingTask.getStarting());
		updatedTask.setEnding(Calendar.getInstance().getTime()); // Set ending to current date

		when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
		when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

		
		Task result = taskService.updateTask(1L, updatedTask);

		
		assertNotNull(result);
		assertEquals("New Title", result.getTitle());
		assertEquals(existingTask.getStarting(), result.getStarting());
		assertNotNull(result.getEnding());
	}

	@Test
	public void testDeleteTask() {
		
		when(taskRepository.existsById(1L)).thenReturn(true);
		doNothing().when(taskRepository).deleteById(1L);

		
		taskService.deleteTask(1L);

		
		verify(taskRepository, times(1)).deleteById(1L);
	}

	@Test
	public void testDeleteTaskNotFound() {
		
		when(taskRepository.existsById(1L)).thenReturn(false);

		
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			taskService.deleteTask(1L);
		});

		assertEquals("Task not found -1", exception.getMessage());
	}
}
