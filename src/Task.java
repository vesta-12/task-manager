public class Task {
    private int id;
    private String title;
    private String description;
    private String status;

    public Task(int id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toFileString() {
        return id + ";" + title + ";" + description + ";" + status;
    }

    @Override
    public String toString() {
        return id + ". " + title + " (" + description + ") - " + status;
    }

    public void setTitle(String newTitle) {
    }
}