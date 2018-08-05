package com.xmith.MessageBodyWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.dom4j.tree.BackedList;

@Provider
public class WriteInterceptor implements WriterInterceptor{

	@Override
public void aroundWriteTo(WriterInterceptorContext interceptorContext) throws IOException, WebApplicationException {
		 OutputStream outputStream = interceptorContext.getOutputStream();
		    String appendedContent = "\nInterceptors always get the last word in.";
		    outputStream.write(appendedContent.getBytes());
		    
		    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		    byteArrayOutputStream.write(appendedContent.getBytes());
		    
		    interceptorContext.setOutputStream(byteArrayOutputStream);

		    interceptorContext.proceed();
	}

}
