package com.polux.hmg.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRIES")
public class Country implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9107982194248149039L;

	@Id
	@Column(name = "cou_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_generator")
	@SequenceGenerator(name = "country_generator", sequenceName = "seq_country", allocationSize = 1)
	private long id;

	@Column(name = "cou_place")
	private String place;

	@Column(name = "cou_country")
	private String country;

	@OneToMany(mappedBy="country")
	private Set<Race> race = new HashSet<Race>();
	
	public Country() {
	}

	public Country(String place, String country) {
		this.place = place;
		this.country = country;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getplace() {
		return place;
	}

	public void setplace(String place) {
		this.place = place;
	}

	public Set<Race> getRace() {
		return race;
	}

	public void setRace(Set<Race> race) {
		this.race = race;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Country)) {
			return false;
		}

		Country country = (Country) obj;

		if (this.getplace().compareToIgnoreCase(country.getplace()) == 0) {
			return true;
		} else {
			return false;
		}
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
