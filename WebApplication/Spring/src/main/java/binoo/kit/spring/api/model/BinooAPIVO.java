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
	
	// ADMIN / USER DATA 
	private String userId;								// 아이디
	private String userPw;								// 비밀번호
	private Integer passwordIncurrectCnt;				// 비밀번호 틀린횟수 (5회이상 로그인 제한)
	private Integer state;								// 계정상태 (1:정상/ 2: 휴면 / 3: 기간정지 / 4. 영구정지 / 5. 탈퇴요청)
	private String apiKey;								// 접근 키
	private String job;									// 직무/직업 구분. (ex 클라이언트의 캐릭터전사 : CW1000 , 서버의 모니터링 운영자 : SMO100 )
	private Integer level;								// 레벨. 유저만 가지는 속성
	private Integer gold;								// 골드. 유저만 가지는 속성
	
	// LOG DB
	// 1 2017/10/23-20:32:57.1923 Y 199.10.12.220 binoo [modified properties(item-drop=2, gold-grown=2)]
	private String procSeq;								// line 번호
	private String procTime;							// 처리시간
	private String procType;							// 로그구분
	private String procResult;							// 결과 (성공 : Y / 실패 : N)
	private String procMessage;							// 결과 메시지
	private String reqAddress;							// 요청 IP
	//private String userId;
	private String procContent;							// 처리/요구 내용
	
	// ITEM
	private String itemCode;							// 아이템 코드
	private String itemName;							// 아이템명
	private String itemType;							// 아이템 분류
	private Integer itemStatAtt;						// 공격 기본 스탯
	private Integer itemStatDef;						// 방어 기본 스탯
	private String itemDescription;						// 아이템 설명
	private String itemIcon;							// 아이템 아이콘 (ex 인벤토리에서 어떻게 보일지)
	private String itemImage;							// 아이템 이미지 (ex 마우스 오버시에 확대된, 또는 애니에이티브한 미디어의 경로)
	
	// ITEM_INSTANCE -> item in inventories
	private Integer inventorySeq; 						// PK
	//private String userId;							// 소유자 아이디 : 아이템은 버리는 즉시 파기되지 않을 수 있음.
	private Integer inventoryNumber; 					// 인벤토리 번호. 유저가 다수개의 인벤토리를 소유할 수도 있음.
	private Integer inventorySlotNumber; 				// 슬롯번호. 인벤토리를 확장된 1차배열로보고, 각 요소에 값을 출력하는 형태.
	
	// CHAT
	private Integer chatSeq;							// PK
	private String fromUserId;							// 보내는 사람
	private String toUserId;							// 받는 사람
	//private Integer state;							// 성격 (0: 전체공지/ 1:공지/ 2. 안내/ 3. 경고/ 4. 모두에게 채팅/ 5. 일반 채팅)
	private String chatTime;							// 보낸 시간
	private Integer chatRead;							// 읽었는지 (0:안읽음 / 1: 읽음)
	private Integer retry;								// 반복 (1: 안함/ 나머지는 횟수)
	private Integer chatTerm;							// 주기 (얼마정도의 시간뒤에 반복할지)
	private String chatContent;							// 내용
	
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
	public Integer getPasswordIncurrectCnt() {
		return passwordIncurrectCnt;
	}
	public void setPasswordIncurrectCnt(Integer passwordIncurrectCnt) {
		this.passwordIncurrectCnt = passwordIncurrectCnt;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getGold() {
		return gold;
	}
	public void setGold(Integer gold) {
		this.gold = gold;
	}
	public String getProcSeq() {
		return procSeq;
	}
	public void setProcSeq(String procSeq) {
		this.procSeq = procSeq;
	}
	public String getProcTime() {
		return procTime;
	}
	public void setProcTime(String procTime) {
		this.procTime = procTime;
	}
	public String getProcType() {
		return procType;
	}
	public void setProcType(String procType) {
		this.procType = procType;
	}
	public String getProcResult() {
		return procResult;
	}
	public void setProcResult(String procResult) {
		this.procResult = procResult;
	}
	public String getProcMessage() {
		return procMessage;
	}
	public void setProcMessage(String procMessage) {
		this.procMessage = procMessage;
	}
	public String getReqAddress() {
		return reqAddress;
	}
	public void setReqAddress(String reqAddress) {
		this.reqAddress = reqAddress;
	}
	public String getProcContent() {
		return procContent;
	}
	public void setProcContent(String procContent) {
		this.procContent = procContent;
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
	public String getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}
	public String getToUserId() {
		return toUserId;
	}
	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}
	public String getChatTime() {
		return chatTime;
	}
	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}
	public Integer getChatRead() {
		return chatRead;
	}
	public void setChatRead(Integer chatRead) {
		this.chatRead = chatRead;
	}
	public Integer getRetry() {
		return retry;
	}
	public void setRetry(Integer retry) {
		this.retry = retry;
	}
	public Integer getChatTerm() {
		return chatTerm;
	}
	public void setChatTerm(Integer chatTerm) {
		this.chatTerm = chatTerm;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public Integer getChatSeq() {
		return chatSeq;
	}
	public void setChatSeq(Integer chatSeq) {
		this.chatSeq = chatSeq;
	}
	
	
}
