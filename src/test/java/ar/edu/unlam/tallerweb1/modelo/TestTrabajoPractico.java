package ar.edu.unlam.tallerweb1.modelo;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.unlam.tallerweb1.SpringTest;

public class TestTrabajoPractico extends SpringTest{

	@Test
	@Transactional
	@Rollback(true)
	public void deberiaTraerTodasLasFarmaciasDeTurnoEnLosDiasMartes() {
		
		Session session= getSession();
		
		Farmacia farmacia1 = new Farmacia("Farmacity", "Martes");
		Farmacia farmacia2 = new Farmacia("Farmacol", "Martes");
		Farmacia farmacia3 = new Farmacia("Far-man", "Jueves");
		Farmacia farmacia4 = new Farmacia("El 35", "Jueves");
		Farmacia farmacia5 = new Farmacia("Dr. Juan", "Martes");
		
		session.save(farmacia1);
		session.save(farmacia2);
		session.save(farmacia3);
		session.save(farmacia4);
		session.save(farmacia5);
		
		List<Farmacia> misFarmacias = session.createCriteria(Farmacia.class)
				.add(Restrictions.eq("diaDeTurno", "Martes")).list();
		
		for (Farmacia farmacia : misFarmacias) {
			int i = misFarmacias.size();
			System.out.println(farmacia.getNombre());
			assertThat(misFarmacias.get(i-1).getDiaDeTurno()).isEqualTo("Martes");
			i++;
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void DeberiaTraerTodasLasFarmaciasDeDeterminadaCalle() {

		Session session = getSession();
		
		Direccion direccion1 = new Direccion("Garcia", "123");
		Direccion direccion2 = new Direccion("Garcia", "1233");
		Direccion direccion3 = new Direccion("Machado", "223");
		Direccion direccion4 = new Direccion("Roque", "333");
		Direccion direccion5 = new Direccion("Garcia", "3200");

		session.save(direccion1);
		session.save(direccion2);
		session.save(direccion3);
		session.save(direccion4);
		session.save(direccion5);

		Farmacia farmacia1 = new Farmacia("Farmacity", "Martes");
		Farmacia farmacia2 = new Farmacia("Farmacol", "Martes");
		Farmacia farmacia3 = new Farmacia("Far-man", "Jueves");
		Farmacia farmacia4 = new Farmacia("El 35", "Jueves");
		Farmacia farmacia5 = new Farmacia("Dr. Juan", "Martes");

		farmacia1.setDireccion(direccion1);
		farmacia2.setDireccion(direccion2);
		farmacia3.setDireccion(direccion3);
		farmacia4.setDireccion(direccion4);
		farmacia5.setDireccion(direccion5);
		
		session.save(farmacia1);
		session.save(farmacia2);
		session.save(farmacia3);
		session.save(farmacia4);
		session.save(farmacia5);
		
		List<Farmacia> misFarmacias = session.createCriteria(Farmacia.class)
			.createAlias("direccion", "dir")
			.add(Restrictions.eq("dir.calle", "Garcia")).list();
		
		for (Farmacia farmacia : misFarmacias) {
			int i = misFarmacias.size();
			System.out.println(farmacia.getNombre());
			assertThat(misFarmacias.get(i-1).getDireccion().getCalle()).isEqualTo("Garcia");
			i++;
		}
	}

	@Test
	@Rollback
	@Transactional
	public void deberiaTraerTodasLasFarmaciasDeUnBarrioEnespecifico() {
		Session session = getSession();
		
		Barrio barrio1 = new Barrio("Virrey Del Pino");
		Barrio barrio2 = new Barrio("Gonzalez Catan");
		Barrio barrio3 = new Barrio("Ramos Mejia");
		
		
		session.save(barrio1);
		session.save(barrio2);
		session.save(barrio3);

		Direccion direccion1 = new Direccion("Garcia", "123");
		Direccion direccion2 = new Direccion("Garcia", "1233");
		Direccion direccion3 = new Direccion("Machado", "223");
		Direccion direccion4 = new Direccion("Roque", "333");
		Direccion direccion5 = new Direccion("Garcia", "3200");
		
		direccion1.setBarrio(barrio3);
		direccion2.setBarrio(barrio3);
		direccion3.setBarrio(barrio3);
		direccion4.setBarrio(barrio1);
		direccion5.setBarrio(barrio2);

		session.save(direccion1);
		session.save(direccion2);
		session.save(direccion3);
		session.save(direccion4);
		session.save(direccion5);		

		Farmacia farmacia1 = new Farmacia("Farmacity", "Martes");
		Farmacia farmacia2 = new Farmacia("Farmacol", "Martes");
		Farmacia farmacia3 = new Farmacia("Far-man", "Jueves");
		Farmacia farmacia4 = new Farmacia("El 35", "Jueves");
		Farmacia farmacia5 = new Farmacia("Dr. Juan", "Martes");

		farmacia1.setDireccion(direccion1);
		farmacia2.setDireccion(direccion2);
		farmacia3.setDireccion(direccion3);
		farmacia4.setDireccion(direccion4);
		farmacia5.setDireccion(direccion5);
		
		session.save(farmacia1);
		session.save(farmacia2);
		session.save(farmacia3);
		session.save(farmacia4);
		session.save(farmacia5);
		
		List<Farmacia> misFarmacias = session.createCriteria(Farmacia.class)
				.createAlias("direccion", "dir")
				
				.createAlias("dir.barrio", "barrio")
				.add(Restrictions.eq("barrio.nombre", "Ramos Mejia"))
				.list();
		
		for (Farmacia farmacia : misFarmacias) {
			int i = misFarmacias.size();
			System.out.println(farmacia.getNombre());
			System.out.println(farmacia.getDireccion().getCalle());
			assertThat(misFarmacias.get(i-1).getDireccion().getBarrio().getNombre()).isEqualTo("Ramos Mejia");
			i++;
		}
	}

}
