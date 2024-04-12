package orm;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DatabaseBuilder {

	private static SqlSessionFactory factory;
	//final은 대문자로 작성해야함
	private static final String CONFIG = "orm/mybatisConfig.xml";
	
	//초기화 블럭 사용하여 객체 생성 후 초기화
	static {
		try {
			factory = new SqlSessionFactoryBuilder().build(
					Resources.getResourceAsReader(CONFIG));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//매번 생성하게 만들지 말고 리턴하게끔 만듦
	public static SqlSessionFactory getFactory() {
		return factory;
	}
}
