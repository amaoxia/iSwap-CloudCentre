package com.ligitalsoft.model.changemanage;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.common.framework.domain.LongIdObject;

/**
 * <strong>Java Doc:</strong><BR>
 * <BR>
 * <P>
 * <strong>Naming Rule: </strong><BR>
 * ...
 * <P>
 * 
 * @author fangbin
 * @version $Revision: 1.1 $Date: 11-8-18 下午3:51 15:51:31 $
 */

@Entity
@Table(name = "receive_result")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReceiveResult extends LongIdObject {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "item_code")
	private String itemCode;

	@Column(name = "item_name")
	private String itemName;

	@Column(name = "receive_dept_id")
	private String receiveDeptId; // 部门编码

	@Column(name = "receive_dept_nanme")
	private String receiveDeptName;

	@Column(name = "data_num")
	private int dataNum;

	@Column(name = "exchange_date")
	private Date exchangeDate;

	@Column(name = "create_date")
	private Date createDate = new Date();

	@Column(name = "sync_state")
	private int syncState = 0;// 同步状态 0 未同步 1已同步

	/**
	 * 待接收总量
	 */
	private int receNum;

	public int getReceNum() {
		return receNum;
	}

	public void setReceNum(int receNum) {
		this.receNum = receNum;
	}

	public int getSyncState() {
		return syncState;
	}

	public void setSyncState(int syncState) {
		this.syncState = syncState;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * @return return itemName
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * @param r_ItemName
	 *            set itemName
	 */
	public void setItemName(String r_ItemName) {
		this.itemName = r_ItemName;
	}

	/**
	 * @return return receiveDeptId
	 */
	public String getReceiveDeptId() {
		return this.receiveDeptId;
	}

	/**
	 * @param r_ReceiveDeptId
	 *            set receiveDeptId
	 */
	public void setReceiveDeptId(String r_ReceiveDeptId) {
		this.receiveDeptId = r_ReceiveDeptId;
	}

	/**
	 * @return return receiveDeptName
	 */
	public String getReceiveDeptName() {
		return this.receiveDeptName;
	}

	/**
	 * @param r_ReceiveDeptName
	 *            set receiveDeptName
	 */
	public void setReceiveDeptName(String r_ReceiveDeptName) {
		this.receiveDeptName = r_ReceiveDeptName;
	}

	/**
	 * @return return dataNum
	 */
	public int getDataNum() {
		return this.dataNum;
	}

	/**
	 * @param r_DataNum
	 *            set dataNum
	 */
	public void setDataNum(int r_DataNum) {
		this.dataNum = r_DataNum;
	}

	public Date getExchangeDate() {
		return exchangeDate;
	}

	public void setExchangeDate(Date exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}