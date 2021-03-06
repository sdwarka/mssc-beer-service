package guru.springframework.msscbeerservice.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ControllerAdvice
@Slf4j
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e) {
        log.info("MvcExceptionHandler: ENTERED HANDLE EXCEPTION");
        List<String> errors = new ArrayList<>();

        //Non-lambda code
        Set<ConstraintViolation<?>> cvs = e.getConstraintViolations();
        for(ConstraintViolation<?> cv : cvs) {
            errors.add(cv.getPropertyPath() + ":" + cv.getMessage());
        }

        /** iterator code for the above
        Iterator iter = cvs.iterator();
        while(iter.hasNext()) {
            ConstraintViolation cv = (ConstraintViolation)iter.next();
            errors.add(cv.toString());
        }*/

        /** equivalent lambda code for the above
         * e.getConstraintViolations().forEach(error -> errors.add(error.toString()));
         */

        ResponseEntity<List> retval = new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        log.info("MvcExceptionHandler: ERRORS: " + retval.toString());
        log.info("MvcExceptionHandler: EXITED HANDLE EXCEPTION");
        return retval;
    }
}
