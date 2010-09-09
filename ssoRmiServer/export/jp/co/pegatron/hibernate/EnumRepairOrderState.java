/**
 * 
 */
package jp.co.pegatron.hibernate;

/**
 * 维修订单的状态 登録済み,連絡開始, 回答待ち,連絡完了, 更新済み, 修理完了, 業務終了;
 * 
 * @author HTB
 * 
 */
public enum EnumRepairOrderState implements java.io.Serializable {
	登録済み, 連絡開始, 回答待ち, 連絡完了, 更新済み, 修理完了, 業務終了;
}
