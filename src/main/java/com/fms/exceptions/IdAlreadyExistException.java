package com.fms.exceptions;

/**
    * This Class is used to handle the exception will be raised
    * when the id of the user already present in the database.
 **/

public class IdAlreadyExistException extends RuntimeException{
    public IdAlreadyExistException(String msg)
    {
        super(msg);
    }
}
