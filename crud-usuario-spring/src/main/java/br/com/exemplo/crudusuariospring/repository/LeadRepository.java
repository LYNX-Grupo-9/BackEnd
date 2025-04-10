package br.com.exemplo.crudusuariospring.repository;

import br.com.exemplo.crudusuariospring.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {
}
