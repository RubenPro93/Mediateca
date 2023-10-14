package atec.poo.mediateca.core.exceptions;

public class BorrowException extends Exception {

    private final int userID;
    private final int obraID;

    public BorrowException(int userID, int obraID) {
        this.userID = userID;
        this.obraID = obraID;
    }

    public int getUserID() {
        return userID;
    }

    public int getObraID() {
        return obraID;
    }

}