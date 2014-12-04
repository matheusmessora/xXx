package br.com.pandox.xxx.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import br.com.pandox.xxx.dto.ErroDTO;
import br.com.pandox.xxx.exception.BadRequestException;
import br.com.pandox.xxx.exception.InternalServerErrorException;
import br.com.pandox.xxx.exception.NoContentException;
import br.com.pandox.xxx.exception.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseController {


    private static Logger log = Logger.getLogger(BaseController.class);

//    @Autowired
//    private ReloadableResourceBundleMessageSource resourceBundleMessageSource;
//
//    @Autowired
//    private ReloadableResourceBundleMessageSource config;

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<ErroDTO> badRequestHandler(BadRequestException ex, HttpServletResponse response) {
        log.info(ex.getErrors());

        return ex.getErrors();
    }

    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ResponseBody
    public List<ErroDTO> noContent(NoContentException ex, HttpServletResponse response) {
        return new ArrayList<>();
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public List<ErroDTO> internalErrorHandler(InternalServerErrorException ex, HttpServletResponse response) {
        List<ErroDTO> result = new ArrayList();

        result.add(new ErroDTO("sistema", "Falhou"));

        return result;
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public List<ErroDTO> unathorizedException(UnauthorizedException ex, HttpServletResponse response) {
        List<ErroDTO> result = new ArrayList();
        result.add(new ErroDTO("login", "usuario nao autenticado"));
        return result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<ErroDTO> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, HttpServletResponse response) {
        List<ErroDTO> result = new ArrayList();


        List<FieldError> allErrors = ex.getBindingResult().getFieldErrors();

        for (FieldError error : allErrors) {
            result.add(new ErroDTO(error.getField(), error.getDefaultMessage()));
        }
        log.info(result);

        return result;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<ErroDTO> validationHandler(BindException ex, HttpServletResponse response) {
        List<ErroDTO> result = new ArrayList();


        List<FieldError> allErrors = ex.getFieldErrors();

        for (FieldError error : allErrors) {
            result.add(new ErroDTO(error.getField(), error.getDefaultMessage()));
        }
        log.info(result);

        return result;
    }

//    protected String getMessage(String keyMessage) {
//        return resourceBundleMessageSource.getMessage(keyMessage, null, null);
//    }
//
//    protected String getMessage(String keyMessage, Object... object) {
//        return resourceBundleMessageSource.getMessage(keyMessage, object, null);
//    }
//
//    protected String getPropertyConfig(String key) {
//        return config.getMessage(key, null, null);
//    }
//
//    protected String getPropertyConfig(String keyMessage, Object... object) {
//        return config.getMessage(keyMessage, object, null);
//    }

    protected String getRemoteIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
