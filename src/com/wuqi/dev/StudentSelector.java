package com.wuqi.dev;

import com.wuqi.dev.entity.Student;

public interface StudentSelector {

    void start();

    void pause();

    void reset();

    interface OnStudentCheckedHandler {
        void onLoading(Student stu);
        void onPause(Student stu);
    }

}
