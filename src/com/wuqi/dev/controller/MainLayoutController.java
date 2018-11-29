package com.wuqi.dev.controller;

import com.wuqi.dev.StudentSelector;
import com.wuqi.dev.StudentSelectorImpl;
import com.wuqi.dev.entity.Student;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainLayoutController {

    public Label textDisplay;
    public Button btnControl;
    public Button btnReset;

    private boolean flag = false;

    private StudentSelector studentSelector = new StudentSelectorImpl(new StudentSelector.OnStudentCheckedHandler() {
        @Override
        public void onLoading(Student stu) {
            Platform.runLater(() -> textDisplay.setText(stu.getName()));
        }

        @Override
        public void onPause(Student stu) {
            Platform.runLater(() -> textDisplay.setText(stu.getName()));
        }
    });

    public void onControlClick(ActionEvent actionEvent) {
        String text = flag ? "开始" : "暂停";
        flag = !flag;
        btnControl.setText(text);
        btnReset.setDisable(flag);
        if (flag) {
            studentSelector.start();
        } else {
            studentSelector.pause();
        }
    }

    public void onResetClick(ActionEvent actionEvent) {
        btnControl.setText("暂停");
        btnReset.setDisable(true);
        flag = true;
        studentSelector.reset();
    }
}
