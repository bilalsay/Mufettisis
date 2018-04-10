package dev.demo.Services;

import dev.demo.Controller.CrudController;
import dev.demo.Entity.AncestorEntity;
import dev.demo.Services.SpecialEnums.CrudTypeEnum;

public class StepBackwardContent {
	
	private AncestorEntity parameter;
	private CrudTypeEnum crudType;
	private CrudController fromController;
	private CrudController toController;
	
	public StepBackwardContent(AncestorEntity parameter, CrudTypeEnum crudType, CrudController fromController, CrudController toController) {
		this.parameter = parameter;
		this.fromController = fromController;
		this.toController = toController;
		this.crudType = crudType;
	}
	
	public AncestorEntity getParameter() {
		return parameter;
	}

	public void setParameter(AncestorEntity parameter) {
		this.parameter = parameter;
	}

	public CrudTypeEnum getCrudType() {
		return crudType;
	}

	public void setCrudType(CrudTypeEnum crudType) {
		this.crudType = crudType;
	}

	public CrudController getFromController() {
		return fromController;
	}

	public void setFromController(CrudController fromController) {
		this.fromController = fromController;
	}

	public CrudController getToController() {
		return toController;
	}

	public void setToController(CrudController toController) {
		this.toController = toController;
	}
}
