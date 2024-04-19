package handler;

import domain.PagingVO;

public class PagingHandler {
	//list 하단에 나오는 페이지네이션 핸들링 클래스
	private int startPage; //현재 한 화면에서 보여줄 시작 페이지네이션 번호 1번부터 시작 다음페이지는 11 그 다음페이지는 21... 규칙
	private int endPage; //현재 한 화면에서 보여줄 끝 페이지네이션 번호 10 20 30 ... 규칙
	private int realEndPage; //실제 전체 리스트의 마지막 페이지 번호
	private boolean prev; //이전 페이지의 존재 여부 시작페이지가 1일 경우는 없음 11 21 31 ... 규칙 (있냐 없냐 true false)
	private boolean next; //다음 페이지의 존재 여부 realEndPage일 경우는 없음 그 이전에는 있음
	
	//선택한것을 받아와야 하는 것들
	private PagingVO pgvo; //파라미터로 현재 사용자가 클릭한 값을 받아오기
	private int totalCount; //DB에서 검색해오기 //컨트롤러에서 매개변수로 받아오기
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		//1~10 / 11~20 / 21~30 ...
		//1~10 -> 1 2 3 4 5 6 7 8 9 10 -> 10
		//pageNo/(나누기)10 -> 0.1(올림처리, 반올림(x)) -> 값이 1이 됨 거기에 * 10 -> 10
		//11~20 -> 11 12 13 14 15 16 17 18 19 20 -> 20
		//pageNo/(나누기)10 -> 1.1(올림처리, 반올림(x)) -> 값이 2이 됨 거기에 * 10 -> 20
		//21~30 -> 21 22 23 24 25 26 27 28 29 30 -> 30
		//pageNo/(나누기)10 -> 2.1(올림처리, 반올림(x)) -> 값이 3이 됨 거기에 * 10 -> 30
		
		//정수/정수=정수값이 나오기 때문에 0.1이 나오려면 형변환이 필요
		//10을 곱하는 이유 -> 한페이지에 나타내는 페이징 값이 10개라는 뜻
		this.endPage = (int)Math.ceil(pgvo.getPageNo() / (double)10)*10;
		
		this.startPage = this.endPage - 9;
		
		//전체 게시글 수 / 한 화면에 표시되는 게시글 수
		//ex)게시글 수 103개 라면 103/10 -> 10.3 남는 3개의 게시글도 한페이지에 넣어야 함
		//11페이지 생성(올림)
		//나머지 게시글이 하나라도 있다면 1페이지가 더 생겨야 함
		this.realEndPage = (int)Math.ceil(totalCount/(double)pgvo.getQty());
		
		//1~10 / 11~20
		//실제 리얼 마지막 페이지가 11페이지라면 11 끝
		//진짜 끝 페이지가 endPage와 같지 않을 경우
		if(this.realEndPage < this.endPage) {
			this.endPage = this.realEndPage;
		}
		
		//이전 다음 유무
		this.prev = startPage > 1;
		this.next = this.endPage < this.realEndPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getRealEndPage() {
		return realEndPage;
	}

	public void setRealEndPage(int realEndPage) {
		this.realEndPage = realEndPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public PagingVO getPgvo() {
		return pgvo;
	}

	public void setPgvo(PagingVO pgvo) {
		this.pgvo = pgvo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "PagingHandler [startPage=" + startPage + ", endPage=" + endPage + ", realEndPage=" + realEndPage
				+ ", prev=" + prev + ", next=" + next + ", pgvo=" + pgvo + ", totalCount=" + totalCount + "]";
	}
	
}
