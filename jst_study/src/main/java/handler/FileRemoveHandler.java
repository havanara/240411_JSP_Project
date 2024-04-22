package handler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.BoardController;

public class FileRemoveHandler {
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	//savePath, imageFileName를 매개 변수로 받아 파일을 삭제하는 메서드
	public int deleteFile(String savePath, String imageFileName) {
		//return type int -> 삭제 여부 값을 리턴
		
		 boolean isDel = false; //삭제가 잘 되었는지 체크하는 변수
		 log.info(">>> deleteFile method 접근 {}", imageFileName);
		 
		 //기존 저장 파일 + _th_(썸네일파일) 같이 삭제
		 File fileDir = new File(savePath);
		 File removeFile = new File(fileDir+File.separator+imageFileName);
		 File removeThumbFile = new File(fileDir+File.separator+"_th_"+imageFileName);
		 
		 //파일이 존재해야 삭제
		 if(removeFile.exists() || removeThumbFile.exists()) {
			 isDel = removeFile.delete(); //원 파일 삭제
			 log.info(">>> fileRemove >>> {}", isDel);
			 
			 if(isDel) {
				 isDel = removeThumbFile.delete(); //썸네일 파일 삭제
				 log.info(">>> fileRemoveThumbFile >>> {}", isDel);
			 }
		 }
		 return isDel? 1:0;
	}
}
