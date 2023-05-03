package com.ezen.upload.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.upload.dao.ProductDao;
import com.ezen.upload.dto.ProductVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		String path = context.getRealPath("upload");

		MultipartRequest multi = new MultipartRequest(
				request, path, 20*1024*1024,"UTF-8" ,new DefaultFileRenamePolicy()
		);
		ProductVO pvo = new ProductVO();
		String code = multi.getParameter("code"); // 자료수정 후에 되돌아갈 상품번호 별도 저장
		pvo.setCode(Integer.parseInt(multi.getParameter("code")));
		pvo.setName(multi.getParameter("name"));
		pvo.setPrice(Integer.parseInt(multi.getParameter("price")));
		pvo.setPictureurl(multi.getFilesystemName("pictureurl"));
		pvo.setDescription(multi.getParameter("description"));

		if(multi.getFilesystemName("pictureurl")==null)  // 수정할 이미지를 선택하지 않았다면
			pvo.setPictureurl(multi.getParameter("oldPicture"));
		else // 수정할 이미지를 선택했다면
			pvo.setPictureurl(multi.getFilesystemName("pictureurl"));

		ProductDao pdao = ProductDao.getInstance();
		pdao.updateProduct(pvo);
		String url = "product.do?command=productView&code=" +code;

		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);

	}

}
