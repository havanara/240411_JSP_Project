package repository;

import java.util.List;

import domain.CommentVO;

public interface CommentDAO {

	int insert(CommentVO cvo);

	List<CommentVO> selectList(int bno);

	int delete(int cno);

	int update(CommentVO cvo);

	int removeAll(int bno);

}
