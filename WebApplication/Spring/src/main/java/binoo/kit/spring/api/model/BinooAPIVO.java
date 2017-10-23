package binoo.kit.spring.api.model;

import binoo.kit.spring.cmmn.BinooCommonVO;

/**
 * @Class Name : BinooAPIVO
 * @Description : Default Model
 * @author botbinoo@naver.com
 * @since 2017.10.22
 * @version test
 *
 *  Copyright (C) by botbinoo's All right reserved.
 */

public class BinooAPIVO extends BinooCommonVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1131906072724377315L;
	
	// ADMIN_DATA 
	private String operatorId;
	private String operatorPw;
	private String operatorAPIKey;
	private String lastAccessIp;
	private Integer errorPwCnt;
	
	// ADMIN_PROCCESSING_LOG
	private String proc_time;
	private String proc_content;
	private String proc_result;
	private String proc_result_code;
	
	// USER_TABLE 
	private String userId;
	private String userPw;
	private String userLevel;
	private String gold;
	
	// ITEM
	private String itemCode;
	private String itemName;
	private String itemType;
	private Integer itemStatAtt;
	private Integer itemStatDef;
	private String itemDescription;
	private String itemIcon;
	private String itemImage;
	
	// ITEM_INSTANCE -> item in inventories
	private Integer inventorySeq; // PK
	private Integer inventoryNumber; // 인벤토리 번호. 예를들면 유저 A가 가진 가방이 여러개일수 있음. 그런 묶음관리를 할 값.
	private Integer inventorySlotNumber; // 
	
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorPw() {
		return operatorPw;
	}
	public void setOperatorPw(String operatorPw) {
		this.operatorPw = operatorPw;
	}
	public String getOperatorAPIKey() {
		return operatorAPIKey;
	}
	public void setOperatorAPIKey(String operatorAPIKey) {
		this.operatorAPIKey = operatorAPIKey;
	}
	public String getLastAccessIp() {
		return lastAccessIp;
	}
	public void setLastAccessIp(String lastAccessIp) {
		this.lastAccessIp = lastAccessIp;
	}
	public Integer getErrorPwCnt() {
		return errorPwCnt;
	}
	public void setErrorPwCnt(Integer errorPwCnt) {
		this.errorPwCnt = errorPwCnt;
	}
	public String getProc_time() {
		return proc_time;
	}
	public void setProc_time(String proc_time) {
		this.proc_time = proc_time;
	}
	public String getProc_content() {
		return proc_content;
	}
	public void setProc_content(String proc_content) {
		this.proc_content = proc_content;
	}
	public String getProc_result() {
		return proc_result;
	}
	public void setProc_result(String proc_result) {
		this.proc_result = proc_result;
	}
	public String getProc_result_code() {
		return proc_result_code;
	}
	public void setProc_result_code(String proc_result_code) {
		this.proc_result_code = proc_result_code;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public String getGold() {
		return gold;
	}
	public void setGold(String gold) {
		this.gold = gold;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public Integer getItemStatAtt() {
		return itemStatAtt;
	}
	public void setItemStatAtt(Integer itemStatAtt) {
		this.itemStatAtt = itemStatAtt;
	}
	public Integer getItemStatDef() {
		return itemStatDef;
	}
	public void setItemStatDef(Integer itemStatDef) {
		this.itemStatDef = itemStatDef;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getItemIcon() {
		return itemIcon;
	}
	public void setItemIcon(String itemIcon) {
		this.itemIcon = itemIcon;
	}
	public String getItemImage() {
		return itemImage;
	}
	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
	public Integer getInventorySeq() {
		return inventorySeq;
	}
	public void setInventorySeq(Integer inventorySeq) {
		this.inventorySeq = inventorySeq;
	}
	public Integer getInventoryNumber() {
		return inventoryNumber;
	}
	public void setInventoryNumber(Integer inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}
	public Integer getInventorySlotNumber() {
		return inventorySlotNumber;
	}
	public void setInventorySlotNumber(Integer inventorySlotNumber) {
		this.inventorySlotNumber = inventorySlotNumber;
	}
	
}
