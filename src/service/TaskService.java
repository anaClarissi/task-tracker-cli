package service;

import model.entity.Task;
import model.enums.TaskStatus;
import repository.TaskRepository;

import java.util.List;

public class TaskService {

    private final TaskRepository repository = new TaskRepository();

    public void add(String description) {

        List<Task> list = repository.findAll();

        int newId = list.stream().map(Task::getId).max(Integer::compareTo).orElse(0) + 1;

        Task task = new Task(newId, description);

        list.add(task);

        repository.saveAll(list);

    }

    public void update(Integer id, String description) {

        List<Task> list = repository.findAll();

        Task task = findById(list, id);

        task.setDescription(description);

        repository.saveAll(list);

    }

    public void markDone(Integer id) {

        List<Task> list = repository.findAll();

        Task task = findById(list, id);

        task.setStatus(TaskStatus.DONE);

        repository.saveAll(list);

    }

    public void markInProgress(Integer id) {

        List<Task> list = repository.findAll();

        Task task = findById(list,id);

        task.setStatus(TaskStatus.IN_PROGRESS);

        repository.saveAll(list);

    }

    public void delete(Integer id) {

        List<Task> list = repository.findAll();

        boolean removed = list.removeIf(task -> task.getId().equals(id));

        if (!removed) throw new RuntimeException("Task not found with ID: " + id);

        repository.saveAll(list);

    }

    public List<Task> list() {

        return repository.findAll();

    }

    public List<Task> listByStatus(TaskStatus status) {

        return repository.findAll().stream().filter(task -> task.getStatus() == status).toList();

    }

    private Task findById(List<Task> list,Integer id) {

        return list.stream().filter(task -> task.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));

    }

}
