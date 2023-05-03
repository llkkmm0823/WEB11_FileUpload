package com.ezen.upload.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.upload.dao.ProductDao;
import com.ezen.upload.dto.ProductVO;

public class ProductViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int code = Integer.parseInt(request.getParameter("code"));
		ProductDao pdao = ProductDao.getInstance();
		ProductVO pvo = pdao.getProduct(code);

		request.setAttribute("product", pvo);
		RequestDispatcher dp = request.getRequestDispatcher("product/productView.jsp");
		dp.forward(request, response);

	}

}
