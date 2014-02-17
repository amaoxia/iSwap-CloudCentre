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
 * @version $Revision: 1.1 $Date: 11-8-18 下午3:38 15:38:57 $
 */

@Entity
@Table(name = "send_result")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SendResult extends LongIdObject {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "type")
	private String type;

	@Column(name = "item_code")
	private String itemCode;

	@Column(name = "item_name")
	private String itemName;

	@Column(name = "dept_id")
	private String deptId;// 部门UID

	@Column(name = "dept_name")
	private String deptName;

	@Column(name = "data_num")
	private int dataNum;

	@Column(name = "exchange_date")
	private Date exchangeDate;

	@Column(name = "createdate")
	private Date createdate = new Date();

	@Column(name = "sync_state")
	private int syncState = 0;// 同步状态 0 未同步 1已同步
	/**
	 * 待发送数量
	 */
	private int  payNum;

	

	public int getPayNum() {
		return payNum;
	}

	public void setPayNum(int payNum) {
		this.payNum = payNum;
	}

	public int getSyncState() {
		return syncState;
	}

	public void setSyncState(int syncState) {
		this.syncState = syncState;
	}

	/**
	 * @return return type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * @param r_Type
	 *            set type
	 */
	public void setType(String r_Type) {
		this.type = r_Type;
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
	 * @return return deptId
	 */
	public String getDeptId() {
		return this.deptId;
	}

	/**
	 * @param r_DeptId
	 *            set deptId
	 */
	public void setDeptId(String r_DeptId) {
		this.deptId = r_DeptId;
	}

	/**
	 * @return return deptName
	 */
	public String getDeptName() {
		return this.deptName;
	}

	/**
	 * @param r_DeptName
	 *            set deptName
	 */
	public void setDeptName(String r_DeptName) {
		this.deptName = r_DeptName;
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

	/**
	 * @return return exchangeDate
	 */
	public java.util.Date getExchangeDate() {
		return this.exchangeDate;
	}

	/**
	 * @param r_ExchangeDate
	 *            set exchangeDate
	 */
	public void setExchangeDate(java.util.Date r_ExchangeDate) {
		this.exchangeDate = r_ExchangeDate;
	}

	/**
	 * @return return createdate
	 */
	public Date getCreatedate() {
		return this.createdate;
	}

	/**
	 * @param r_Createdate
	 *            set createdate
	 */
	public void setCreatedate(Date r_Createdate) {
		this.createdate = r_Createdate;
	}

}