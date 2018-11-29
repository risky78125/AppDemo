package com.wuqi.dev;

import com.wuqi.dev.entity.Student;
import com.wuqi.dev.loader.StudentLoader;
import com.wuqi.dev.loader.TxtFileLoader;
import com.wuqi.dev.utils.SleepUtils;

import java.util.List;
import java.util.Random;

public class StudentSelectorImpl implements StudentSelector, Runnable {

    private StudentLoader studentLoader;
    private List<Student> students;
    private boolean flag = true;

    private Random r = new Random();

    private OnStudentCheckedHandler onStudentCheckedHandler;

    public StudentSelectorImpl(OnStudentCheckedHandler onStudentCheckedHandler) {
        this.onStudentCheckedHandler = onStudentCheckedHandler;
        studentLoader = new TxtFileLoader("name-list.txt");
        students = studentLoader.load();
    }

    @Override
    public void start() {
        flag = true;
        new Thread(this).start();
    }

    @Override
    public void pause() {
        flag = false;
    }

    @Override
    public void reset() {
        students = studentLoader.load();
        start();
    }

    @Override
    public void run() {
        int i = 0;
        int size = students.size();
        if (size > 0) {
            while (flag) {
                int index = r.nextInt(size);
                Student stu = students.get(index);
                this.onStudentCheckedHandler.onLoading(stu);
                SleepUtils.sleep();
                i++;
            }
            int index = --i % size;
            Student stu = students.remove(index);
            this.onStudentCheckedHandler.onPause(stu);
        } else {
            reset();
        }
    }
}
