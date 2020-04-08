package com.empty.member.controller;

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

import com.empty.member.mail.Gmail;
import com.empty.member.mail.SHA256;
import com.empty.member.model.service.MemberService;
import com.empty.member.model.vo.Member;

@WebServlet(name = "FindPwServlet", urlPatterns = "/findPw")
public class FindPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FindPwServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String email = request.getParameter("email");
		Member m = new MemberService().findPw(userId, email);

		if(m != null) {
			String host = "http://rclass.iptime.org:9999/20AM_EMPTY_SEAT/";
			String from = "qkrejrgus4713@gmail.com";
			String to = email;
			String subject = "빈시트 비밀번호 찾기 인증 메일입니다.";
			String sha256 = SHA256.getSHA256(to);
			String content = "<div style='width:400px;'>"
					+ "<div>다음 링크에 접속하시면 비밀번호 변경 페이지로 이동합니다.</br></div>"
					+ "<div>"
					+ "<img src='https://postfiles.pstatic.net/MjAyMDAzMzBfMiAg/MDAxNTg1NTcwOTEzOTg0.B1AVBwL55DSqoaZyYb-gJx16ZEwllIzwUJcqJ4RrIH8g.7HvnqufTa1oa48HZRDcJjEYSfvieZt2wOadudYk1IJ8g.PNG.pdh4713/%EB%B9%88%EC%8B%9C%ED%8A%B8500-1.png?type=w773' width='200px'><br>"
					+ "<a style='font-size=15px' font-weight='border'; href='" + host + "checkEmailAuthentication?code=" + sha256 + "&email=" + email + "&userId=" + userId +"' + >이메일 인증하기</a>"
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

			String msg = "입력하신 메일 주소로 이메일이 발송되었습니다.";
			String loc = "/";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			RequestDispatcher rd = request.getRequestDispatcher("views/common/msg.jsp");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("views/member/failFindPw.jsp");
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
