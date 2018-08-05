package com.xmith.web.PspServices;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.xmith.models.Students;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
private static final Logger logger = LoggerFactory.getLogger(MyResource.class);	
	
@POST
//@GET
@Path("/abc")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public Students  getdata( @Valid Students st) throws ValidationException {
logger.info("method entry");
System.out.println(st.getStudent().get(0).getAge());
return st;
}
	
	


}
