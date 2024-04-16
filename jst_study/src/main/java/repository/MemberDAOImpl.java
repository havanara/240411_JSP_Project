package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import orm.DatabaseBuilder;
public class MemberDAOImpl implements MemberDAO {
	//로그객체
	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	//sqkSession 객체
	private SqlSession sql;
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	//namespace -> mapper의 이름
	//namespace="MemberMapper"
	//MemberMapper.id
	
	@Override
	public int insert(MemberVO mvo) {
		log.info("join dao in!");
		int isOK = sql.insert("MemberMapper.add", mvo);
		
		if(isOK > 0) {
			sql.commit();
		}
		return isOK;
	}

	@Override
	public MemberVO getID(MemberVO mvo) {
		log.info("login dao in!");
		return sql.selectOne("MemberMapper.login", mvo);
	}

	@Override
	public int lastlogin(String id) {
		log.info("lastlogin dao in!");
		int isOK = sql.update("MemberMapper.last", id);
		if(isOK > 0) {
			sql.commit();
		}
		return isOK;
	}

	@Override
	public List<MemberVO> selectList() {
		log.info("list dao in!");
		return sql.selectList("MemberMapper.list");
	}

	@Override
	public MemberVO selectOne(String id) {
		log.info("selectOne dao in!");
		return sql.selectOne("MemberMapper.detail",id);
	}

	@Override
	public int update(MemberVO mvo) {
		log.info("update dao in!");
		//insert, update, delete는 commit이 없으면 성공은 하지만 반영이 안됨
		int isOK = sql.update("MemberMapper.update", mvo);
		if(isOK > 0) {
			sql.commit();
		}
		return isOK;
	}

	@Override
	public int delete(String id) {
		log.info("delete dao in!");
		int isOK = sql.delete("MemberMapper.delete", id);
		if(isOK > 0) {
			sql.commit();
		}
		return isOK;
	}
	
}
