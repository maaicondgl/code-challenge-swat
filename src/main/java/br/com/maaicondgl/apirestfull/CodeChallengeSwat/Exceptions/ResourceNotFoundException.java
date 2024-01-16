package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public String VALOR_INVALIDO = " Valor de deposito Invalido";

    public String ID_CONTA_NULL = "Não foi possivel encontrar o idConta";
    private static final long serialVersionUID = 1;
    public ResourceNotFoundException(String ex){
         super(ex);
    }

}
