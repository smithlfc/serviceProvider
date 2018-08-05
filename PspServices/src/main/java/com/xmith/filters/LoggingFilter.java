package com.xmith.filters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.message.internal.ReaderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Provider
public class LoggingFilter implements ContainerRequestFilter  {
private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);	
	
@Override

public void filter(ContainerRequestContext requestcontext) throws IOException {
logger.info("filter :Entry");
logQueryParamaters(requestcontext);
getEntityStream(requestcontext);
logger.info("filter :Exit");

}

public void logQueryParamaters(ContainerRequestContext requestcontext){
MultivaluedMap<String, String> queryParameters =null;	
queryParameters = requestcontext.getUriInfo().getQueryParameters();	
Set<String> keySet = queryParameters.keySet();
boolean flag=true;
for (String string : keySet) {
logger.info("QuerParamaters: "+ string + " "+ queryParameters.get(string));	
flag=false;
}
if(flag){
logger.info("QuerParamaters: NO");	
}	
}

public void getEntityStream(ContainerRequestContext requestcontext){
logger.info("getEntityStream :Entry");
InputStream entityStream = requestcontext.getEntityStream();
ByteArrayOutputStream os= new ByteArrayOutputStream();
StringBuilder builder= new StringBuilder();
try {
	ReaderWriter.writeTo(entityStream, os);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
byte[] byteArray = os.toByteArray();
if(byteArray.length==0){
//imp as you have to pass thes stream	
builder.append("");	
}
else{
builder.append(new String(byteArray));	
}
//imp
//the input is in output
//and explicitly  set like dochain

requestcontext.setEntityStream(new ByteArrayInputStream(byteArray));

logger.info("Entity is : "+builder.toString());


logger.info("getEntityStream :Exit");
}





}
