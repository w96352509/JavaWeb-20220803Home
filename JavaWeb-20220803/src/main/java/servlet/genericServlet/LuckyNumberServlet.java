package servlet.genericServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LuckyNumberServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		Random r = new Random();
		PrintWriter out = res.getWriter();
		out.print("<html>");
		out.println("<H1 style='color: blue'>");
		out.print(String.format("新運數字%d", r.nextInt(100)));
		out.println("</H1>");
		out.print("</html>");
	}

}
