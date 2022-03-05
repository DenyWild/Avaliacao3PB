package com.br.avaliacao.Avaliacao.services;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

	Calendar cal = Calendar.getInstance();
	int currentYear = cal.get(Calendar.YEAR);

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
		return ResponseEntity.notFound().build();

	}

	public List<States> findStatesByRegion(@RequestParam Region region) {
		return statesRepository.findByRegion(region);
	}

	public List<States> findStatesByBiggestPopulation() {
		return statesRepository.findByBiggestPopulation();
	}

	public List<States> findStatesByBiggestArea() {
		return statesRepository.findByBiggestsArea();
	}

	public States validationTimeSinceFundation(States states) {
		int fundationYear = states.getFundationDate().getYear();
		int timeSinceFundation = currentYear - fundationYear;

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
