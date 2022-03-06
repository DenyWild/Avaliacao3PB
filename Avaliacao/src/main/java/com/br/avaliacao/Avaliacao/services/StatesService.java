package com.br.avaliacao.Avaliacao.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.avaliacao.Avaliacao.exceptions.EntityNotFound;
import com.br.avaliacao.Avaliacao.exceptions.ValidationOfTimeSinceFundation;
import com.br.avaliacao.Avaliacao.models.States;
import com.br.avaliacao.Avaliacao.models.enums.Region;
import com.br.avaliacao.Avaliacao.repositories.StatesRepository;

@Service
public class StatesService {

	@Autowired
	private StatesRepository statesRepository;

	public States findById(Long id) {
		return statesRepository.findById(id).orElseThrow(() -> new EntityNotFound("id not found " + id));
	}

	public List<States> findAllStates() {
		return statesRepository.findAll();
	}

	public States saveState(@Valid States states) {
		return validationTimeSinceFundation(states);
	}

	public States UpdateStateById(@PathVariable Long id, @Valid States states) {
		Optional<States> sta = statesRepository.findById(id);
		if (sta.isPresent()) {
			validationTimeSinceFundation(states);
		}
		return sta.orElseThrow(() -> new EntityNotFound("id not found " + id));

	}

	public ResponseEntity<?> deleteStateById(@PathVariable Long id) {
		Optional<States> st = statesRepository.findById(id);
		if (st.isPresent()) {
			statesRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	public List<States> findStatesByRegion(@RequestParam Region region) {
		return statesRepository.findByRegion(region);
	}

	public List<States> orderStatesByBiggestPopulation() {
		return statesRepository.orderByBiggestPopulation();
	}

	public List<States> orderStatesByBiggestArea() {
		return statesRepository.orderByBiggestsArea();
	}

	public List<States> listStatesByPopulationBiggerThenAverage() {

		return statesRepository.listByPopulationBiggerThenAverage(average(1));

	}

	public List<States> listStatesByAreaBiggerThenAverage() {

		return statesRepository.listByAreaBiggerThenAverage(average(2));

	}
	

	public List<States> listStatesByPopulationBiggerThenValue(double value) {

		return statesRepository.listByPopulationBiggerThenValue(value);

	}
	

	public List<States> listStatesByAreaBiggerThenValue(double value) {

		return statesRepository.listByAreaBiggerThenValue(value);

	}
	
	public double average(int info) {

		List<States> sta = statesRepository.findAll();
		double aux = 0;
		for (int i = 0; i < sta.size(); i++) {
			if(info == 1) {
			aux = aux + sta.get(i).getPopulation();
			}else if(info == 2) {
				aux = aux + sta.get(i).getArea();
			}
		}
		double average = (aux / sta.size());
		return average;
	}

	public States validationTimeSinceFundation(States states) {

		LocalDate currentDate = LocalDate.now();
		Period period = Period.between(currentDate, states.getFundationDate());

		int timeSinceFundation = Math.abs(period.getYears());

		if (states.getTimeSinceFundation() == timeSinceFundation) {
			States st = statesRepository.save(states);
			return st;
		}
		if (states.getTimeSinceFundation() != timeSinceFundation) {

			throw new ValidationOfTimeSinceFundation("The value of Time Since Fundation isn't right");
		}
		return states;
	}

}
