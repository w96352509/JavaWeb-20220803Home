package servlet.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/servlet/httpServlet/lotto")
public class LottoFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("執行 LottoFilter");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		// 驗證參數 count
		String count = request.getParameter("count");
		if(count == null || count.length() == 0) {
			PrintWriter out = response.getWriter();
			out.print("Lotto 無法服務. 因為資料輸入不完整");
		} else {
			// 通過並往下繼續執行
			chain.doFilter(request, response);
		}
	}

	
	
}
