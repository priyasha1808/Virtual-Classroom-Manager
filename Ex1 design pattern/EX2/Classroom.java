package com.virtualclassroom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Classroom {
    private String name;
    private List<String> students;
    private List<String> assignments;
    private Map<String, List<String>> submissions;

    public Classroom(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.assignments = new ArrayList<>();
        this.submissions = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addStudent(String studentId) {
        students.add(studentId);
    }

    public List<String> getStudents() {
        return students;
    }

    public void addAssignment(String assignmentDetails) {
        assignments.add(assignmentDetails);
    }

    public List<String> getAssignments() {
        return assignments;
    }

    public void submitAssignment(String studentId, String assignmentDetails) {
        submissions.computeIfAbsent(studentId, k -> new ArrayList<>()).add(assignmentDetails);
    }

    public Map<String, List<String>> getSubmissions() {
        return submissions;
    }
}
