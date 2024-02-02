package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }
}
