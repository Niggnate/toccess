package com.nursoft.toccess.core.models;

import java.time.LocalDate;
import java.util.UUID;

public class Agenda {
    private String id;
    private String title;
    private String description;
    private String category;
    private LocalDate deadline;

    public Agenda() {
        id = UUID.randomUUID().toString();
        title = "";
        description = "";
        category = "";
        deadline = null;
    }

    public Agenda(String title, String description, String category, LocalDate deadline) {
        this();
        this.title = title;
        this.description = description;
        this.category = category;
        this.deadline = deadline;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDeadline() {
        return deadline;
    }
}
