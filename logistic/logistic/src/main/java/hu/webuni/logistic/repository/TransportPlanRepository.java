package hu.webuni.logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.logistic.model.TransportPlan;

public interface TransportPlanRepository extends JpaRepository<TransportPlan,Long> {

}
