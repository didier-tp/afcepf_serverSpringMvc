package fr.afcepf.dja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.dja.data.Devise;

//@Component
@Repository //de Spring
@Transactional //de Spring
public class DeviseDaoJpa implements IDeviseDao {
	
	@PersistenceContext() //pour initialiser entityManager avec configSpring + META-INF/persistence.xml
	private EntityManager entityManager;

	@Override
	public Devise findDeviseByCode(String codeDevise) {
		return entityManager.find(Devise.class, codeDevise);
	}

	@Override
	public List<Devise> findAllDevise() {
		return entityManager.createQuery("SELECT d FROM Devise d",Devise.class)
				            .getResultList();
	}

	@Override
	public void updateDevise(Devise d) {
		entityManager.merge(d);
	}

	@Override
	public void insertDevise(Devise d) {
		entityManager.persist(d);
	}

	@Override
	public List<Devise> findDeviseWithTauxMini(double tauxChangeMini) {
		/*return entityManager.createQuery(
				"SELECT d FROM Devise d WHERE d.tauxChange >= :tauxMin",
				Devise.class)
			   .setParameter("tauxMin", tauxChangeMini)
	           .getResultList();*/
		return entityManager.createNamedQuery("Devise.findByTauxMini",
				Devise.class)
			   .setParameter("tauxMin", tauxChangeMini)
	           .getResultList();
	}

	@Override
	public void deleteDeviseBycode(String codeDevise) {
		entityManager.remove( entityManager.find(Devise.class, codeDevise) );
	}
}
