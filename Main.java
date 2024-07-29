package com.virtualclassroom;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VirtualClassroomManager manager = VirtualClassroomManager.getInstance();
        boolean running = true;

        while (running) {
            try {
                System.out.println("Enter command:");
                String command = scanner.nextLine().trim();
                if (command.isEmpty()) {
                    continue;
                }
                String[] parts = command.split(" ", 3);

                switch (parts[0]) {
                    case "add_classroom":
                        validateParts(parts, 2);
                        manager.addClassroom(parts[1]);
                        break;
                    case "add_student":
                        validateParts(parts, 3);
                        manager.addStudent(parts[1], parts[2]);
                        break;
                    case "schedule_assignment":
                        validateParts(parts, 3);
                        manager.scheduleAssignment(parts[1], parts[2]);
                        break;
                    case "submit_assignment":
                        validateParts(parts, 3);
                        String[] subParts = parts[2].split(" ", 2);
                        if (subParts.length < 2) {
                            throw new VirtualClassroomManagerException("Invalid assignment submission details.");
                        }
                        manager.submitAssignment(parts[1], subParts[0], subParts[1]);
                        break;
                    case "list_classrooms":
                        manager.listClassrooms();
                        break;
                    case "list_students":
                        validateParts(parts, 2);
                        manager.listStudents(parts[1]);
                        break;
                    case "list_assignments":
                        validateParts(parts, 2);
                        manager.listAssignments(parts[1]);
                        break;
                    case "exit":
                        logger.info("Exiting...");
                        running = false;
                        break;
                    default:
                        logger.warning("Unknown command.");
                }
            } catch (VirtualClassroomManagerException e) {
                logger.severe("Error: " + e.getMessage());
            } catch (Exception e) {
                logger.severe("Unexpected error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void validateParts(String[] parts, int expectedLength) throws VirtualClassroomManagerException {
        if (parts.length < expectedLength) {
            throw new VirtualClassroomManagerException("Insufficient command arguments.");
        }
    }
}
