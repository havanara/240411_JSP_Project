package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import service.CommentService;
import service.CommentServiceImpl;

/**
 * Servlet implementation class CommentController
 */
@WebServlet("/cmt/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(CommentController.class);
       
	//비동기 통신은 데이터를 요청한 곳으로 바로 결과를 보내주는 방식
	//RequestDispatcher, destPage, contentType 필요없음
	//그냥 요청한 곳으로 데이터만 보내는것뿐!
	
	private CommentService csv;
	private int isOK; //굳이 안써도됨 (그때 그때 int isOK 생성해서 쓰는 케이스가 더 많음)
	
    public CommentController() {
    	csv = new CommentServiceImpl();
    }
 

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //request 요청객체 인코딩
		response.setCharacterEncoding("utf-8"); //response 응답객체 인코딩
		
		String url = request.getRequestURI();
		String path = url.substring(url.lastIndexOf("/")+1);
		log.info(">>> cmt path > {} ", path);
		
		switch(path) {
		case "post" :
			try {
				log.info("post case check 1");
				//js에서 보낸 데이터를 읽어들이는 작업
				//js('Object') -> controller(String)
				//'{...}
				StringBuffer sb = new StringBuffer();
				String line = "";
				BufferedReader br = request.getReader(); //댓글 객체가 들어있음
				while((line = br.readLine()) != null){
					sb.append(line);
				}
				log.info(">>> sb >>> {}", sb.toString());
				
				//이 값을 객체로 생성
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject)parser.parse(sb.toString()); //key:value
				log.info(">>> jsonObj >>> {}", jsonObj.toJSONString());
				
				//key를 이용하여 value를 추출
				int bno = Integer.parseInt(jsonObj.get("bno").toString());
				String writer = jsonObj.get("writer").toString();
				String content = jsonObj.get("content").toString();	
				
				CommentVO cvo = new CommentVO(bno, writer, content);
				log.info(">>> cvo >>> {}", cvo);
				int isOK = csv.post(cvo);
				log.info("isOK >>> "+(isOK>0? "ok":"fail"));
				
				//결과 데이터 전송 -> 화면으로 전송(response 객체의 body에 기록)
				PrintWriter out = response.getWriter();
				out.print(isOK);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "list" :
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info(" >>> list parameter bno >>> "+bno);
				
				List<CommentVO> list = csv.getList(bno);
				log.info(" >>> list >>> {} ", list);
				
				//list -> json 변환 후 보내기
				//[{...},{...},{...}...] : [] JSONArray
				//{...} : JSONObject
				
				JSONArray jsonObjList = new JSONArray();
				JSONObject[] jsonObjArr = new JSONObject[list.size()];
				
				for(int i=0; i<list.size(); i++) {
					jsonObjArr[i] = new JSONObject();
					jsonObjArr[i].put("cno", list.get(i).getCno());
					jsonObjArr[i].put("bno", list.get(i).getBno());
					jsonObjArr[i].put("writer", list.get(i).getWriter());
					jsonObjArr[i].put("content", list.get(i).getContent());
					jsonObjArr[i].put("regdate", list.get(i).getRegdate());
					
					jsonObjList.add(jsonObjArr[i]);
				}
				//'[{...},{...},{...}...]' -> obj를 String으로 변환하여 전송(전송은 무조건 String)
				String jsonData = jsonObjList.toString();
				
				//print
				PrintWriter out = response.getWriter();
				out.print(jsonData); //jsonData = result	
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "modify" :
			try {
				StringBuffer sb = new StringBuffer();
				String line = "";
				BufferedReader br = request.getReader(); //댓글 객체가 들어있음
				while((line = br.readLine()) != null){
					sb.append(line);
				}
				log.info(">>> sb >>> {}", sb.toString());
				
				//이 값을 객체로 생성
				JSONParser parser = new JSONParser();
				
				//(JSONObject) 형변환 처리
				JSONObject jsonObj = (JSONObject)parser.parse(sb.toString()); //key:value 형태
				log.info(">>> jsonObj >>> {}", jsonObj.toJSONString());	
				
				int cno = Integer.parseInt(jsonObj.get("cno").toString());
				String content = jsonObj.get("content").toString();
				
				CommentVO cvo = new CommentVO(cno, content);
				
				int isOK = csv.modify(cvo);
				
				PrintWriter out = response.getWriter();
				out.print(isOK);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "remove" : 
			try {
				int cno = Integer.parseInt(request.getParameter("cno"));
				int isOK = csv.remove(cno);
				log.info("comment remove >>> {}", (isOK>0? "ok":"fail"));
				PrintWriter out = response.getWriter();
				out.print(isOK);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}
}
