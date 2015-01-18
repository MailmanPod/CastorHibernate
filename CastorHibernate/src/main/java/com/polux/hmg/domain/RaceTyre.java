package com.polux.hmg.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="RACE_TYRE")
public class RaceTyre implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6454827337714702858L;

	@Embeddable
	public static class CompositeId implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 7161824030404513953L;

		@Column(name="rty_rac_id")
		private long raceId;
		
		@Column(name="rty_tyr_id")
		private long tyreId;
		
		@Column(name="rty_start_date")
		private Date startDate;
		
		public CompositeId() {
		}

		public CompositeId(long rac_id, long tyr_id, Date startDate) {
			this.raceId = rac_id;
			this.tyreId = tyr_id;
			this.startDate = startDate;
		}

		public long getRac_id() {
			return raceId;
		}

		public void setRac_id(long rac_id) {
			this.raceId = rac_id;
		}

		public long getTyr_id() {
			return tyreId;
		}

		public void setTyr_id(long tyr_id) {
			this.tyreId = tyr_id;
		}
		
		public Date getStartDate() {
			return startDate;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (raceId ^ (raceId >>> 32));
			result = prime * result + (int) (tyreId ^ (tyreId >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if(obj == null || !(obj instanceof RaceTyre)) {
				return false;
			}
			
			CompositeId rct = (CompositeId) obj;
			
			if(this.getRac_id() == rct.getRac_id() && this.getTyr_id() == rct.getTyr_id()) {
				return true;
			}else {
				return false;
			}
		}
	}

	@EmbeddedId
	private CompositeId id = new CompositeId();
	
	@Column(name="rty_add_date")
	private Date addDate;
	
	@Column(name="rty_usr_load")
	private String usrLoad;
	
	@Column(name="rty_usr_down")
	private String usrDown;
	
	@Column(name="rty_end_date")
	private Date endDate;
	
	@ManyToOne
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name="rty_rac_id", insertable=false, updatable=false) 
	private Race race;
	
	@ManyToOne
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name="rty_tyr_id", insertable=false, updatable=false) 
	private Tyre tyre;
	
	public RaceTyre() {
	}

	public RaceTyre(Race race, Tyre tyre, Date addDate, String usrLoad,
			String usrDown, Date startDate, Date endDate) {
		
		this.race = race;
		this.tyre = tyre;
		
		
		this.id.raceId = race.getId();
		this.id.tyreId = tyre.getId();
		this.id.startDate = startDate;
		
		race.getRaceTyre().add(this);
		tyre.getRaceTyre().add(this);
		
		this.addDate = addDate;
		this.usrLoad = usrLoad;
		this.usrDown = usrDown;
		this.endDate = endDate;
	}

	public CompositeId getId() {
		return id;
	}

	public void setId(CompositeId id) {
		this.id = id;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public Tyre getTyre() {
		return tyre;
	}

	public void setTyre(Tyre tyre) {
		this.tyre = tyre;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getUsrLoad() {
		return usrLoad;
	}

	public void setUsrLoad(String usrLoad) {
		this.usrLoad = usrLoad;
	}

	public String getUsrDown() {
		return usrDown;
	}

	public void setUsrDown(String usrDown) {
		this.usrDown = usrDown;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
