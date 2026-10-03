package lk.spring_security.stateless_jwt.domain.models;



public class Task {
    private Long taskId;
    private String taskTitle;
    private String  taskDescription;
    private boolean completed;

    private Long userId;

    public Task(Long taskId, String taskTitle, String taskDescription, boolean completed, Long userId) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.completed = completed;
        this.userId = userId;
    }

    public Long getTaskId() { return taskId; }
    public String getTaskTitle() { return taskTitle; }
    public String getTaskDescription() { return taskDescription; }
    public boolean isCompleted() { return completed; }
    public Long getUserId() { return userId; }}
