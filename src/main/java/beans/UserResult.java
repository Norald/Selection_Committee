package beans;

import java.sql.Date;

public class UserResult {
    private int userId;
    private SubjectExam subject_exam;
    private int result;
    private Date dateOfExam;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public SubjectExam getSubject_exam() {
        return subject_exam;
    }

    public void setSubject_exam(SubjectExam subject_exam) {
        this.subject_exam = subject_exam;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Date getDateOfExam() {
        return dateOfExam;
    }

    public void setDateOfExam(Date dateOfExam) {
        this.dateOfExam = dateOfExam;
    }

    @Override
    public String toString() {
        return "UserResult{" +
                "userId=" + userId +
                ", subject_exam=" + subject_exam +
                ", result=" + result +
                ", dateOfExam=" + dateOfExam +
                '}';
    }
}
