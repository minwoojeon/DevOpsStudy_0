package binoo.kit.spring.cmmn;

import java.io.Serializable;

/**
 * @Class Name : BinooCommonVO
 * @Description : Default Model
 * @author botbinoo@naver.com
 * @since 2017.10.24
 * @version test
 *
 *  Copyright (C) by botbinoo's All right reserved.
 */

public class BinooCommonVO extends Object implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6067337497535413923L;
	
	private String search_sttTime;
	private String search_endTime;
	private String search_keyword;
	private String search_dep;
	private Integer search_index;
	
	public String getSearch_sttTime() {
		return search_sttTime;
	}
	public void setSearch_sttTime(String search_sttTime) {
		this.search_sttTime = search_sttTime;
	}
	public String getSearch_endTime() {
		return search_endTime;
	}
	public void setSearch_endTime(String search_endTime) {
		this.search_endTime = search_endTime;
	}
	public String getSearch_keyword() {
		return search_keyword;
	}
	public void setSearch_keyword(String search_keyword) {
		this.search_keyword = search_keyword;
	}
	public String getSearch_dep() {
		return search_dep;
	}
	public void setSearch_dep(String search_dep) {
		this.search_dep = search_dep;
	}
	public Integer getSearch_index() {
		return search_index;
	}
	public void setSearch_index(Integer search_index) {
		this.search_index = search_index;
	}
}
