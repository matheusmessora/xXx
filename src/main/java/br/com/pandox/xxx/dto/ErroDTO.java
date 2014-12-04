package br.com.pandox.xxx.dto;

public class ErroDTO {

    private String field;
    private String message;

    public ErroDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErroDTO{");
        sb.append("field='").append(field).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
