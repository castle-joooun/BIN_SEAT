package com.empty.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.empty.member.model.service.MemberService;
import com.empty.member.model.vo.Member;
import com.empty.member.model.vo.StoreImg;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class EnrollStoreServlet
 */
@WebServlet(name = "EnrollstoreServlet", urlPatterns = { "/enroll/fileUp" })
public class EnrollStoreFileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollStoreFileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!ServletFileUpload.isMultipartContent(request)) {
			response.sendRedirect("/");
			return;
		}
		HttpSession session = request.getSession();
		Member m = (Member) session.getAttribute("loginMember");
		String userId= m.getUserId();
		System.out.println(userId);
		//저장경로
		String path = getServletContext().getRealPath("/image");
		//최대 파일 크기
		
		  int maxSize= 1024*1024*10; 
		  MultipartRequest mr = new MultipartRequest(request, path, maxSize, "UTF-8",new DefaultFileRenamePolicy()); 
		  String storeImg1 ="image/"+mr.getFilesystemName("empty0"); 
		  String storeImg2 ="image/"+mr.getFilesystemName("empty1"); 
		  String storeImg3 ="image/"+mr.getFilesystemName("empty2"); 
		  String storeImg4 ="image/"+mr.getFilesystemName("empty3"); 
		  String storeImg5 ="image/"+mr.getFilesystemName("empty4"); 
		  List list = new ArrayList();
		  list.add(storeImg1);
		  list.add(storeImg2);
		  list.add(storeImg3);
		  list.add(storeImg4);
		  list.add(storeImg5);
		  int result = new MemberService().insertStoreImg(userId, list);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
