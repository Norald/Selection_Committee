package comparator;

import beans.UserFinalStatementResult;

import java.util.Comparator;

public class TotalResultComparator implements Comparator<UserFinalStatementResult> {
    @Override
    public int compare(UserFinalStatementResult o1, UserFinalStatementResult o2) {
        return -o1.getTotalResult() + o2.getTotalResult();
    }
}
