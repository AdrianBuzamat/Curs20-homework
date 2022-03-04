package ro.fasttrackit.curs20homework.exceptions;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(String msg) {
        super(msg);
    }
}
