package dev.demo.Services;

import javafx.scene.control.TextField;

public class MTextField extends TextField {
	
	public MTextField(String value) {
		super(value);
		super.setEditable(false);
	}

}
