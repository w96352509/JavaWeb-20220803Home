package servlet.HttpServlet;

import java.io.IOException;
import java.net.http.HttpClient.Redirect;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MessageService;


@WebServlet("/servlet/message")
public class MessageServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String content = req.getParameter("content");
		String stickerPackageId = req.getParameter("stickerPackageId");
		String stickerId = req.getParameter("stickerId");
		String webImageUrl = req.getParameter("webImageUrl");
		int httpCode =0;
		MessageService messageService = new MessageService();
		if(stickerPackageId != null && stickerId != null && webImageUrl != null) {
			 httpCode = messageService.pushMessageAndStickerAndWebImage(content, stickerPackageId, stickerId, webImageUrl);
		}else if (stickerPackageId != null && stickerId != null) {
		     httpCode = messageService.pushMessageAndSticker(content, stickerPackageId, stickerId);
		} else if (webImageUrl != null) {
			 httpCode = messageService.pushMessageAndWebImage(content, webImageUrl);
		} else {
			 httpCode = messageService.pushMessage(content);
		}
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/message_result.jsp");
		req.setAttribute("content", content);
		req.setAttribute("stickerId", stickerId);
		req.setAttribute("webImageUrl", webImageUrl);
		req.setAttribute("httpCode", httpCode);
		rd.forward(req, resp);

	}

}
