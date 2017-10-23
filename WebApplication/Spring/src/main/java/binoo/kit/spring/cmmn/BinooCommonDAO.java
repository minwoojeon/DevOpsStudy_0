package binoo.kit.spring.cmmn;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @Class Name : BinooCommonDAO
 * @Description : Common DAO Component
 * @author botbinoo@naver.com
 * @since 2017.09.05
 * https://github.com/wjsalsdn/homeProject_Maven_1_cloud
 * @version test
 *
 *  Copyright (C) by botbinoo's All right reserved.
 */

@Component
@SuppressWarnings("rawtypes")
public class BinooCommonDAO extends SqlSessionDaoSupport implements BinooMapper{

	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BinooCommonDAO.class);
	
	public void insertItem(BinooCommonVO vo) throws Exception{
		getSqlSession().insert(vo.getClass().getName()+".insertItem", vo);
	}
	public void updateItem(BinooCommonVO vo) throws Exception{
		getSqlSession().update(vo.getClass().getName()+".updateItem", vo);
	}
	public void deleteItem(BinooCommonVO vo) throws Exception{
		getSqlSession().delete(vo.getClass().getName()+".deleteItem", vo);
	}
	public BinooCommonVO selectItem(BinooCommonVO vo) throws Exception{
		return getSqlSession().selectOne(vo.getClass().getName()+".selectItem", vo);
	}
	public ArrayList selectItemList(BinooCommonVO vo) throws Exception{
		return (ArrayList) getSqlSession().selectList(vo.getClass().getName()+".selectItemList", vo);
	}
	public int selectItemListTotCnt(BinooCommonVO vo) throws Exception{
		return getSqlSession().selectOne(vo.getClass().getName()+".selectItemListTotCnt", vo);
	}
}
