package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;

public class ProductoTest extends SpringTest{

	@Test
	@Transactional
	@Rollback(true)
	public void queSeLosProductosValganMenosDeMil()
	{
		Session session = (Session) getSession();
		Producto producto1 = new Producto();
		Producto producto2 = new Producto();
		Producto producto3 = new Producto();		
		
		producto1.setDescripcion("Pure Marolio");
		producto1.setPrecio(20.00);
		producto1.setCantidad(20);
		producto1.setTitulo("Pure");
		
		
		producto2.setDescripcion("Arroz Los Hermanos");
		producto2.setPrecio(25.00);
		producto2.setCantidad(20);
		producto2.setTitulo("Arroz");
		
		producto3.setDescripcion("Aceite Cañuelas");
		producto3.setPrecio(45.00);
		producto3.setCantidad(20);
		producto3.setTitulo("Aceite");

		getSession().save(producto1);
		getSession().save(producto2);
		getSession().save(producto3);
		
		List<Producto> productos = session.createCriteria(Producto.class).list();
		
		//List<Producto> menosDeMil = session.createCriteria(Producto.class).add()
		
		
	}
}
