package beans;

public class UserFinalStatementResult {
    private String fullname;
    private long idn;
    private int totalExamResult;
    private int certificatePoint;
    private boolean is_approved;
    private int totalResult;


    public int getTotalResult() {
        this.totalResult = totalExamResult+ certificatePoint;
        return this.totalResult;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public long getIdn() {
        return idn;
    }

    public void setIdn(long idn) {
        this.idn = idn;
    }

    public int getTotalExamResult() {
        return totalExamResult;
    }

    public void setTotalExamResult(int totalExamResult) {
        this.totalExamResult = totalExamResult;
    }

    public int getCertificatePoint() {
        return certificatePoint;
    }

    public void setCertificatePoint(int certificatePoint) {
        this.certificatePoint = certificatePoint;
    }

    public boolean isIs_approved() {
        return is_approved;
    }

    public void setIs_approved(boolean is_approved) {
        this.is_approved = is_approved;
    }
}
