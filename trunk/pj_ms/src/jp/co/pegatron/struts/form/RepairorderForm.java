package jp.co.pegatron.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.ssoserver.utils.FormatDate;
import com.ssoserver.utils.GetPropMessage;

import jp.co.pegatron.domain.model.Agent;
import jp.co.pegatron.domain.model.Customer;
import jp.co.pegatron.domain.model.Repairorder;
import jp.co.pegatron.service.AgentSvc;
import jp.co.pegatron.service.CustomerSvc;
import jp.co.pegatron.service.ModelSvc;
import jp.co.pegatron.service.RepairOrderSvc;

public class RepairorderForm extends ActionForm {
	private Repairorder repairorder;
	private int repairorderid;
	private int customerid;
	private String customername;
	private int agentid;
	private String agentname;
	private int modelid;
	private String modelname;
	private String rmano;
	private String sn;
	private String location;
	private String parts;
	private String pn;
	private String[] warranty = new String[0];
	private String waitparts;
	private String[] validate = new String[0];
	private String problem;
	private String isagentrepair;
	private String repairiordergroup;
	private String startdate;
	private String checkindatetime;

	/**
	 * 
	 */
	public RepairorderForm() {
		super();
		this.repairorder = new Repairorder();
	}

	/**
	 * 填充Form中的数据到Repairorder对象中。
	 * 
	 * @param repairorder
	 *            需要填充的Repairorder对象
	 * @return
	 */
	public Repairorder buildRepairorder(Repairorder repairorder) {
		repairorder.setRepairorderid(repairorderid);
		repairorder.setRmano(rmano);
		if ("1".equals(isagentrepair)) {
			repairorder.setIsagentrepair(Byte.valueOf((byte) 1));
			if (agentid != 0) {
				repairorder.setAgent(AgentSvc.getInstance().findById(
						new Integer(agentid)));
			}
		} else {
			repairorder.setIsagentrepair(Byte.valueOf((byte) 0));
			if (customerid != 0) {
				Customer customer = CustomerSvc.getInstance().findById(
						new Integer(customerid));
				customer.setCustomername(customername);
				repairorder.setCustomer(customer);
			}
		}
		repairorder.setModel(ModelSvc.getInstance().findById(
				new Integer(modelid)));
		repairorder.setValidate("" + this.getValidateInt());
		repairorder.setProblem(problem);
		repairorder.setParts(parts);
		repairorder.setPn(pn);
		repairorder.setWarranty("" + this.getWarrantyInt());
		repairorder.setWaitparts(waitparts);
		repairorder.setSn(sn);
		repairorder.setLocation(location);
		if (startdate != null && !"".equals(startdate)) {
			repairorder.setStartdate(FormatDate.parseStringToDate(startdate));
		}
		if (checkindatetime != null && !"".equals(this.checkindatetime)) {
			repairorder.setCheckindatetime(FormatDate
					.parseStringToDateTime(this.checkindatetime));
		}
		return repairorder;
	}

	/**
	 * 从Form中填充，获取新的Repairorder对象
	 * 
	 * @return the repairorder
	 */
	public Repairorder getRepairorder() {
		return this.buildRepairorder(this.repairorder);
	}

	/**
	 * @param repairorder
	 *            the repairorder to set
	 */
	public void setRepairorderFrom(Repairorder repairorder) {
		this.repairorder = repairorder;
		repairorderid = repairorder.getRepairorderid();
		rmano = repairorder.getRmano();
		isagentrepair = repairorder.getIsagentrepair().toString();
		if ("1".equals(isagentrepair)) {
			agentid = repairorder.getAgent().getAgentid();
			agentname = repairorder.getAgent().getAgentname();
		} else {
			customerid = repairorder.getCustomer().getCustomerid();
			customername = repairorder.getCustomer().getCustomername();
		}

		if (repairorder.getModel() != null) {
			modelid = repairorder.getModel().getModelid();
			modelname = repairorder.getModel().getModelname();
		}

		sn = repairorder.getSn();
		if (repairorder.getCheckindatetime() != null
				&& !"".equals(repairorder.getCheckindatetime())) {
			checkindatetime = FormatDate.formatDateTimeToString(repairorder
					.getCheckindatetime());
		}
		location = repairorder.getLocation();
		parts = repairorder.getParts();
		pn = repairorder.getPn();
		this.setWarranty(repairorder.getWarranty());
		waitparts = repairorder.getWaitparts();
		this.setValidate(repairorder.getValidate());

		problem = repairorder.getProblem();
		if (repairorder.getOrganization() != null) {
			repairiordergroup = repairorder.getOrganization().getOrgname();
		}
		startdate = FormatDate.formatDateToString(repairorder.getStartdate());
	}

	/**
	 * @return the customername
	 */
	public String getCustomername() {
		return customername;
	}

	/**
	 * @param customername
	 *            the customername to set
	 */
	public void setCustomername(String customername) {
		this.customername = customername;
	}

	/**
	 * @return the agentname
	 */
	public String getAgentname() {
		return agentname;
	}

