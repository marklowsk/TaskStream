package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing deadlines");
        printDeadlines(tasksData);
        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        printDeadlinesUsingStreams(tasksData);

        System.out.println("Filter tasks by String");
        printData(filterTaskByString(tasksData, "11"));
        
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    private static int countDeadlinesWithStreams(ArrayList<Task> tasksData) {
        int count = (int) tasksData.stream()
                                   .filter(t -> t instanceof Deadline)
                                   .count();
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataWithStreams(ArrayList<Task> taskData) {
        System.out.println("Printing tasks using streams.");
        taskData.stream()
                .forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStreams(ArrayList<Task> tasksData) {
        tasksData.stream()
                 .filter(s -> s instanceof Deadline)
                 .sorted((a, b) -> a.getDescription().toLowerCase()
                                    .compareTo(b.getDescription().toLowerCase()))
                 .forEach(System.out::println);
    }

    public static ArrayList<Task> filterTaskByString(ArrayList<Task> tasksData, String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>)
                tasksData.stream()
                         .filter(s -> s.getDescription().contains(filterString))
                         .collect(toList());
        return filteredList;

    }
}
