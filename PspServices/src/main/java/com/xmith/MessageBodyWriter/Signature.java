package com.xmith.MessageBodyWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.xmith.models.Students;


public class Signature implements MessageBodyWriter<Students> {

@Override
public long getSize(Students arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
//depricated
		return -1;
}

@Override
public boolean isWriteable(Class<?> type, Type arg1, Annotation[] arg2, MediaType arg3) {
	//will return truw is student is there
	System.out.println("is assignable "+Students.class.isAssignableFrom(type));
return 	Students.class.isAssignableFrom(type);	
}

@Override
public void writeTo(Students students, Class<?> type, Type generictype, Annotation[] annotation, MediaType mediatype,
			MultivaluedMap<String, Object> headers, OutputStream entitystream) throws IOException, WebApplicationException {
try{
JAXBContext jaxbContext = JAXBContext.newInstance(Students.class);
Marshaller marshaller=jaxbContext.createMarshaller();
marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
StringWriter sw= new StringWriter();
marshaller.marshal(students, sw);
String result= sw.toString();
System.out.println("string resp"+result);

StringBuilder sb= new StringBuilder();
sb.append(result);
sb.append("<this is my signature>");

entitystream.write(sb.toString().getBytes());
}
catch (Exception e){
e.printStackTrace();	
}
	//catch
		
	}

}
