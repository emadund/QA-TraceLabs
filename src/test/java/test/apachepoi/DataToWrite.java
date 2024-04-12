package test.apachepoi;

public class DataToWrite {

    private String testCaseId;
    private String passed;
    private String failed;
    private String overall;

    public DataToWrite(String testCaseId, String passed, String failed, String overall) {
        this.testCaseId = testCaseId;
        this.passed = passed;
        this.failed = failed;
        this.overall = overall;
    }

    public DataToWrite() {
    }

    public String getFailed() {
        return failed;
    }

    public void setFailed(String failed) {
        this.failed = failed;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getPassed() {
        return passed;
    }

    public void setPassed(String passed) {
        this.passed = passed;
    }

    public String getOverall() {
        return overall;
    }

    public void setOverall(String overall) {
        this.overall = overall;
    }
}
