package com.ezen.upload.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.upload.dao.ProductDao;
import com.ezen.upload.dto.ProductVO;

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		int code=Integer.parseInt(request.getParameter("code"));

		//조회
		ProductDao pdao = ProductDao.getInstance();
		ProductVO pvo = pdao.getProduct(code);

		//저장
		request.setAttribute("product", pvo);

		//이동
		RequestDispatcher rd = request.getRequestDispatcher("product/updateForm.jsp");
		rd.forward(request, response);

	}

}
