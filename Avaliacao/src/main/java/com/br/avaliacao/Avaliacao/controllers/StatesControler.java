package com.br.avaliacao.Avaliacao.controllers;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.avaliacao.Avaliacao.models.States;
import com.br.avaliacao.Avaliacao.models.enums.Region;
import com.br.avaliacao.Avaliacao.services.StatesService;

@RestController
@RequestMapping("/api/states")
public class StatesControler {

	@Autowired
	private StatesService statesService;

	@PostMapping
	@Transactional
	public ResponseEntity<States> saveState(@RequestBody @Valid States states) {
		States st = statesService.saveState(states);
		return ResponseEntity.ok().body(st);
	}

	@GetMapping
	public List<States> listStates() {
		return statesService.findAllStates();
	}

	@GetMapping(path = "/{id}")
	public States findStateById(@PathVariable Long id) {
		return statesService.findById(id);

	}

	@GetMapping(path = "/region")
	public List<States> findByRegion(@RequestParam Region region) {
		return statesService.findStatesByRegion(region);

	}

	@GetMapping("/biggestpopulation")
	public List<States> findByBiggestPopulation() {
		return statesService.findStatesByBiggestPopulation();

	}

	@GetMapping("/biggestarea")
	public List<States> findByBiggestArea() {
		return statesService.findStatesByBiggestArea();

	}

	@PutMapping(path = "/{id}")
	@Transactional
	public States updateById(@PathVariable Long id, @RequestBody States states) {
		return statesService.UpdateStateById(id, states);

	}

	@DeleteMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<?> deleteStateById(@PathVariable Long id) {
		return statesService.deleteStateById(id);
	}

}
