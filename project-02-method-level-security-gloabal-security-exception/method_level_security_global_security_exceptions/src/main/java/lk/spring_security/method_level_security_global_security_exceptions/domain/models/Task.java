package lk.spring_security.method_level_security_global_security_exceptions.domain.models;


public class Task {
    private Long taskId;
    private String taskTitle;
    private String taskDescription;
    private boolean completed;

    private Long userId;

    private User user;

    public Task(Long taskId, String taskTitle, String taskDescription, boolean completed, Long userId, User user) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.completed = completed;
        this.userId = userId;
        this.user = user;
    }

    public Long getTaskId() { return taskId; }
    public String getTaskTitle() { return taskTitle; }
    public String getTaskDescription() { return taskDescription; }
    public boolean isCompleted() { return completed; }
    public Long getUserId() { return userId; }
    public User getUser() { return user; }


}
