package com.tour.prevel.global.exception;

import com.nimbusds.jose.proc.BadJWSException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Globals;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final MessageSource messageSource;

    @ExceptionHandler({ BadJWSException.class })
    protected ResponseEntity<Object> handleBadJWSException(
            Exception ex,
            HttpServletRequest request,
            HttpServletResponse response,
            WebRequest webRequest
    ) {
        log.error("?? Error occurred while requesting URI={}, HTTP StatusCode={}, Exception={}, Message={}",
                request.getRequestURI(), response.getStatus(), ex.getClass().getName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(createErrorAttribute(webRequest, messageSource.getMessage("error.auth.unauthorized", null, null)));
    }

    @ExceptionHandler({ UsernameNotFoundException.class, BadCredentialsException.class})
    protected ResponseEntity<Object> handleUsernameNotFoundException(
            Exception ex,
            HttpServletRequest request,
            HttpServletResponse response,
            WebRequest webRequest
    ) {
        log.error("?? Error occurred while requesting URI={}, HTTP StatusCode={}, Exception={}, Message={}",
                request.getRequestURI(), response.getStatus(), ex.getClass().getName(), ex.getMessage());
        return ResponseEntity.badRequest().body(createErrorAttribute(webRequest, messageSource.getMessage("error.auth.invalid", null, null)));
    }

    private Map<String, Object> createErrorAttribute(WebRequest request, String message) {
        Map<String, Object> responseInfo = getDefaultAttributes(request);
        responseInfo.put(ErrorAttributes.ERROR_ATTR, message);
        return responseInfo;
    }

    private Map<String, Object> getDefaultAttributes(WebRequest request) {
        Map<String, Object> responseInfo = new LinkedHashMap<>();
        responseInfo.put(ErrorAttributes.TIMESTAMP_ATTR, LocalDateTime.now());
        Object path = request.getAttribute(Globals.DISPATCHER_REQUEST_PATH_ATTR, RequestAttributes.SCOPE_REQUEST);
        if (path != null) {
            responseInfo.put(ErrorAttributes.PATH_ATTR, path);
        }
        return responseInfo;
    }
}
