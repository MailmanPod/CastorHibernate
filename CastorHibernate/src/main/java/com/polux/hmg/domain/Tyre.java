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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TYRES")
public class Tyre implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8306958751023054777L;

	@Id
	@Column(name="tyr_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tyre_generator")
	@SequenceGenerator(name="tyre_generator", sequenceName="seq_tyre", allocationSize=1)
	private long id;
	
	@Column(name="tyr_description")
	private String description;
	
	@Column(name="tyr_min_wear")
	private int minWear;
	
	@Column(name="tyr_max_wear")
	private int maxWear;
	
	@Column(name="tyr_start_date")
	private Date startDate;
	
	@Column(name="tyr_end_date")
	private Date endDate;
	
	@OneToMany(mappedBy="tyre")
	private Set<RaceTyre> raceTyre = new HashSet<RaceTyre>();

	public Tyre() {
	}

	public Tyre(String description, int minWear, int maxWear, Date startDate,
			Date endDate) {
		this.description = description;
		this.minWear = minWear;
		this.maxWear = maxWear;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMinWear() {
		return minWear;
	}

	public void setMinWear(int minWear) {
		this.minWear = minWear;
	}

	public int getMaxWear() {
		return maxWear;
	}

	public void setMaxWear(int maxWear) {
		this.maxWear = maxWear;
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
		builder.append("Tyre [description=");
		builder.append(description);
		builder.append(", minWear=");
		builder.append(minWear);
		builder.append(", maxWear=");
		builder.append(maxWear);
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
		if (obj == null || !(obj instanceof Tyre)) {
			return false;
		}

		Tyre tyre = (Tyre) obj;

		if (this.getDescription().compareToIgnoreCase(tyre.getDescription()) == 0) {
			if (this.getStartDate() == tyre.getStartDate()) {
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
