package com.ezen.upload.controller.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.upload.dao.ProductDao;
import com.ezen.upload.dto.ProductVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ProductWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		//getSession()은 서블릿 안에서는 단독으로 사용이 되지만, 자바 클래스 안에서는 session에 있는 멤버 메서드를 호출하여 사용

		String path = context.getRealPath("upload");

		MultipartRequest multi = new MultipartRequest(

				request,path,5*1024*1024,"UTF-8",new DefaultFileRenamePolicy()
		);
		ProductVO pvo= new ProductVO();
		pvo.setName(multi.getParameter("name"));
		pvo.setPrice(Integer.parseInt(multi.getParameter("price")));
		pvo.setPictureurl(multi.getFilesystemName("pictureurl"));
		pvo.setDescription(multi.getParameter("description"));

		ProductDao pdao = ProductDao.getInstance();
		pdao.insertProduct(pvo);
		response.sendRedirect("product.do?command=index");


	}
}
