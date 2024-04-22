package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import repository.CommentDAO;
import repository.CommentDAOImpl;

public class CommentServiceImpl implements CommentService {
	private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
	private CommentDAO cdao;
	
	public CommentServiceImpl() {
		cdao = new CommentDAOImpl();
	}

	@Override
	public int post(CommentVO cvo) {
		log.info("post service check 2");
		return cdao.insert(cvo);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		log.info("list service check 2");
		return cdao.selectList(bno);
	}

	@Override
	public int remove(int cno) {
		log.info("remove service check 2");
		return cdao.delete(cno);
	}

	@Override
	public int modify(CommentVO cvo) {
		log.info("modify service check 2");
		return cdao.update(cvo);
	}

	public int removeAll(int bno) {
		// TODO Auto-generated method stub
		return cdao.removeAll(bno);
	}
}
