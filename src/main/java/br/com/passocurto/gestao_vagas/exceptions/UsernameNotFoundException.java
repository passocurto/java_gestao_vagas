package br.com.passocurto.gestao_vagas.exceptions;

public class UsernameNotFoundException extends RuntimeException {

    public UsernameNotFoundException() {
        super("user not found");
    }

}
