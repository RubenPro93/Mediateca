package atec.poo.mediateca.core.exceptions;

public class RuleException extends Exception {

    private final int userID;
    private final int obraID;
    private final int ruleID;

    public RuleException(int userID, int obraID, int ruleID) {
        this.userID = userID;
        this.obraID = obraID;
        this.ruleID = ruleID;
    }

    public int getUserID() {
        return userID;
    }

    public int getObraID() {
        return obraID;
    }

    public int getRuleID() {
        return ruleID;
    }
}