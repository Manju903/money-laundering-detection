
package detection;
import model.Transaction;

public class RiskAnalyzer {
    public static boolean isSuspicious(Transaction tx) {
        return tx.amount > 100000 || tx.location.equalsIgnoreCase("offshore");
    }
}
