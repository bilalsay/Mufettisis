package dev.demo.Services;

import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.TextInputControl;

/**
 * Created by bilalsay on 06/10/2017.
 */
public class PostingChecker {

    private boolean addingFlag = true;
    private boolean specialConditionFlag = false;

    public boolean emptyCheck(TextInputControl ... textInputs) {
        for (TextInputControl ti: textInputs) {
            if (ti.getText().trim().isEmpty()) {
                ti.setStyle("-fx-border-color: red;");
                if (isAddingFlag()) {
                    setAddingFlag(false);
                }
            } else {
                ti.setStyle("-fx-border-width: 0 0 0 0;");
            }
        }
        return isAddingFlag();
    }

    public boolean emptyCheck(ComboBoxBase<?> ... comboBoxes) {
        for (ComboBoxBase<?> cb: comboBoxes) {
            if (cb.getValue() == null) {
                cb.setStyle("-fx-border-color: red;");
                    setAddingFlag(false);
            } else {
                cb.setStyle("-fx-border-width: 0 0 0 0;");
            }
        }
        return isAddingFlag();
    }

    public boolean isAddingFlag() {
        return addingFlag;
    }

    public void setAddingFlag(boolean addingFlag) {
        this.addingFlag = addingFlag;
    }

    public boolean isSpecialConditionFlag() {
        return specialConditionFlag;
    }

    public void setSpecialConditionFlag(boolean specialConditionFlag) {
        this.specialConditionFlag = specialConditionFlag;
    }
}
