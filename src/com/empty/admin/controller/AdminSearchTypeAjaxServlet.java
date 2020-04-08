package com.empty.admin.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.empty.admin.model.service.AdminService;
import com.empty.member.model.vo.Member;

/**
 * Servlet implementation class AdminSearchTypeServlet
 */
@WebServlet("/admin/user/searchTypeAjax")
public class AdminSearchTypeAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchTypeAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("searchType");//검색 타입 , userName, gender,phone
		String keyword = request.getParameter("searchKeyword").trim();//검색 키워드 
		 
		//페이징 처리 
		int cPage;
		try{
			cPage= Integer.parseInt(request.getParameter("cPage"));//현재 페이지
		}catch(NumberFormatException e) {
			cPage=1;
		}
		int numPerPage ;
		try{
			numPerPage= Integer.parseInt(request.getParameter("numPerPage"));//페이지당 게시글 수 
		}catch(NumberFormatException e) {
			numPerPage=10;
		}
		
		List <Member> list = new AdminService().selectMember(type,keyword,cPage,numPerPage);
		JSONArray ja = new JSONArray();
		if(list.size()>0) {
			int totalMember = new AdminService().memberCount(type,keyword);
			int totalPage = (int)Math.ceil((double)totalMember/numPerPage);
			int pageBarSize = 5;
			int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
			int pageEnd = pageNo+pageBarSize-1;
			
			String pageBar="";
			if(pageNo==1) {
				pageBar+="<span class='nextBtn' style='color:grey'>  ◀ 이전 </span>";
			}else {
				pageBar += "<a href='javascript:void(0);' onclick='searchKeyType(\""+type+"\",\""+keyword+"\","+(pageNo-1)+","+numPerPage+")'> ◀ 이전 </a>";
				pageBar+="<a href='javascript:void(0);' onclick='searchKeyType(\""+type+"\",\""+keyword+"\","+1+","+numPerPage+")'>  1  </a>";
				pageBar+="<span> ... </span>";
			}
			while(!(pageNo>pageEnd||pageNo>totalPage)) {
				if(pageNo==cPage) {
					pageBar+="<span class='cPage' style='color:#ff7531'> "+pageNo+" </span>";
				}else {
					pageBar+="<a href='javascript:void(0);' onclick='searchKeyType(\""+type+"\",\""+keyword+"\","+pageNo+","+numPerPage+")'> "+pageNo+" </a>";
					
				}
				pageNo++;
			}
			if(pageNo>totalPage) {
				pageBar+="<span class='nextBtn'style='color:grey'> 다음 ▶ </span>";
			}else {
				pageBar+="<span> ... </span>";
				pageBar+="<a href='javascript:void(0);' onclick='searchKeyType(\""+type+"\",\""+keyword+"\","+totalPage+","+numPerPage+")'>  "+totalPage+"  </a>";
				pageBar+="<a href='javascript:void(0);' onclick='searchKeyType(\""+type+"\",\""+keyword+"\","+pageNo+","+numPerPage+")'> 다음 ▶ <a>";
			}
			
			
			for(Member m : list) {
				JSONObject jo = new JSONObject();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
				String date = sdf.format(m.getEnrollDate());
				jo.put("userid",m.getUserId());
				if(!m.isUserDiv())jo.put("userdiv","일반회원" );
				jo.put("username",m.getUserName());
				jo.put("gender",m.getGender());
				jo.put("phone",m.getPhone());
				jo.put("email",m.getEmail());
				jo.put("address",m.getAddress());
				jo.put("enrolldate",date);
				ja.add(jo);
			}
			//pagebar add
			JSONObject obj=new JSONObject();
			obj.put("result", totalMember);
			ja.add(obj);
			obj=new JSONObject();
			obj.put("pageBar", pageBar);
			ja.add(obj);
			
		}else {
			//검색 결과가 없을 때
			JSONObject obj=new JSONObject();
			obj.put("msg","검색된 결과가 없습니다.");
			ja.add(obj);
		}
		
		response.setContentType("application/text/plain;charset=UTF-8");
		response.getWriter().print(ja);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
