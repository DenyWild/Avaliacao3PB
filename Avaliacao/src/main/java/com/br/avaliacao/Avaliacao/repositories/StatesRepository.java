package com.br.avaliacao.Avaliacao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.avaliacao.Avaliacao.models.States;
import com.br.avaliacao.Avaliacao.models.enums.Region;

public interface StatesRepository extends JpaRepository<States, Long> {

	List<States> findByRegion(Region region);

	@Query(value = "SELECT * FROM States ORDER BY population DESC ", nativeQuery = true)
	List<States> findByBiggestPopulation();

	@Query(value = "SELECT * FROM States ORDER BY area DESC ", nativeQuery = true)
	List<States> findByBiggestsArea();

}
