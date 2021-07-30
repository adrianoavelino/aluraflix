package br.com.alura.aluraflix.validacao;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
    private final MessageSource messageSource;

    public ErroDeValidacaoHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<MensagemErro> handle(MethodArgumentNotValidException exception) {
        List<MensagemErro> mensagensErro = new ArrayList<>();

        List<FieldError> errosDosCampos = exception.getBindingResult().getFieldErrors();
        errosDosCampos.forEach(erroDoCampo -> {
            String mensagem = this.messageSource.getMessage(erroDoCampo, LocaleContextHolder.getLocale());
            MensagemErro mensagemErro = new MensagemErro(erroDoCampo.getField(), mensagem);
            mensagensErro.add(mensagemErro);
        });
        
        return mensagensErro;
    }

    @ExceptionHandler(ResourceNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public MensagemErro handleEntityNotFound(ResourceNotFound ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new MensagemErro("categoriaId", ex.getMessage());
    }
}
