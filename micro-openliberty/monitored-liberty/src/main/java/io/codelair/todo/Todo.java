package io.codelair.todo;

/**
 * Simple task POJO
 */
public class Todo {
    private long id;
    private String task;
    private String description;
    private boolean isFinished;

    private Todo(){

    }

    private Todo(Builder builder) {
        id = builder.id;
        task = builder.task;
        description = builder.description;
        isFinished = builder.isFinished;
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

    // Builder
    public static class Builder{
        private long id;
        private String task;
        private String description;
        private boolean isFinished;

        // Build
        public Todo build(){
            return new Todo(this);
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setTask(String task) {
            this.task = task;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setFinished(boolean finished) {
            isFinished = finished;
            return this;
        }
    }
}
