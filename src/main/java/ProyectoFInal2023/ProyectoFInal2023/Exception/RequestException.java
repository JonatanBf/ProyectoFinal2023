package ProyectoFInal2023.ProyectoFInal2023.Exception;

import lombok.Data;

@Data
public class RequestException extends RuntimeException{

    private String code;

    public RequestException(String code, String message) {
        super(message);
        this.code = code;
    }
}
