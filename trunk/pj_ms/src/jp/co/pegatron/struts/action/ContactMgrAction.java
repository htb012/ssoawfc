package jp.co.pegatron.struts.action;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.pegatron.domain.model.Contact;
import jp.co.pegatron.domain.model.Repairorder;
import jp.co.pegatron.domain.model.User;
import jp.co.pegatron.hibernate.EnumRepairOrderState;
import jp.co.pegatron.service.ContactSvc;
import jp.co.pegatron.service.RepairOrderSvc;
import jp.co.pegatron.struts.form.ContactForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ssoserver.business.userMgr.UserMgr;
import com.ssoserver.common.rmi.UserMgrFactory;

/**
 * MyEclipse Struts Creation date: 02-28-2010
 * 
 * XDoclet definition:
 * 
 * @struts.action path="contactMgr" name="RepairorderForm" scope="request"
 * @struts.action-forward name="safeSuc" path="/common/safeSuccess.jsp"
 * @struts.action-forward name="contact" path="/systemRepair/contact.jsp"
 */
public class ContactMgrAction extends Action {
	/*
	 * Generated Methods
	 */
	public ContactMgrAction() {

	}

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String task = request.getParameter("task");
		if ("view".equals(task)) {
			String id = request.getParameter("id");
			Repairorder order = RepairOrderSvc.getInstance().findById(
					new Integer(id));
			String[] photoArray = order.getPhoto().split(";");
			Collection<String> photos = new ArrayList<String>();
			if (!"".equals(order.getPhoto())) {
				for (int i = 0; i < photoArray.length; i++) {
					photos.add("uploadfile/" + photoArray[i]);
				}
			}
			request.setAttribute("photos", photos);
			request.setAttribute("repairCount", RepairOrderSvc.getInstance()
					.findBySn(order.getSn()).size());
			request.setAttribute("repairorder", order);
			request.setAttribute("contacts", order.getContacts());
			return mapping.findForward("contact");
		} else if ("add".equals(task)) {
			ContactForm contactForm = (ContactForm) form;
			Contact contact = contactForm.getContact();
			String secKey = request.getSession().getAttribute(UserMgr.SECKEY)
					.toString();
			User user = null;
			try {
				user = UserMgrFactory.getUserMgr().getCurrentUser(secKey);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			contact.setUser(user);
			ContactSvc.getInstance().save(contact);
			Repairorder order = RepairOrderSvc.getInstance().findById(
					new Integer(contactForm.getRepairorderid()));
			order.setValidate("" + contactForm.getContactvalidateInt());
			order.setRepairorderstate(EnumRepairOrderState.valueOf(contactForm
					.getRepairorderstate()));
			RepairOrderSvc.getInstance().update(order);
			return mapping.findForward("safeSuc");
		}
		return null;
	}
}