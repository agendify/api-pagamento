package br.com.agendify.mensages;

public class CustomException extends Exception{

    private final String exceptionCode;


    public CustomException(String message, String execeptionCode){
        super(message);
        this.exceptionCode = execeptionCode;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

}
