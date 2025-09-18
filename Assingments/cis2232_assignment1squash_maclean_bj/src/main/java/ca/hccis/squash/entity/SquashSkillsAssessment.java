package ca.hccis.squash.entity;

import ca.hccis.squash.util.CisUtility;
import com.google.gson.Gson;

public class  SquashSkillsAssessment {

    private String athleteName;
    private String assessorName; // Assessor’s name
    private String assessmentDate; // Date assessment completed (yyyy-MM-dd)

    private int forehandDrives;
    private int backhandDrives;

    private int forehandVolleyMax;
    private int forehandVolleySum;
    private int backhandVolleyMax;
    private int backhandVolleySum;

    private int technicalScore;

    // Method to gather input from user
    public void getInformation() {
        athleteName = CisUtility.getInputString("Enter athlete name: ");
        assessorName = CisUtility.getInputString("Enter assessor name: ");
        assessmentDate = CisUtility.getInputString("Enter assessment date (yyyy-MM-dd): ");
        forehandDrives = CisUtility.getInputInt("Enter forehand drives: ");
        backhandDrives = CisUtility.getInputInt("Enter backhand drives: ");
        forehandVolleyMax = CisUtility.getInputInt("Enter forehand volley max: ");
        forehandVolleySum = CisUtility.getInputInt("Enter forehand volley sum: ");
        backhandVolleyMax = CisUtility.getInputInt("Enter backhand volley max: ");
        backhandVolleySum = CisUtility.getInputInt("Enter backhand volley sum: ");

    }

    // Getters and Setters
    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public String getAssessorName() {
        return assessorName;
    }

    public void setAssessorName(String assessorName) {
        this.assessorName = assessorName;
    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(String assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public int getForehandDrives() {
        return forehandDrives;
    }

    public void setForehandDrives(int forehandDrives) {
        this.forehandDrives = forehandDrives;
    }

    public int getBackhandDrives() {
        return backhandDrives;
    }

    public void setBackhandDrives(int backhandDrives) {
        this.backhandDrives = backhandDrives;
    }

    public int getForehandVolleyMax() {
        return forehandVolleyMax;
    }

    public void setForehandVolleyMax(int forehandVolleyMax) {
        this.forehandVolleyMax = forehandVolleyMax;
    }

    public int getForehandVolleySum() {
        return forehandVolleySum;
    }

    public void setForehandVolleySum(int forehandVolleySum) {
        this.forehandVolleySum = forehandVolleySum;
    }

    public int getBackhandVolleyMax() {
        return backhandVolleyMax;
    }

    public void setBackhandVolleyMax(int backhandVolleyMax) {
        this.backhandVolleyMax = backhandVolleyMax;
    }

    public int getBackhandVolleySum() {
        return backhandVolleySum;
    }

    public void setBackhandVolleySum(int backhandVolleySum) {
        this.backhandVolleySum = backhandVolleySum;
    }

    public int getTechnicalScore() {
        return technicalScore;
    }

    public void setTechnicalScore(int technicalScore) {
        this.technicalScore = technicalScore;
    }

    // JSON Serialization using Gson
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    // ToString method for displaying all data
    @Override
    public String toString() {
        return "Athlete: " + athleteName +
                "\nAssessor: " + assessorName +
                "\nAssessment Date: " + assessmentDate +
                "\nForehand Drives: " + forehandDrives +
                "\nBackhand Drives: " + backhandDrives +
                "\nForehand Volley Max: " + forehandVolleyMax +
                "\nForehand Volley Sum: " + forehandVolleySum +
                "\nBackhand Volley Max: " + backhandVolleyMax +
                "\nBackhand Volley Sum: " + backhandVolleySum +
                "\nTechnical Score: " + technicalScore;
    }
}
