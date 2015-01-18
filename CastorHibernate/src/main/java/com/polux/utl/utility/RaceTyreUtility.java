package com.polux.utl.utility;

import java.sql.Date;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.polux.hmg.domain.Country;
import com.polux.hmg.domain.Race;
import com.polux.hmg.domain.RaceTyre;
import com.polux.hmg.domain.Tyre;

public class RaceTyreUtility {

	private Logger logger;
	private SessionFactory factory;

	public RaceTyreUtility() {
		this.logger = Logger.getLogger(RaceTyreUtility.class);

		logger.info("Building SessionFactory");
		Configuration config = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties());
		this.factory = config.buildSessionFactory(builder.build());
	}

	private RaceTyre createRaceTyre(Race race, Tyre tyre) {
		logger.warn("Creating new asociation - Race/Tyre");
		Date addDate = new Date(System.currentTimeMillis());
		String usrLoad = "Razor1911";
		String usrDown = "";
		Date startDate = new Date(System.currentTimeMillis());
		Date endDate = new Date(64060513200000L);

		return new RaceTyre(race, tyre, addDate, usrLoad, usrDown, startDate,
				endDate);
	}

	public void addItems(Race race, Tyre tyre) {
		logger.info("Inserting Items in Database - Race/Tyre");
		Session session = this.factory.openSession();
		Transaction tx = null;

		try {
			logger.info("Begin Transaction");
			tx = session.beginTransaction();

			logger.warn("Saving new Race...");
			session.save(race);

			logger.warn("Saving new Tyre...");
			session.save(tyre);

			RaceTyre rt = createRaceTyre(race, tyre);
			session.save(rt);
			tx.commit();

		} catch (HibernateException hex) {
			if (tx != null) {
				logger.info("Rollingback transactions...");
				tx.rollback();
			}
			logger.error("Error saving changes...");
			logger.error(hex.getMessage());
		} finally {
			logger.warn("Closing channels...");
			session.close();
			factory.close();
		}
	}

	public void addItemRace(Race race, Country country) {
		logger.info("Inserting new item Race into database");
		Session session = this.factory.openSession();
		Transaction tx = null;

		try {
			logger.info("Saving new race...");
			tx = session.beginTransaction();
			
			session.save(country);
			session.save(race);
			
			tx.commit();
		} catch (HibernateException hex) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Rollingback changes...");
			logger.error(hex.getMessage());
		} finally {
			logger.warn("Closing channels...");
			session.close();
			this.factory.close();
		}
	}

	public void addItemTyre(Tyre tyre) {
		logger.info("Inserting new item Tyre into database");
		Session session = this.factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(tyre);
			tx.commit();
		} catch (HibernateException hex) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Rollingback changes...");
			logger.error(hex.getMessage());
		} finally {
			logger.warn("Closing channels...");
			session.close();
			this.factory.close();
		}
	}
}
