package com.wuqi.dev.loader;

import com.wuqi.dev.entity.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtFileLoader implements StudentLoader {

    private String filePath;

    public TxtFileLoader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Student> load() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                students.add(new Student(line));
            }
        } catch (IOException e) {
            students.clear();
            e.printStackTrace();
        }
        return students;
    }
}
