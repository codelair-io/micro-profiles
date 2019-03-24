package codelair.health.event;

public class TodoServiceStartUpEvent {
    private boolean isStarted;

    public TodoServiceStartUpEvent(boolean didStart) {
        this.isStarted = didStart;
    }

    public boolean isStarted() {
        return isStarted;
    }
}
