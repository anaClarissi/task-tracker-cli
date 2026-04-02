package repository;

import model.entity.Task;
import model.enums.TaskStatus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskRepository {

    private static final String FILE_NAME = "tasks.json";

    private void initFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

                writer.write("[]");

            } catch (IOException e) {

                throw new RuntimeException("Error creating file: ", e);

            }

        }

    }

    public List<Task> findAll() {

        initFile();

        try {

            String content = Files.readString(Path.of(FILE_NAME));

            content = content.replace("\n", "").replace("\r", "").trim();

            if (content.equals("[]")) {

                return new ArrayList<>();

            }

            List<Task> list = new ArrayList<>();

            content = content.substring(1, content.length() - 1);

            String[] taskArray = content.split("},\\s*\\{");

            String[] fields;

            String[] keyValue;

            for (String taskString : taskArray) {

                Map<String, String> attributes = new HashMap<>();

                taskString = taskString.replace("{", "").replace("}", "").trim();

                fields = taskString.split(",(?=\\s*\"\\w+\":)");

                for (String field : fields) {

                    keyValue = field.split(":", 2);

                    String key = keyValue[0].replace("\"", "").trim();
                    String value = keyValue[1].replace("\"", "").trim();

                    attributes.put(key, value);

                }

                Task task = new Task(
                        Integer.parseInt(attributes.get("id")),
                        attributes.get("description"),
                        TaskStatus.valueOf(attributes.get("status")),
                        LocalDateTime.parse(attributes.get("createdAt")),
                        LocalDateTime.parse(attributes.get("updatedAt"))
                );

                list.add(task);

            }

            return list;

        } catch (IOException e) {

            throw new RuntimeException("Error listing file: ", e);

        }

    }

    public void saveAll(List<Task> tasks) {

        initFile();

        StringBuilder json = new StringBuilder();

        json.append("[\n");

        for (int i = 0; i < tasks.size(); i++){

            Task task = tasks.get(i);

            json.append("  {\n");
            json.append("    \"id\": ").append(task.getId()).append(",\n");
            json.append("    \"description\": \"").append(task.getDescription()).append("\",\n");
            json.append("    \"status\": \"").append(task.getStatus()).append("\",\n");
            json.append("    \"createdAt\": \"").append(task.getCreatedAt()).append("\",\n");
            json.append("    \"updatedAt\": \"").append(task.getUpdatedAt()).append("\"\n");
            json.append("  }");

            if (i < tasks.size() - 1) {

                json.append(",");

            }

            json.append("\n");

        }

        json.append("]");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            writer.write(json.toString());

        } catch (IOException e) {

            throw new RuntimeException("Error saving file: ", e);

        }

    }

}
