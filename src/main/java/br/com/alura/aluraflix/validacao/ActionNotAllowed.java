package br.com.alura.aluraflix.validacao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ActionNotAllowed extends  Exception {
    public ActionNotAllowed(String message) {
        super(message);
    }
}
