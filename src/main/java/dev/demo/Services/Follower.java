package dev.demo.Services;

import java.util.Stack;
import dev.demo.Controller.CrudController;
import dev.demo.Services.SpecialEnums.CrudTypeEnum;

public class Follower {
	
	public static Stack<StepBackwardContent> stack = new Stack<>();
	
	public static void setStepBackward() {
		if (!Follower.stack.isEmpty()) {
			StepBackwardContent backwardContent = Follower.stack.peek();
			CrudController toController = backwardContent.getToController();
			CrudController fromController = backwardContent.getFromController();
			fromController.backButton.setOnMouseClicked(event -> {
				Follower.stepReduce();
				try {
					if (backwardContent.getCrudType() == CrudTypeEnum.LIST) {
						toController.listPage(backwardContent.getParameter(), null);
					} else if (backwardContent.getCrudType() == CrudTypeEnum.SHOW) {
						toController.showPage(backwardContent.getParameter());
					} else if (backwardContent.getCrudType() == CrudTypeEnum.ADD) {
						toController.addPage(null);
					} else if (backwardContent.getCrudType() == CrudTypeEnum.UPDATE) {
						toController.updatePage(backwardContent.getParameter());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
	}
	
	public static void stepReduce() {
		Follower.stack.pop();
	}

}
