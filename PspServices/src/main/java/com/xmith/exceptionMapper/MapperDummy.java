package com.xmith.exceptionMapper;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MapperDummy implements ExceptionMapper<javax.validation.ValidationException> {

@Override
public Response toResponse(ValidationException ex) {
ConstraintViolationException cv=(ConstraintViolationException)ex;
Set<ConstraintViolation<?>> constraintViolations = cv.getConstraintViolations();
StringBuilder sb= new StringBuilder();
for (ConstraintViolation<?> constraintViolation : constraintViolations) {
sb.append(constraintViolation.getMessage())	;
}

return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.TEXT_PLAIN).entity(sb.toString()).build();
}

}
