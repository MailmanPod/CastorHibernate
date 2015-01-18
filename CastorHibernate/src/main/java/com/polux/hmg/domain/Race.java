package com.polux.hmg.domain;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "RACES")
public class Race implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4572966013644939612L;

	@Id
	@Column(name = "rac_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "race_generator")
	@SequenceGenerator(name = "race_generator", sequenceName = "seq_race", allocationSize = 1)
	private long id;

	@Column(name = "rac_full_name")
	private String fullName;

	@Column(name = "rac_laps")
	private int laps;

	/*
	 * @Column(name="rac_country") private int country;
	 */

	@Column(name = "rac_start_date")
	private Date startDate;

	@Column(name = "rac_end_date")
	private Date endDate;

	@OneToMany(mappedBy = "race")
	private Set<RaceTyre> raceTyre = new HashSet<RaceTyre>();
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name="rac_country")
	private Country country;
	
	public Race() {
	}

	public Race(String fullName, int laps, Date startDate, Date endDate, Country country) {
		this.fullName = fullName;
		this.laps = laps;
		this.startDate = startDate;
		this.endDate = endDate;
		
		this.country=country;
		country.getRace().add(this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Race [fullName=");
		builder.append(fullName);
		builder.append(", laps=");
		builder.append(laps);
		builder.append(", country=");
		builder.append(country);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		try {
			final SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			int value = random.nextInt();
			int unValue = Math.abs(value);

			return unValue;

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Race)) {
			return false;
		}

		Race race = (Race) obj;

		if (this.getFullName().compareToIgnoreCase(race.getFullName()) == 0) {
			if (this.getStartDate() == race.getStartDate()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public Set<RaceTyre> getRaceTyre() {
		return raceTyre;
	}

	public void setRaceTyre(Set<RaceTyre> raceTyre) {
		this.raceTyre = raceTyre;
	}
}
