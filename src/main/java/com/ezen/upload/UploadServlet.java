package com.ezen.upload;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload.do")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//1. 파일이 업로드될 타겟폴더를 지정, String으로 저장해둠
		String savePath = "upload";

		//2. 업로드될 파일의 용량을 제한하기 위한 용량값을 int변수에 저장해둠
		int uploadFileSizeLimit = 5*1024*1024;
		//1바이트 기준 1024byte=1Kbyte
		//1024KByte = 1MB

		//3. 인코딩 방식을 String변수에 저장해둠
		String encType = "UTF-8";

		//4. 업로드될 서버의 실제 저장장소를 얻어서 upload폴더를 만들고 그 경로는 String 변수에 저장해둠
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);

		System.out.println(uploadFilePath);
		//우리가 만든 webapp 하위 폴더인 upload폴더와 uploadFilePath의 경로의 폴더를 다운받은 cos.jar이 매핑시켜줌.

		String name = request.getParameter("name");
		String title = request.getParameter("title");

		System.out.println("기존 request로 받은 데이터 출력");
		System.out.println("name : " + name);
		System.out.println("title : " + title);
//		기존 request로 받은 데이터 출력
//		name : null
//		title : null  <-이렇게 나옴
		System.out.println("출력 결과를 봐서는 multipart/form-data로 보낸 파라미터는 request만으로 받아 저장할 수 없음");


		//5. MultipartRequest 객체를 생성하여 업로드를 마무리
		//import할 때 import com.oreilly.servlet.MultipartRequest; 가 맞는지 확인
		//받는건 request로 잘 받았으나 표현을 못하는 것임
		MultipartRequest multi = new MultipartRequest(
			request, // jsp에서 전달된 request객체를 MultipartRequest로 전달
			//MultipartRequest에 request를 넣어서 복합사용해야 담겨있는 유효한 값을 얻을 수 있음
			uploadFilePath, // 서버상의 실제 저장장소
			uploadFileSizeLimit, // 최대업로드 용량제한
			encType, //인코딩방식
			new DefaultFileRenamePolicy() // 저장장소에 업로드되는 파일이름 중복시 문제 해결하는 객체
			//ex) dog.jpg , dog1.jpg , dog2.jpg  똑같은 파일네임으로 업로드 시 저장은 이름을 바꿔 저장함
			//이게 없으면 밀리초단위의 날짜와시간을 파일이름 뒤에 붙여 저장해버림
		);
		//MultipartRequest 객체가 생성되는 순간 업로드되는 파일은 해당 경로에 업로드를 완료

		name = multi.getParameter("name");
		title = multi.getParameter("title");
		String fileName = multi.getFilesystemName("uploadFile"); // 전달된 파일이름 추출
		String originName = multi.getOriginalFileName("uploadFile");
		//전달된 파일의 원래 이름 ( 중복될 경우 DefaultFileRenamePolicy에 의해 숫자를 추가해 저장하기 때문)
		//ex) dog.jpg , dog1.jpg 일때, OriginalFileName : dog.jpg 이나 filename은 dog1.jpg임.
		System.out.println("multi로 받은 데이터 출력");
		System.out.println("name : " + name);
		System.out.println("title : " + title);
		System.out.println("fileName : " + fileName);
		System.out.println("OriginalfileName : " + originName);


		request.setAttribute("name", name);
		request.setAttribute("title", title);
		request.setAttribute("fileName", fileName);


		RequestDispatcher dp = request.getRequestDispatcher("01_result.jsp");
		dp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
