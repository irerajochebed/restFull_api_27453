package auca.ac.rw.restfullApiAssignment.controller.taskmanagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.restfullApiAssignment.modal.taskmanagement.Task;

@RestController
@RequestMapping(value = "/api/tasks")
public class TaskController {
    List<Task> tasks = new ArrayList<>();
    
    public TaskController() {
        // Adding 3 sample example tasks
        tasks.add(new Task(1L, "Complete Spring Boot project", "Build a task management API", false, "HIGH", "2024-03-15"));
        tasks.add(new Task(2L, "Study for exam", "Prepare for final exam", false, "MEDIUM", "2024-03-20"));
        tasks.add(new Task(3L, "Buy groceries", "Weekly grocery shopping", true, "LOW", "2024-03-10"));
    }
    
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        System.out.println("Returning all tasks");
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    
    @GetMapping(value = "/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        System.out.println("Searching task by id: " + taskId);
        
        Task foundTask = null;
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                foundTask = task;
                break;
            }
        }
        
        if (foundTask != null) {
            System.out.println("Task found: " + foundTask.getTitle());
            return new ResponseEntity<>(foundTask, HttpStatus.OK);
        } else {
            System.out.println("Task not found with Id: " + taskId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping(value = "/status")
    public ResponseEntity<List<Task>> getTasksByStatus(@RequestParam boolean completed) {
        System.out.println("Searching tasks by completion status: " + completed);
        
        // Store matching tasks in this list
        List<Task> matchingTasks = new ArrayList<>();
        
        // Loop through tasks to find matches
        for (Task task : tasks) {
            if (task.isCompleted() == completed) {
                matchingTasks.add(task);
            }
        }
        
        System.out.println("Found " + matchingTasks.size() + " matching tasks");
        return new ResponseEntity<>(matchingTasks, HttpStatus.OK);
    }
    
    @GetMapping(value = "/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable String priority) {
        System.out.println("Searching tasks by priority: " + priority);
        
        // Store matching tasks in this list
        List<Task> matchingTasks = new ArrayList<>();
        
        // Loop through tasks to find matches
        for (Task task : tasks) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                matchingTasks.add(task);
            }
        }
        
        System.out.println("Found " + matchingTasks.size() + " matching tasks");
        return new ResponseEntity<>(matchingTasks, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        System.out.println("Adding new task: " + task.getTitle());
        
        // Auto-generate ID (next available ID)
        Long newId;
        if (tasks.isEmpty()) {
            newId = 1L;
        } else {
            // Get the last task's ID and add 1
            newId = tasks.get(tasks.size() - 1).getTaskId() + 1;
        }
        task.setTaskId(newId);
        
        // Add task to the list
        tasks.add(task);
        
        return new ResponseEntity<>(task, HttpStatus.CREATED);  // 201 CREATED
    }
    
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        System.out.println("Attempting to update task with ID: " + taskId);
        
        Task existingTask = null;
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                existingTask = task;
                break;
            }
        }
        
        if (existingTask != null) {
            // Update all fields
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setCompleted(updatedTask.isCompleted());
            existingTask.setPriority(updatedTask.getPriority());
            existingTask.setDueDate(updatedTask.getDueDate());
            
            return new ResponseEntity<>(existingTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> markTaskAsCompleted(@PathVariable Long taskId) {
        System.out.println("Attempting to mark task as completed with ID: " + taskId);
        
        Task existingTask = null;
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                existingTask = task;
                break;
            }
        }
        
        if (existingTask != null) {
            existingTask.setCompleted(true);
            return new ResponseEntity<>(existingTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        System.out.println("Attempting to delete task with ID: " + taskId);
        
        // Try to remove the task with matching ID
        boolean removed = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId().equals(taskId)) {
                tasks.remove(i);
                removed = true;
                break;
            }
        }
        
        // Check if task was removed
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 NO CONTENT
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 NOT FOUND
        }
    }
}