package app;

import model.entity.Task;
import model.enums.TaskStatus;
import service.TaskService;

import java.util.Arrays;
import java.util.List;

public class Program {

    public static void main(String[] args) {

        if (args.length == 0) {

            System.out.println("No command provided.");

            return;

        }

        String command = args[0];

        try {

            TaskService service = new TaskService();

            switch (command) {

                case "add" -> {

                    if (args.length < 2) {

                        System.out.println("Description is required.");

                        return;

                    }

                    String description = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

                    Integer id = service.add(description);

                    System.out.println("[OK] Task added. (ID: " + id + ")");


                }

                case "list" -> {

                    if (args.length == 1) {

                        var tasks = service.list();

                        printTasks(tasks);

                    } else {

                        String status = args[1];

                        switch (status) {

                            case "done" -> {

                                var tasks = service.listByStatus(TaskStatus.DONE);

                                printTasks(tasks);

                            }

                            case "todo" -> {

                                var tasks = service.listByStatus(TaskStatus.TODO);

                                printTasks(tasks);

                            }

                            case "in-progress" -> {

                                var tasks = service.listByStatus(TaskStatus.IN_PROGRESS);

                                printTasks(tasks);

                            }

                            default -> System.out.println("Invalid status. Use: done, todo, in-progress");

                        }

                    }

                }

                case "update" -> {

                    if (args.length < 3) {

                        System.out.println("Usage: update <id> <description>");

                        return;

                    }

                    Integer updateId = parseId(args[1]);

                    if (updateId == null) return;

                    String description = String.join(" ", Arrays.copyOfRange(args, 2, args.length));

                    service.update(updateId, description);

                    System.out.println("Task updated.");

                }

                case "delete" -> {

                    if (args.length < 2) {

                        System.out.println("Usage: delete <id>");

                        return;

                    }

                    Integer deleteId = parseId(args[1]);

                    if (deleteId == null) return;

                    service.delete(deleteId);

                    System.out.println("Task deleted.");

                }

                case "mark-done" -> {

                    if (args.length < 2) {

                        System.out.println("Usage: mark-done <id>");

                        return;

                    }

                    Integer doneId = parseId(args[1]);

                    if (doneId == null) return;

                    service.markDone(doneId);

                    System.out.println("Task marked as done.");

                }

                case "mark-in-progress" -> {

                    if (args.length < 2) {

                        System.out.println("Usage: mark-in-progress <id>");

                        return;

                    }

                    Integer inProgressId = parseId(args[1]);

                    if (inProgressId == null) return;

                    service.markInProgress(inProgressId);

                    System.out.println("Task marked as in-progress.");

                }

                case "help" -> showHelp();

                default -> showHelp();

            }
        } catch (RuntimeException e) {

            System.out.println("Error: " + e.getMessage());

        }

    }

    private static Integer parseId(String value) {

        try {

            return Integer.parseInt(value);

        } catch (NumberFormatException e) {

            System.out.println("Invalid ID. Must be a number.");

            return null;

        }

    }

    private static void showHelp() {

        System.out.println("""
                            📌 Available commands:
                            add <description>
                            list [done|todo|in-progress]
                            update <id> <description>
                            delete <id>
                            mark-done <id>
                            mark-in-progress <id>
                            """);

    }

    private static void printTasks(List<Task> tasks) {

        if (tasks.isEmpty()) {

            System.out.println("No tasks found.");

        } else {

            tasks.forEach(System.out::println);

        }

    }

}
