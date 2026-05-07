package com.example.schoolmanager.respository;

import com.example.schoolmanager.model.GradeComponent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GradeComponentRepository extends JpaRepository<GradeComponent, UUID> {

    List<GradeComponent> findByCourseClass_IdOrderByInputOrderAsc(UUID courseClassId);

    List<GradeComponent> findByCourseClass_IdAndDeletedAtIsNullOrderByInputOrderAsc(UUID courseClassId);

    List<GradeComponent> findByDeletedAtIsNull();
}
