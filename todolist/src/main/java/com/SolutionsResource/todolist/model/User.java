package com.SolutionsResource.todolist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String actions;
    private LocalDateTime timestamp;
    private String dayOfWeek;
    private String timeOfDay;
    private LocalDate dueDate;
    private boolean isDone;

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }


    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public User() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getActions() {
        return actions;
    }
    public void setActions(String actions) {
        this.actions = actions;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        setDayOfWeekAndTimeOfDay(timestamp);
    }
    public String getDayOfWeek() {
        return dayOfWeek;
    }
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    public String getTimeOfDay() {
        return timeOfDay;
    }
    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    // Helper method to set dayOfWeek and timeOfDay based on timestamp
    private void setDayOfWeekAndTimeOfDay(LocalDateTime timestamp) {
        this.dayOfWeek = timestamp.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
        this.timeOfDay = getTimeOfDay(timestamp);
    }

    // Helper method to determine time of day
    private String getTimeOfDay(LocalDateTime timestamp) {
        int hour = timestamp.getHour();
        if (hour >= 6 && hour < 12) {
            return "Morning";
        } else if (hour >= 12 && hour < 18) {
            return "Afternoon";
        } else if (hour >= 18 && hour < 24) {
            return "Evening";
        } else {
            return "Night";
        }
    }
}
