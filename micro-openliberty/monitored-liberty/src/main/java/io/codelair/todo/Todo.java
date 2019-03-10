package io.codelair.todo;

/**
 * Simple task POJO
 */
public class Todo {
    private long id;
    private String task;
    private String description;
    private boolean isFinished;

    public Todo(){

    }

    public long getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public Todo setId(long id) {
        this.id = id;
        return this;
    }

    public Todo setTask(String task) {
        this.task = task;
        return this;
    }

    public Todo setDescription(String description) {
        this.description = description;
        return this;
    }

    public Todo setFinished(boolean finished) {
        isFinished = finished;
        return this;
    }
}
