package ex05.pyrmont.valves;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Valve;
import org.apache.catalina.ValveContext;

public class HeaderLoggerValve implements Valve, Contained {

    protected Container container;

    public void invoke(Request request, Response response, ValveContext valveContext)
            throws IOException, ServletException {

       /* try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        // Pass this request on to the next valve in our pipeline
        valveContext.invokeNext(request, response);

        System.out.println("Header Logger Valve " + System.currentTimeMillis());
        ServletRequest sreq = request.getRequest();
        if (sreq instanceof HttpServletRequest) {
            HttpServletRequest hreq = (HttpServletRequest) sreq;
            Enumeration headerNames = hreq.getHeaderNames();
            System.out.println("request uri is " + hreq.getRequestURL());
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement().toString();
                String headerValue = hreq.getHeader(headerName);
                System.out.println(headerName + ":" + headerValue);
            }

        } else System.out.println("Not an HTTP Request");

        System.out.println("------------------------------------");
    }

    public String getInfo() {
        return null;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }
}