package com.virtualclassroom;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.logging.Logger;

public class VirtualClassroomManager {
    private static final Logger logger = Logger.getLogger(VirtualClassroomManager.class.getName());
    private static VirtualClassroomManager instance;
    private Map<String, Classroom> classrooms;

    private VirtualClassroomManager() {
        this.classrooms = new HashMap<>();
    }

    public static VirtualClassroomManager getInstance() {
        if (instance == null) {
            instance = new VirtualClassroomManager();
        }
        return instance;
    }

    public void addClassroom(String className) {
        if (!classrooms.containsKey(className)) {
            classrooms.put(className, new Classroom(className));
            logger.info("Classroom " + className + " has been created.");
        } else {
            logger.warning("Classroom " + className + " already exists.");
        }
    }

    public void addStudent(String studentId, String className) {
        Classroom classroom = classrooms.get(className);
        if (classroom != null) {
            classroom.addStudent(studentId);
            logger.info("Student " + studentId + " has been enrolled in " + className + ".");
        } else {
            logger.warning("Classroom " + className + " does not exist.");
        }
    }

    public void scheduleAssignment(String className, String assignmentDetails) {
        Classroom classroom = classrooms.get(className);
        if (classroom != null) {
            classroom.addAssignment(assignmentDetails);
            logger.info("Assignment for " + className + " has been scheduled.");
        } else {
            logger.warning("Classroom " + className + " does not exist.");
        }
    }

    public void submitAssignment(String studentId, String className, String assignmentDetails) {
        Classroom classroom = classrooms.get(className);
        if (classroom != null) {
            classroom.submitAssignment(studentId, assignmentDetails);
            logger.info("Assignment submitted by Student " + studentId + " in " + className + ".");
        } else {
            logger.warning("Classroom " + className + " does not exist.");
        }
    }

    public void listClassrooms() {
        if (classrooms.isEmpty()) {
            logger.info("No classrooms available.");
        } else {
            logger.info("List of Classrooms:");
            for (String className : classrooms.keySet()) {
                logger.info("- " + className);
            }
        }
    }

    public void listStudents(String className) {
        Classroom classroom = classrooms.get(className);
        if (classroom != null) {
            List<String> students = classroom.getStudents();
            if (students.isEmpty()) {
                logger.info("No students enrolled in " + className + ".");
            } else {
                logger.info("Students in " + className + ":");
                for (String studentId : students) {
                    logger.info("- " + studentId);
                }
            }
        } else {
            logger.warning("Classroom " + className + " does not exist.");
        }
    }

    public void listAssignments(String className) {
        Classroom classroom = classrooms.get(className);
        if (classroom != null) {
            List<String> assignments = classroom.getAssignments();
            if (assignments.isEmpty()) {
                logger.info("No assignments scheduled for " + className + ".");
            } else {
                logger.info("Assignments for " + className + ":");
                for (String assignment : assignments) {
                    logger.info("- " + assignment);
                }
            }
        } else {
            logger.warning("Classroom " + className + " does not exist.");
        }
    }
}
