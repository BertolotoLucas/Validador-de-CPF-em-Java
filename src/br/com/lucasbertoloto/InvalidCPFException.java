package br.com.lucasbertoloto;

public class InvalidCPFException extends Exception{
    public InvalidCPFException(String message) {
        super("CPF invalido!\n" + message);
    }
}
