package com.example.schoolmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.schoolmanager.model.Student;
import com.example.schoolmanager.respository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    // ➕ THÊM SINH VIÊN
    public Student addStudent(Student student) {
        student.setId(null); // chỉ dùng cho ADD
        return repository.save(student);
    }

    // ✏️ CẬP NHẬT SINH VIÊN
    public Student updateStudent(int id, Student newData) {
        Student existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existing.setName(newData.getName());
        existing.setEmail(newData.getEmail());

        return repository.save(existing); // ❌ KHÔNG setId(null)
    }

    public void deleteStudent(int id) {
        repository.deleteById(id);
    }

    public List<Student> findByName(String name){
        return repository.findByNameContainingIgnoreCase(name);
    }

    public List<Student> getAll(){
        return repository.findAll();
    }

    public Student getStudentById(int id){
        return repository.findById(id).orElse(null);
    }
}
