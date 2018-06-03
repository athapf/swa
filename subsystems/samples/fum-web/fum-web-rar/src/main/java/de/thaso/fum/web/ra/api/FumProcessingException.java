package de.thaso.fum.web.ra.api;

public class FumProcessingException extends RuntimeException {

    private String number;

    public FumProcessingException(final String number, final String message) {
        super(message);
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
