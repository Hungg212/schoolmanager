package com.example.schoolmanager.controller;

import com.example.schoolmanager.model.GradeComponent;
import com.example.schoolmanager.service.GradeComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/grade-components")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GradeComponentController {

    @Autowired
    private GradeComponentService service;

    @PostMapping
    public ResponseEntity<?> addGradeComponent(@RequestBody GradeComponent component) {
        try {
            if (component.getComponentCode() == null || component.getComponentCode().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Mã thành phần không được để trống");
            }
            if (component.getComponentName() == null || component.getComponentName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Tên thành phần không được để trống");
            }
            if (component.getWeightPercentage() == null || 
                component.getWeightPercentage().doubleValue() < 0 || 
                component.getWeightPercentage().doubleValue() > 100) {
                return ResponseEntity.badRequest().body("Trọng số phải từ 0 đến 100");
            }
            if (component.getCourseClass() == null || component.getCourseClass().getId() == null) {
                return ResponseEntity.badRequest().body("course_class_id không được để trống");
            }

            GradeComponent saved = service.addGradeComponent(component);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi khi thêm thành phần điểm: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGradeComponent(
            @PathVariable UUID id,
            @RequestBody GradeComponent updated) {
        try {
            if (updated.getWeightPercentage() != null && 
                (updated.getWeightPercentage().doubleValue() < 0 || 
                 updated.getWeightPercentage().doubleValue() > 100)) {
                return ResponseEntity.badRequest().body("Trọng số phải từ 0 đến 100");
            }

            GradeComponent result = service.updateGradeComponent(id, updated);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi khi cập nhật: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGradeComponent(@PathVariable UUID id) {
        service.deleteGradeComponent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/course-class/{courseClassId}")
    public List<GradeComponent> getByCourseClassId(@PathVariable UUID courseClassId) {
        return service.getByCourseClassId(courseClassId);
    }

    @GetMapping
    public List<GradeComponent> getAllActive() {
        return service.getAllActive();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        GradeComponent component = service.getById(id);
        if (component == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(component);
    }
}
