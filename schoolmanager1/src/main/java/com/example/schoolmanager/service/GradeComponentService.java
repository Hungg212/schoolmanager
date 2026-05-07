package com.example.schoolmanager.service;

import com.example.schoolmanager.model.CourseClass;
import com.example.schoolmanager.model.GradeComponent;
import com.example.schoolmanager.respository.GradeComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GradeComponentService {

    @Autowired
    private GradeComponentRepository repository;

    public GradeComponent addGradeComponent(GradeComponent component) {
        component.setId(null);
        return repository.save(component);
    }

    public GradeComponent updateGradeComponent(UUID id, GradeComponent newData) {
        GradeComponent existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("GradeComponent not found with id: " + id));

        existing.setComponentCode(newData.getComponentCode());
        existing.setComponentName(newData.getComponentName());
        existing.setWeightPercentage(newData.getWeightPercentage());
        existing.setMinScore(newData.getMinScore());
        existing.setMaxScore(newData.getMaxScore());
        existing.setIsRequired(newData.getIsRequired());
        existing.setInputOrder(newData.getInputOrder());
        existing.setIsActive(newData.getIsActive());
        existing.setUpdatedBy(newData.getUpdatedBy());

        return repository.save(existing);
    }

    public void deleteGradeComponent(UUID id) {
        repository.deleteById(id);
    }

    public void softDelete(UUID id, UUID deletedBy) {
        GradeComponent existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("GradeComponent not found with id: " + id));
        existing.setDeletedAt(java.time.LocalDateTime.now());
        existing.setDeletedBy(deletedBy);
        repository.save(existing);
    }

    public List<GradeComponent> getByCourseClassId(UUID courseClassId) {
        return repository.findByCourseClass_IdAndDeletedAtIsNullOrderByInputOrderAsc(courseClassId);
    }

    public List<GradeComponent> getAllActive() {
        return repository.findByDeletedAtIsNull();
    }

    public GradeComponent getById(UUID id) {
        return repository.findById(id).orElse(null);
    }
}
