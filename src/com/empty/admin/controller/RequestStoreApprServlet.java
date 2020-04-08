package com.empty.admin.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empty.admin.model.service.AdminService;
import com.empty.member.mail.Gmail;
import com.empty.member.mail.SHA256;

/**
 * Servlet implementation class RequestStoreApprServlet
 */
@WebServlet("/admin/store/requestAppr")
public class RequestStoreApprServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestStoreApprServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId=request.getParameter("userId");
		String email = request.getParameter("email");
		int result = new AdminService().updateAppr(userId);
		if(result>0) {
			String host = "http://localhost:9090/EMPTY/";
			String from = "qkrejrgus4713@gmail.com";
			String to = email;
			String subject = "스토어 승인이 완료되었습니다.";
			String sha256 = SHA256.getSHA256(to);
			String content = "<div style='width:400px;'>"
					+ "<div></br></div>"
					+ "<div>"
					+ "<img src='https://postfiles.pstatic.net/MjAyMDAzMzBfMiAg/MDAxNTg1NTcwOTEzOTg0.B1AVBwL55DSqoaZyYb-gJx16ZEwllIzwUJcqJ4RrIH8g.7HvnqufTa1oa48HZRDcJjEYSfvieZt2wOadudYk1IJ8g.PNG.pdh4713/%EB%B9%88%EC%8B%9C%ED%8A%B8500-1.png?type=w773' width='200px'><br>"
					+ "<a style='font-size:15px;font-weight:border;' href='" + host+"'>빈시트 가기</a>"
					+ "</div>"
					+ "</div>";

			Properties p = new Properties();
			// SMTP서버 쓸 때 필요한거
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.transport.protocol", "smtp");
			p.put("mail.smtp.host", "smtp.gmail.com");
			p.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.port", "465");
			p.put("mail.smtp.user", from);
			p.put("mail.smtp.auth", "true");

			try{
				Authenticator auth = new Gmail();
				Session ses = Session.getInstance(p, auth);
				ses.setDebug(true);
				MimeMessage msg = new MimeMessage(ses);      
				msg.setSubject(subject);

				Address fromAddr = new InternetAddress(from);      
				msg.setFrom(fromAddr);

				Address toAddr = new InternetAddress(to);      
				msg.addRecipient(Message.RecipientType.TO, toAddr);

				msg.setContent(content,"text/html; charset=UTF8");
				Transport.send(msg);

			}catch(Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("msg", userId+" 유저의 스토어 승인이 완료되었습니다.");
			
		}else {
			request.setAttribute("msg", userId+" 유저의 스토어 승인이 실패되었습니다.");
		}
		
		request.setAttribute("loc", "/admin/store/requestStoreList");
		request.setAttribute("locstr", "목록으로");
		request.getRequestDispatcher("/views/admincommon/msg.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
