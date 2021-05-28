package hu.webuni.logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.logistic.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {

}
