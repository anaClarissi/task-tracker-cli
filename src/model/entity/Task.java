package model.entity;

import model.enums.TaskStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task {

    private final Integer id;
    private String description;
    private TaskStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Task(Integer id, String description) {

        validateDescription(description);

        this.id = id;

        this.description = description;

        this.status = TaskStatus.TODO;

        this.createdAt = LocalDateTime.now();

        this.updatedAt = LocalDateTime.now();

    }

    public Task(Integer id, String description, TaskStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {

        this.id = id;

        this.description = description;

        this.status = status;

        this.createdAt = createdAt;

        this.updatedAt = updatedAt;

    }

    public Integer getId() {

        return id;

    }

    public String getDescription() {

        return description;

    }

    public void setDescription(String description) {

        validateDescription(description);

        this.description = description;

        touch();

    }

    public TaskStatus getStatus() {

        return status;

    }

    public void setStatus(TaskStatus status) {

        if (status == null) throw new IllegalArgumentException("Status cannot be null");

        this.status = status;

        touch();

    }

    public LocalDateTime getCreatedAt() {

        return createdAt;

    }

    public LocalDateTime getUpdatedAt() {

        return updatedAt;

    }

    private void touch() {

        this.updatedAt = LocalDateTime.now();

    }

    private void validateDescription(String description) {

        if (description == null || description.isBlank()) {

            throw new IllegalArgumentException("Description cannot be empty");

        }

    }

    @Override
    public boolean equals(Object object) {

        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        Task task = (Task) object;

        return Objects.equals(id, task.id);

    }

    @Override
    public int hashCode() {

        return Objects.hash(id);

    }

    @Override
    public String toString() {
        return String.format(
                "[%d] %s | %s | Criado: %s | Atualizado: %s"
                , id
                , description
                , status
                , FORMATTER.format(createdAt)
                , FORMATTER.format(updatedAt)
        );
    }
}
