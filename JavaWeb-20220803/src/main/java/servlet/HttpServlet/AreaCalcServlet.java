package servlet.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CalcAreaService;

@WebServlet("/servlet/httpServlet/AreaCalcServlet")
public class AreaCalcServlet extends HttpServlet {

	private service.CalcAreaService  calcAreaService = new CalcAreaService(); 
	
	private void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 resp.setCharacterEncoding("UTF-8");
		 resp.setContentType("text/html");
		 PrintWriter out = resp.getWriter();
		 
		 String[] types = req.getParameterValues("type");
		 String[] rs     = req.getParameterValues("r");
		 
		 List<Map> list = calcAreaService.getAreaResults(types, rs);
		 
		 out.print(list);
		 
		 
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doHandle(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doHandle(req, resp);
	}

	
	
}
