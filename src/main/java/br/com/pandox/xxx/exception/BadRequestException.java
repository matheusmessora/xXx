package br.com.pandox.xxx.exception;

import br.com.pandox.xxx.dto.ErroDTO;

import java.util.ArrayList;
import java.util.List;

public class BadRequestException extends RuntimeException {

    private List<ErroDTO> errors;

    public BadRequestException(ErroDTO erro) {
        this.errors = new ArrayList<>();
        errors.add(erro);
    }

    public List<ErroDTO> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BadRequestException{");
        sb.append("errors=").append(errors);
        sb.append('}');
        return sb.toString();
    }
}
