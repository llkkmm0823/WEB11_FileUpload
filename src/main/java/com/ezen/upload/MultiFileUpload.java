package com.ezen.upload;

import java.io.IOException;
import java.util.Enumeration;

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
 * Servlet implementation class MultiFileUpload
 */
@WebServlet("/upload2.do")
public class MultiFileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultiFileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath("upload");

		MultipartRequest multi = new MultipartRequest(
				request, uploadFilePath, 5*1024*1024, "UTF-8",
				new DefaultFileRenamePolicy()
				); // 폼 안에 있던 <input type = "file"> 의 파일들은 MultipartRequest 생성 시 한번에 업로드 됨

		//한번에 전달된 "파일이름들"은 Enumeration을 사용하여 전달받고, 하나씩 꺼내서 사용함
		Enumeration files = multi.getFileNames(); //전송된 input type="file"의 name들
		//파일(<input type="file" name="uploadFile01">들을 Enumeration 에 저장
		int i=1;
		while(files.hasMoreElements()) { //파일요소의 갯수만큼 반복실행
			String file = (String) files.nextElement(); // 하나씩 파일요소(name) 추출
			String file_name = multi.getFilesystemName(file); //저장파일이름
			String ori_file_name=multi.getOriginalFileName(file); // 원래파일이름
			//증가하는 숫자를 이용하여 애트리뷰트 이름생성
			request.setAttribute("file_system_name"+i,file_name);
			request.setAttribute("ori_file_name"+i,ori_file_name);
			i++;
		}
		RequestDispatcher dp = request.getRequestDispatcher("02_result.jsp");
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