	/**
	 * @param agentname
	 *            the agentname to set
	 */
	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}

	/**
	 * @return the modelname
	 */
	public String getModelname() {
		return modelname;
	}

	/**
	 * @param modelname
	 *            the modelname to set
	 */
	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	/**
	 * @return the rmano
	 */
	public String getRmano() {
		return rmano;
	}

	/**
	 * @param rmino
	 *            the rmano to set
	 */
	public void setRmano(String rmano) {
		this.rmano = rmano;
	}

	/**
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}

	/**
	 * @param sn
	 *            the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * @return the parts
	 */
	public String getParts() {
		return parts;
	}

	/**
	 * @param parts
	 *            the parts to set
	 */
	public void setParts(String parts) {
		this.parts = parts;
	}

	/**
	 * @return the pn
	 */
	public String getPn() {
		return pn;
	}

	/**
	 * @param pn
	 *            the pn to set
	 */
	public void setPn(String pn) {
		this.pn = pn;
	}

	/**
	 * @return the warranty
	 */
	public String[] getWarranty() {
		return warranty;
	}

	public int getWarrantyInt() {
		int warrantyInt = 0;
		for (int i = 0; i < warranty.length; i++) {
			warrantyInt += new Integer(warranty[i]).intValue();
		}
		return warrantyInt;
	}

	/**
	 * @param warranty
	 *            the warranty to set
	 */
	public void setWarranty(String[] warranty) {
		this.warranty = warranty;
	}

	/**
	 * @param warranty
	 *            the warranty to set
	 */
	public void setWarranty(String warrantyStr) {
		if (warrantyStr != null && !"".equals(warrantyStr)) {
			warranty = new String[10];
			int temp = 1;
			for (int i = 0; i < warrantyStr.length(); i++) {
				if ('1' == warrantyStr.charAt(warrantyStr.length() - 1 - i)) {
					warranty[i] = "" + temp;
				}
				temp *= 10;
			}
		}
	}

	/**
	 * @return the waitparts
	 */
	public String getWaitparts() {
		return waitparts;
	}

	/**
	 * @param waitparts
	 *            the waitparts to set
	 */
	public void setWaitparts(String waitparts) {
		this.waitparts = waitparts;
	}

	/**
	 * @return the problem
	 */
	public String getProblem() {
		return problem;
	}

	/**
	 * @param problem
	 *            the problem to set
	 */
	public void setProblem(String problem) {
		this.problem = problem;
	}

	/**
	 * @return the isagentrepair
	 */
	public String getIsagentrepair() {
		return isagentrepair;
	}

	/**
	 * @param isagentrepair
	 *            the isagentrepair to set
	 */
	public void setIsagentrepair(String isagentrepair) {
		this.isagentrepair = isagentrepair;
	}

	/**
	 * @return the repairiordergroup
	 */
	public String getRepairiordergroup() {
		return repairiordergroup;
	}

	/**
	 * @param repairiordergroup
	 *            the repairiordergroup to set
	 */
	public void setRepairiordergroup(String repairiordergroup) {
		this.repairiordergroup = repairiordergroup;
	}

	/**
	 * @return the startdate
	 */
	public String getStartdate() {
		return startdate;
	}

	/**
	 * @param startdate
	 *            the startdate to set
	 */
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the checkindatetime
	 */
	public String getCheckindatetime() {
		return checkindatetime;
	}

	/**
	 * @param checkindatetime
	 *            the checkindatetime to set
	 */
	public void setCheckindatetime(String checkindatetime) {
		this.checkindatetime = checkindatetime;
	}

	/**
	 * @return the customerid
	 */
	public int getCustomerid() {
		return customerid;
	}

	/**
	 * @param customerid
	 *            the customerid to set
	 */
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	/**
	 * @return the agentid
	 */
	public int getAgentid() {
		return agentid;
	}

	/**
	 * @param agentid
	 *            the agentid to set
	 */
	public void setAgentid(int agentid) {
		this.agentid = agentid;
	}

	/**
	 * @return the modelid
	 */
	public int getModelid() {
		return modelid;
	}

	/**
	 * @param modelid
	 *            the modelid to set
	 */
	public void setModelid(int modelid) {
		this.modelid = modelid;
	}

	/**
	 * @return the repairorderid
	 */
	public int getRepairorderid() {
		return repairorderid;
	}

	/**
	 * @param repairorderid
	 *            the repairorderid to set
	 */
	public void setRepairorderid(int repairorderid) {
		this.repairorderid = repairorderid;
	}

	/**
	 * @return the validate
	 */
	public String[] getValidate() {

		return validate;
	}

	/**
	 * @return the validate
	 */
	public int getValidateInt() {
		int validateInt = 0;
		for (int i = 0; i < validate.length; i++) {
			validateInt += new Integer(validate[i]).intValue();
		}
		return validateInt;
	}

	/**
	 * @param validate
	 *            the validate to set
	 */
	public void setValidate(String[] validate) {
		this.validate = validate;
	}

	public void setValidate(String validateStr) {
		validate = new String[10];
		int temp = 1;
		for (int i = 0; i < validateStr.length(); i++) {
			if ('1' == validateStr.charAt(validateStr.length() - 1 - i)) {
				validate[i] = "" + temp;
			}
			temp *= 10;
		}
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.reset(mapping, request);
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if ("0".equals(isagentrepair) && "".equals(customername)) {
			errors.add("customername", new ActionMessage(
					"repairorderForm.customername", "请输入客户名"));
		}
		if ("1".equals(isagentrepair) && "".equals(agentname)) {
			errors.add("agentname", new ActionMessage(
					"repairorderForm.agentname", "请选择代理商"));
		}
		if (location == null || "".equals(location)) {
			errors.add("location",
					new ActionMessage("repairorderForm.location"));
		}
		if (!errors.isEmpty()) {
			return errors;
		} else {
			return super.validate(mapping, request);
		}
	}
}
