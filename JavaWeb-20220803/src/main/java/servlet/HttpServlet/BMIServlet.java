package servlet.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/httpServlet/BMIServlet")
public class BMIServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print("GET");
		System.out.println("GET");
		/*
		String h = req.getParameter("h");
		String w = req.getParameter("w");
		out.println("<p>");
		out.println("height = " + h);
		out.println("<p>");
		out.println("weight = " + w); 
		*/
		/*
		Map<String, String[]> map = req.getParameterMap();
		String[] h = map.get("h");
		String[] w = map.get("w");
		out.println("<p>");
		out.println("height = " + Arrays.toString(h));
		out.println("<p>");
		out.println("weight = " + Arrays.toString(w)); 
		*/
		String[] heights = req.getParameterValues("h");
		String[] weights = req.getParameterValues("w");
		out.println("<p>");
		out.println("heights = " + Arrays.toString(heights));
		out.println("<p>");
		out.println("weights = " + Arrays.toString(weights));
		out.println("<p>");
		// 得到所有參數名
				Enumeration<String> names = req.getParameterNames();
				out.println("得到參數名稱:");
				while(names.hasMoreElements()) {
					out.println(names.nextElement() + " ");
				}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.print("POST");
		System.out.println("doPost");
	}

	
	
}
