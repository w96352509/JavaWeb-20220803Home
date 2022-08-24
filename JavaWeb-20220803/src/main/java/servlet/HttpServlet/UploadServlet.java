package servlet.HttpServlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

@WebServlet(urlPatterns = "/servlet/upload/*")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 30
)
public class UploadServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 設定回傳字體編碼
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");

		// 取得路徑尾
		String path = req.getPathInfo();
		// 印出路徑
		resp.getWriter().print("path" + path + "<p />");

		switch (path) {
		case "/file":
			uploadFile(req ,  resp);
			break;

		case "/image":
			uploadImage(req, resp);
			break;
		}

	}

	// 文字檔 + cname
	private void uploadFile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getParts()
        .stream()
        .filter(part -> part.getName().equals("cname"))
        .forEach(part -> {
            try {
                String cname = IOUtils.toString(part.getInputStream(),
                        StandardCharsets.UTF_8.name());
                resp.getWriter().print(part.getName() + " : ");
                resp.getWriter().print(cname + "<br />");
            } catch (Exception e) {
            }
        });
       
		req.getParts()
        .stream()
        .filter(part -> part.getName().equals("upload_file"))
        .forEach(part -> {
            try {
                String cname = IOUtils.toString(part.getInputStream(),
                        StandardCharsets.UTF_8.name());
                resp.getWriter().print(part.getName() + " : ");
                resp.getWriter().print(cname + "<br />");
                
                String path = "C:\\upload";
				File file = new File(path);
				if(!file.exists()) {
					file.mkdir();
				}
				String name = part.getSubmittedFileName();
				System.out.println(name);
				part.write(path+ "\\" +name);
				
            } catch (Exception e) {
            }
        });
		
      
	}

	// 圖檔 + cname
	private void uploadImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// cname 部分
		req.getParts()
        .stream()
        .filter(part -> part.getName().equals("cname"))
        .forEach(part -> {
            try {
                String cname = IOUtils.toString(part.getInputStream(),
                        StandardCharsets.UTF_8.name());
                resp.getWriter().print(part.getName() + " : ");
                resp.getWriter().print(cname + "<br />");
                
               
            
            } catch (Exception e) {
            }
        });
		
		req.getParts()
		   .stream()
		   .filter(part -> part.getName().equals("upload_file"))
		   .forEach(p -> {
			     try {
					InputStream is =  p.getInputStream();
					byte[] bytes = IOUtils.toByteArray(is);
					String base64 = Base64.getEncoder().encodeToString(bytes);
					
					String imageHtml = "<img src='data:image/png;base64, %s'>";
					resp.getWriter().print(String.format(imageHtml, base64));
					
					String path = "C:\\upload";
					File file = new File(path);
					if(!file.exists()) {
						file.mkdir();
					}
					String name = p.getSubmittedFileName();
					p.write(path+ "\\" +name);
					
				} catch (IOException e) {
					
				}
		   });
	}

}
