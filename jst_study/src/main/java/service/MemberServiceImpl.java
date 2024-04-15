package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {
	//로그객체
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	//DAO 객체 연결
	private MemberDAO mdao; //repository -> interface
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl(); //repository -> class implement MemberDAO
	}
	@Override
	public int register(MemberVO mvo) {
		log.info("join service in!");
		return mdao.insert(mvo);
	}
	@Override
	public MemberVO login(MemberVO mvo) {
		log.info("login service in!");
		return mdao.getID(mvo);
	}
	@Override
	public int lastlogin(String id) {
		log.info("lastlogin service in!");
		return mdao.lastlogin(id);
	}
	@Override
	public List<MemberVO> getList() {
		log.info("list service in!");
		return mdao.selectList();
	}
	@Override
	public MemberVO getDetail(String id) {
		log.info("detail service in!");
		return mdao.selectOne(id);
	}
	@Override
	public int update(MemberVO mvo) {
		log.info("update service in!");
		return mdao.update(mvo);
	}
	@Override
	public int getRemove(String id) {
		log.info("delete service in!");
		return mdao.delete(id);
	}
}
