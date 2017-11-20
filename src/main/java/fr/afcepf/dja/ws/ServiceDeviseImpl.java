package fr.afcepf.dja.ws;



import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.afcepf.dja.dao.IDeviseDao;
import fr.afcepf.dja.data.Devise;


@Service
@Transactional
public class ServiceDeviseImpl implements IServiceDevise {
	
	@Autowired
	private IDeviseDao deviseDao;
	
	@PostConstruct
	public void initJeuxDeDonnees(){
		if(deviseDao.findAllDevise().isEmpty()){
			deviseDao.insertDevise(new Devise("EUR",0.84));
			deviseDao.insertDevise(new Devise("USD",1.0));
			deviseDao.insertDevise(new Devise("JPY",112.0));
			deviseDao.insertDevise(new Devise("GBP",0.758));
		}
	}

	@Override
	public double convertir(double montant, String codeMonnaieSource, String codeMonnaieCible) {
		Devise deviseSource = deviseDao.findDeviseByCode(codeMonnaieSource);
		Devise deviseCible = deviseDao.findDeviseByCode(codeMonnaieCible);
		return montant * deviseCible.getTauxChange() / deviseSource.getTauxChange();
		//ou return montant *  deviseSource.getTauxChange() / deviseCible.getTauxChange() ;
	}

	

}
