package binoo.kit.spring.cmmn;

import java.util.ArrayList;

/**
 * @Class Name : binooMapper
 * @Description : cmmnDAO
 * @author botbinoo@naver.com
 * @since 2017.09.05
 * https://github.com/wjsalsdn/homeProject_Maven_1_cloud
 * @version test
 *
 *  Copyright (C) by botbinoo's All right reserved.
 */
@SuppressWarnings("rawtypes")
public interface BinooMapper {
	public void insertItem(BinooCommonVO item) throws Exception;
	public void updateItem(BinooCommonVO item) throws Exception;
	public void deleteItem(BinooCommonVO item) throws Exception;
	public BinooCommonVO selectItem(BinooCommonVO item) throws Exception;
	public ArrayList selectItemList(BinooCommonVO item) throws Exception;
	public int selectItemListTotCnt(BinooCommonVO item) throws Exception;
}
