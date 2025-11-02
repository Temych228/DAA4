package org.example.app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GraphRunner runner = new GraphRunner();
        Scanner sc = new Scanner(System.in);

        String teacherFile = "tasks.json";
        String[] studentFiles = {
                "data/small1.json","data/small2.json","data/small3.json",
                "data/medium1.json","data/medium2.json","data/medium3.json",
                "data/large1.json","data/large2.json","data/large3.json"
        };

        System.out.println("=== Graph Analysis Menu ===");
        System.out.println("1) Run 'tasks' dataset");
        System.out.println("2) Run all my datasets (9)");
        System.out.println("3) Choose one of my datasets");
        System.out.print("Choose (1/2/3): ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1 -> runner.run(teacherFile);
            case 2 -> {
                for (String f : studentFiles) runner.run(f);
            }
            case 3 -> {
                System.out.println("--- Available student datasets ---");
                for (int i = 0; i < studentFiles.length; i++) {
                    System.out.println((i + 1) + ") " + studentFiles[i]);
                }
                System.out.print("Select dataset number: ");
                int idx = sc.nextInt();
                if (idx >= 1 && idx <= studentFiles.length) {
                    runner.run(studentFiles[idx - 1]);
                } else {
                    System.out.println("Invalid index");
                }
            }
            default -> System.out.println("Invalid choice.");
        }
    }
}
