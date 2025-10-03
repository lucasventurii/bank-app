package br.com.bank.expcetion;

public class PixInUseException extends RuntimeException {

    public PixInUseException(String message) {
        super(message);
    }


}
