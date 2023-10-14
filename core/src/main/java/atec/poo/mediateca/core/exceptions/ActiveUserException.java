package atec.poo.mediateca.core.exceptions;

public class ActiveUserException extends Exception {

    private final int userID;

    public ActiveUserException(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }
}