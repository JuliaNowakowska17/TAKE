package lab.app;

public class ComplaintDTO {
    private Long id;
    private String complaintDate;
    private String complaintText;
    private String author;
    private String status;

    public Long getId() {
        return id;
    }

    public String getComplaintDate() {
        return complaintDate;
    }

    public String getComplaintText() {
        return complaintText;
    }

    public String getAuthor() {
        return author;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setComplaintDate(String complaintDate) {
        this.complaintDate = complaintDate;
    }

    public void setComplaintText(String complaintText) {
        this.complaintText = complaintText;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ComplaintDTO{" +
                "id=" + id +
                ", complaintDate='" + complaintDate + '\'' +
                ", complaintText='" + complaintText + '\'' +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}