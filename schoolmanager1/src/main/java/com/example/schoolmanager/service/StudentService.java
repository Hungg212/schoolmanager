package com.example.schoolmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.schoolmanager.model.Student;
import com.example.schoolmanager.respository.StudentRepository;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository repository;
    
    public Student addStudent(Student student) {
        if (student != null) {
            return repository.save(student);
        }
        return null;
    }
    
    public Student getStudentById(int id) {
        Optional<Student> student = repository.findById(id);
        return student.orElse(null);
    }
    
    public List<Student> getAll() {
        return repository.findAll();
    }
    
    public Student updateStudent(int id, Student updatedStudent) {
        Optional<Student> studentOpt = repository.findById(id);
        if (studentOpt.isPresent() && updatedStudent != null) {
            Student student = studentOpt.get();
            if (updatedStudent.getName() != null) {
                student.setName(updatedStudent.getName());
            }
            if (updatedStudent.getEmail() != null) {
                student.setEmail(updatedStudent.getEmail());
            }
            return repository.save(student);
        }
        return null;
    }
    
    public void deleteStudent(int id) {
        repository.deleteById(id);
    }
    
    public List<Student> findByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}
