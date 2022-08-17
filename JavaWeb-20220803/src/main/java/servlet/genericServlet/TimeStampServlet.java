package servlet.genericServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/generic/TimeStampServlet")
public class TimeStampServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss E");
		String todayString = sdf.format(today);
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;chartset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<H1 style='color: red'>");
		out.println(todayString);
		out.println("</H1>");
		out.println("</BODY>");
		out.println("</HTML>");
	}
 
}
