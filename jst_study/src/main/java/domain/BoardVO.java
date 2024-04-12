package domain;

public class BoardVO {
	private int bno;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private String moddate;
	
	public BoardVO() {}
	
	public BoardVO(String title, String writer, String content) {
		//insert : title, writer, content
		this.title = title;
		this.writer = writer;
		this.content = content;
	}
	public BoardVO(int bno, String title, String writer, String content, String regdate) {
		//list : bno, title, writer, regdate
		this(title,writer,content);
		this.bno = bno;
		this.regdate = regdate;
	}
	public BoardVO(int bno, String title, String content) {
		//update : bno, title, content
		this.bno = bno;
		this.title = title;
		this.content = content;
	}
	public BoardVO(int bno, String title, String writer, String content, String regdate, String moddate) {
		//detail : bno, title, writer, content, regdate, moddate
		this(bno,title,writer,content,regdate);
		this.moddate = moddate;
	}
	
	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getModdate() {
		return moddate;
	}

	public void setModdate(String moddate) {
		this.moddate = moddate;
	}

	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + ", moddate=" + moddate + "]";
	}
	
	
	
	
}
