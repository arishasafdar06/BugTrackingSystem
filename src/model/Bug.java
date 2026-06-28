package model;

public class Bug {

    private int bugId;
    private int projectId;
    private String title;
    private String description;
    private String severity;
    private String status;
    private int reportedBy;
    private int assignedTo;

    public Bug() {
    }

    public Bug(int bugId, int projectId, String title, String description,
               String severity, String status, int reportedBy, int assignedTo) {
        this.bugId = bugId;
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.status = status;
        this.reportedBy = reportedBy;
        this.assignedTo = assignedTo;
    }

    public int getBugId() {
        return bugId;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSeverity() {
        return severity;
    }

    public String getStatus() {
        return status;
    }

    public int getReportedBy() {
        return reportedBy;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public void setBugId(int bugId) {
        this.bugId = bugId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReportedBy(int reportedBy) {
        this.reportedBy = reportedBy;
    }

    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public String toString() {
        return bugId + " | " + title + " | " + severity + " | " + status +
                " | Reported By: " + reportedBy + " | Assigned To: " + assignedTo;
    }
}
