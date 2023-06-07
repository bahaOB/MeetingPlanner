package com.meetingplanner;

import com.meetingplanner.entities.Equipement;
import com.meetingplanner.entities.EquipementSalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootApplication
public class MeetingPlannerApplication implements CommandLineRunner {

	/*@Autowired
	private JdbcTemplate jdbcTemplate;*/

	public static void main(String[] args) {
		SpringApplication.run(MeetingPlannerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	/*	String sql = "INSERT INTO etage (id_etage, num_etage) VALUES "
				+ "(1, 1),"
				+ "(2, 2),"
				+ "(3, 3)";

		String sql1 = "INSERT INTO salle (nom, nombre_places, reserve, id_etage) VALUES "
				+ "('E1001', 21, false, 1),"
				+ "('E1002', 10, false, 1),"
				+ "('E1003', 8, false, 1),"
				+ "('E1004', 4, false, 1),"
				+ "('E2001', 4, false, 2),"
				+ "('E2002', 15, false, 2),"
				+ "('E2003', 7, false, 2),"
				+ "('E2004', 9, false, 2),"
				+ "('E3001', 13, false, 3),"
				+ "('E3002', 8, false, 3),"
				+ "('E3003', 9, false, 3),"
				+ "('E3004', 4, false, 3)";

		String sql2 = "INSERT INTO equipement_salle(id, libelle, nom_salle) VALUES "
				+"(1, '', 'E1001'),"
				+"(2, 'Ecran', 'E1002'),"
				+"(3, 'Pieuvre', 'E1003'),"
				+"(4, 'Tableau', 'E1004'),"
				+"(5, '', 'E2001'),"
				+"(6, 'Ecran', 'E2002'),"
				+"(7, 'Webcam', 'E2002'),"
				+"(8, '', 'E2003'),"
				+"(9, 'Tableau', 'E2004'),"
				+"(10, 'Ecran', 'E3001'),"
				+"(11, 'Webcam', 'E3001'),"
				+"(12, 'Pieuvre', 'E3001'),"
				+"(13, '', 'E3002'),"
				+"(14, 'Ecran', 'E3003'),"
				+"(15, 'Pieuvre', 'E3003'),"
				+"(16, '', 'E3004')";

		String sql3 = "INSERT INTO equipement(id, libelle) VALUES "
				+"(1, 'Ecran'),"
				+"(2, 'Pieuvre'),"
				+"(3, 'Tableau'),"
				+"(4, 'Webcam')";

		String sql4 = "INSERT INTO equipement_amovible(id, libelle, nombre_equipements, disponible) VALUES "
				+"(1, 'Ecran', 4, 4),"
				+"(2, 'Pieuvre', 2, 2),"
				+"(3, 'Tableau', 4, 4),"
				+"(4, 'Webcam', 2, 2)";

		String sql5 = "INSERT INTO type_reunion(id_type_reunion, libelle_type_reunion) VALUES "
				+"(1, 'VC'),"
				+"(2, 'SPEC'),"
				+"(3, 'RS'),"
				+"(4, 'RC')";

		String sql6 = "INSERT INTO equipement_type_reunion(id, libelle, type_reunion) VALUES "
				+"(1, 'Ecran', 1),"
				+"(2, 'Pieuvre', 1),"
				+"(3, 'Webcam', 1),"
				+"(4, 'Tableau', 2),"
				+"(5, '', 3),"
				+"(6, 'Tableau', 4),"
				+"(7, 'Ecran', 4),"
				+"(8, 'Pieuvre', 4)";*/


		/*jdbcTemplate.update(sql);
		jdbcTemplate.update(sql1);
		jdbcTemplate.update(sql2);
		jdbcTemplate.update(sql3);
		jdbcTemplate.update(sql4);
		jdbcTemplate.update(sql5);
		jdbcTemplate.update(sql6);*/
/*
		//1: ouverture unité de travail JPA
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("meeting-planner");
		EntityManager em = emf.createEntityManager();

		//2: ouverture transaction
		EntityTransaction et = em.getTransaction();
		et.begin();

		//3: instanciation
		Equipement equipementSalle = new EquipementSalle("Néant", "");*/
	}
}
