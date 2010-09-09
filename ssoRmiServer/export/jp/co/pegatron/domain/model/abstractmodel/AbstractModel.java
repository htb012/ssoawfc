package jp.co.pegatron.domain.model.abstractmodel;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractModel entity provides the base persistence definition of the Model
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractModel implements java.io.Serializable {

	// Fields

	private Integer modelid;
	private String modelname;
	private String modelphoto;
	private String parameter;
	private String linkmark;
	private String modelextend;
	private Set repairorders = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractModel() {
	}

	/** minimal constructor */
	public AbstractModel(String modelname) {
		this.modelname = modelname;
	}

	/** full constructor */
	public AbstractModel(String modelname, String modelphoto, String parameter,
			String linkmark, String modelextend, Set repairorders) {
		this.modelname = modelname;
		this.modelphoto = modelphoto;
		this.parameter = parameter;
		this.linkmark = linkmark;
		this.modelextend = modelextend;
		this.repairorders = repairorders;
	}

	// Property accessors

	public Integer getModelid() {
		return this.modelid;
	}

	public void setModelid(Integer modelid) {
		this.modelid = modelid;
	}

	public String getModelname() {
		return this.modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	public String getModelphoto() {
		return this.modelphoto;
	}

	public void setModelphoto(String modelphoto) {
		this.modelphoto = modelphoto;
	}

	public String getParameter() {
		return this.parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getLinkmark() {
		return this.linkmark;
	}

	public void setLinkmark(String linkmark) {
		this.linkmark = linkmark;
	}

	public String getModelextend() {
		return this.modelextend;
	}

	public void setModelextend(String modelextend) {
		this.modelextend = modelextend;
	}

	public Set getRepairorders() {
		return this.repairorders;
	}

	public void setRepairorders(Set repairorders) {
		this.repairorders = repairorders;
	}

}