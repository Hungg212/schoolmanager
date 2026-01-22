package com.example.schoolmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.schoolmanager.service.StudentService;
import com.example.schoolmanager.model.Student;

@RestController
@RequestMapping("/api/students")
@CrossOrigin // cho phép frontend gọi
public class StudentController {

    @Autowired
    private StudentService service;

    //1. API thêm sinh viên
    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        try {
            // Validate dữ liệu đầu vào
            if (student.getName() == null || student.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Tên sinh viên không được để trống");
            }
            if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Email không được để trống");
            }
            
            Student savedStudent = service.addStudent(student);
            return ResponseEntity.ok(savedStudent);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi khi thêm sinh viên: " + e.getMessage());
        }
    }

    //2. API xóa sinh viên cũ (nếu có nơi khác đang dùng)
    @PostMapping("/delete/{id}")
    public String deleteStudentOld(@PathVariable int id) {
        service.deleteStudent(id);
        return "Student with ID " + id + " has been deleted.";
    }

    //2b. API xóa sinh viên chuẩn REST cho frontend (DELETE /api/students/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    //3. Tim kiếm sinh viên theo tên
    @GetMapping("/search")
    public List<Student> searchByName(@RequestParam String name) {
        return service.findByName(name);
    }

    //4. API lấy sinh viên theo ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return service.getStudentById(id);
    }

    //5. API lấy danh sách sinh viên
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAll();
    }
    
    //6. API cập nhật sinh viên
    //   Sử dụng JSON trong body để cập nhật cho thống nhất với API thêm mới
    @PutMapping("/update/{id}")
public ResponseEntity<Student> updateStudent(
        @PathVariable int id,
        @RequestBody Student updatedStudent) {

    Student result = service.updateStudent(id, updatedStudent);
    return ResponseEntity.ok(result);
    }
}

