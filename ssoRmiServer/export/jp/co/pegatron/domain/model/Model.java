package jp.co.pegatron.domain.model;

import java.util.Set;

import jp.co.pegatron.domain.model.abstractmodel.AbstractModel;

/**
 * Model entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Model extends AbstractModel implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Model() {
	}

	/** minimal constructor */
	public Model(String modelname) {
		super(modelname);
	}

	/** full constructor */
	public Model(String modelname, String modelphoto, String parameter,
			String linkmark, String modelextend, Set repairorders) {
		super(modelname, modelphoto, parameter, linkmark, modelextend,
				repairorders);
	}

}
