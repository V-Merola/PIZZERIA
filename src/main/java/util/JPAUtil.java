package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static final String PERSISTENCE_UNIT_NAME = "PERS";

	/*
	 * EntityManagerFactory: crea un EntityManager oggetto per convertire i dati da
	 * relazionele a oggetti Java
	 */
	private static EntityManagerFactory factory;

	public static EntityManagerFactory getEntityManagerFactory() {
		if (factory == null) {

			// Passo il nome del <persistence-unit name="PIZZERIA"> del file
			// perstistence.xml

			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return factory;
	}

	public static void shutdown() {
		if (factory != null) {
			factory.close();
		}
	}

}