package atec.poo.mediateca.core.exceptions;

import java.io.Serializable;

public class UserNotFoundException extends Exception implements Serializable {

    private final int id;

    public UserNotFoundException(int id) {
        this.id = id;
    }

    public int getUserID() {
        return id;
    }
}