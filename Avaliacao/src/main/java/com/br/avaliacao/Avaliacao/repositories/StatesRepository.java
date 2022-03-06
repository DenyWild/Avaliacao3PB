package com.br.avaliacao.Avaliacao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.avaliacao.Avaliacao.models.States;
import com.br.avaliacao.Avaliacao.models.enums.Region;

public interface StatesRepository extends JpaRepository<States, Long> {

	List<States> findByRegion(Region region);

	@Query(value = "SELECT * FROM States ORDER BY population DESC ", nativeQuery = true)
	List<States> orderByBiggestPopulation();

	@Query(value = "SELECT * FROM States ORDER BY area DESC ", nativeQuery = true)
	List<States> orderByBiggestsArea();

	@Query(value = "SELECT * FROM States WHERE population > :average ORDER BY population DESC", nativeQuery = true)
	List<States> listByPopulationBiggerThenAverage(double average);

	@Query(value = "SELECT * FROM States WHERE area > :average ORDER BY area DESC", nativeQuery = true)
	List<States> listByAreaBiggerThenAverage(double average);

	@Query(value = "SELECT * FROM States WHERE population > :value ORDER BY population DESC", nativeQuery = true)
	List<States> listByPopulationBiggerThenValue(double value);
	
	@Query(value = "SELECT * FROM States WHERE area > :value ORDER BY area DESC", nativeQuery = true)
	List<States> listByAreaBiggerThenValue(double value);
	
}
