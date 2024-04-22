package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.CommentController;
import domain.CommentVO;
import orm.DatabaseBuilder;

public class CommentDAOImpl implements CommentDAO {
	private static final Logger log = LoggerFactory.getLogger(CommentDAOImpl.class);
	private SqlSession sql;
	
	public CommentDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(CommentVO cvo) {
		log.info("post DAO check 3");
		int isOK = sql.insert("CommentMapper.post", cvo);
		if(isOK>0) {
			sql.commit();
		}
		return isOK;
	}

	@Override
	public List<CommentVO> selectList(int bno) {
		log.info("list DAO check 3");
		return sql.selectList("CommentMapper.list", bno);
	}

	@Override
	public int delete(int cno) {
		log.info("delete DAO check 3");
		int isOK = sql.delete("CommentMapper.delete", cno);
		if(isOK>0) {
			sql.commit();
		}
		return isOK;
	}

	@Override
	public int update(CommentVO cvo) {
		log.info("modify DAO check 3");
		int isOK = sql.update("CommentMapper.modify", cvo);
		if(isOK>0) {
			sql.commit();
		}
		return isOK;
	}

	@Override
	public int removeAll(int bno) {
		// TODO Auto-generated method stub
		int isOK = sql.delete("CommentMapper.all", bno);
		if(isOK>=0) {
			sql.commit();
		}
		return isOK;
	}
}
