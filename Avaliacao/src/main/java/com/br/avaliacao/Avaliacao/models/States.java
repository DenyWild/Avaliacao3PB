package com.br.avaliacao.Avaliacao.models;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.br.avaliacao.Avaliacao.models.enums.Region;

@Entity
public class States {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String name;
	@Enumerated(EnumType.STRING)
	@NotNull
	private Region region;
	@NotNull
	private double population;
	@NotBlank
	private String capital;
	@NotNull
	private double area;
	@NotNull
	private LocalDate fundationDate;
	@NotNull
	private int timeSinceFundation;

	public States() {

	}

	public States(@NotBlank String name, @NotNull Region region, @NotNull double population, @NotBlank String capital,
			@NotNull double area, @NotNull LocalDate fundationDate, @NotNull int timeSinceFundation) {
		super();
		this.name = name;
		this.region = region;
		this.population = population;
		this.capital = capital;
		this.area = area;
		this.fundationDate = fundationDate;
		this.timeSinceFundation = timeSinceFundation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public double getPopulation() {
		return population;
	}

	public void setPopulation(double population) {
		this.population = population;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public double getArea() {

		return area;
	}

	public void setArea(double area) {

		this.area = area;
	}

	public LocalDate getFundationDate() {
		return fundationDate;
	}

	public void setFundationDate(LocalDate fundationDate) {
		this.fundationDate = fundationDate;
	}

	public int getTimeSinceFundation() {
		return timeSinceFundation;
	}

	public void setTimeSinceFundation(int timeSinceFundation) {
		this.timeSinceFundation = timeSinceFundation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(area, capital, fundationDate, id, name, population, region, timeSinceFundation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		States other = (States) obj;
		return Double.doubleToLongBits(area) == Double.doubleToLongBits(other.area)
				&& Objects.equals(capital, other.capital) && Objects.equals(fundationDate, other.fundationDate)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(population) == Double.doubleToLongBits(other.population)
				&& region == other.region && timeSinceFundation == other.timeSinceFundation;
	}

	@Override
	public String toString() {
		return "States [id= " + id + ", name= " + name + ", region= " + region + ", population= " + population
				+ ", capital= " + capital + ", area= " + area + ", fundationDate= " + fundationDate
				+ ", timeSinceFundation= " + timeSinceFundation + "]";
	}

}
