package listener;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Context listener. Need for initing log4j
 * @author Vladislav Prokopenko
 */
public class ContextListener implements ServletContextListener {
	public ContextListener() {
	}

	public void contextDestroyed(ServletContextEvent paramServletContextEvent)  {
	}

	public void contextInitialized(ServletContextEvent servletContext)  {
		PropertyConfigurator.configure(servletContext.getServletContext().getRealPath(
				"WEB-INF/log4j.properties"));
	}
}