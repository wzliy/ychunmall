package com.ychun.common.error;

import com.ychun.common.api.BaseResponse;
import com.ychun.common.api.ResponseModel;
import com.ychun.common.api.ResultCode;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionTranslator {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionTranslator.class);


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public <T> BaseResponse<T> handleError(MissingServletRequestParameterException e) {
        logger.warn("Missing Request Parameter", e);
        String message = String.format("Missing Request Parameter: %s", e.getParameterName());
        return ResponseModel.error(message, ResultCode.PARAM_MISS);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public <T> BaseResponse<T> handleError(MethodArgumentTypeMismatchException e) {
        logger.warn("Method Argument Type Mismatch", e);
        String message = String.format("Method Argument Type Mismatch: %s", e.getName());
        return ResponseModel.error(message, ResultCode.PARAM_TYPE_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> BaseResponse<T> handleError(MethodArgumentNotValidException e) {
        logger.warn("Method Argument Not Valid", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        assert error != null;
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return ResponseModel.error(message, ResultCode.PARAM_VALID_ERROR);
    }

    @ExceptionHandler(BindException.class)
    public <T> BaseResponse<T> handleError(BindException e) {
        logger.warn("Bind Exception", e);
        FieldError error = e.getFieldError();
        assert error != null;
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return ResponseModel.error(message, ResultCode.PARAM_VALID_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public <T> BaseResponse<T> handleError(ConstraintViolationException e) {
        logger.warn("Constraint Violation", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
        String message = String.format("%s:%s", path, violation.getMessage());
        return ResponseModel.error(message, ResultCode.PARAM_VALID_ERROR);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public <T> BaseResponse<T> handleError(NoHandlerFoundException e) {
        logger.error("404 Not Found", e);
        return ResponseModel.error(e.getMessage(), ResultCode.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public <T> BaseResponse<T> handleError(HttpMessageNotReadableException e) {
        logger.error("Message Not Readable", e);
        return ResponseModel.error(e.getMessage(), ResultCode.MSG_NOT_READABLE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public <T> BaseResponse<T> handleError(HttpRequestMethodNotSupportedException e) {
        logger.error("Request Method Not Supported", e);
        return ResponseModel.error(e.getMessage(), ResultCode.MEDIA_TYPE_NOT_SUPPORTED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public <T> BaseResponse<T> handleError(HttpMediaTypeNotSupportedException e) {
        logger.error("Media Type Not Supported", e);
        return ResponseModel.error(e.getMessage(), ResultCode.MEDIA_TYPE_NOT_SUPPORTED);
    }

    @ExceptionHandler(ServiceException.class)
    public <T> BaseResponse<T> handleError(ServiceException e) {
        logger.error("Service Exception", e);
        return ResponseModel.error(e.getMessage(), e.getResultCode());
    }

}
