package fr.afcepf.dja.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.afcepf.dja.dao.IDeviseDao;
import fr.afcepf.dja.data.Devise;
import fr.afcepf.dja.ws.IServiceDevise;



@RestController // composant Spring de type "ControllerRest" (plus précis que @Component)
@RequestMapping(value="rest/devise" , headers="Accept=application/json")
//cette classe est une sorte de "adaptateur REST"
public class RestDeviseService {
	
	@Autowired //injection du "business service"
	private IServiceDevise serviceDevise;
	
	@Autowired //injection du "dao"
	private IDeviseDao deviseDao;
	
    //dans web.xml , le DispatcherServlet de springWebMvc sera associé au url en mvc/* ou ws/*
	//url : http://localhost:8080/serverSpringMvc/ws/rest/devise
	//ou bien http://localhost:8080/serverSpringMvc/ws/rest/devise?tauxChangeMini=1.1
	@RequestMapping(value="",method=RequestMethod.GET)
	public List<Devise> /*allDevise()*/
	       devisesByCriteria(@RequestParam(value="tauxChangeMini",required=false)Double tauxChangeMini){
		    List<Devise> listeDevises = null; 
		     if(tauxChangeMini==null){
		    	 listeDevises = deviseDao.findAllDevise();
		     }
		     else{
		    	  listeDevises = deviseDao.findDeviseWithTauxMini(tauxChangeMini);		    	  
		      }
		      return listeDevises;
	}
	
	//url : http://localhost:8080/serverSpringMvc/ws/rest/devise/EUR
	@RequestMapping(value="/{codeDev}",method=RequestMethod.GET)
	public Devise deviseByCode(@PathVariable("codeDev")String codeDevise){
		return deviseDao.findDeviseByCode(codeDevise);
	}
	

	//url : http://localhost:8080/serverSpringMvc/ws/rest/devise/EUR
	@RequestMapping(value="/{codeDev}",method=RequestMethod.DELETE)
	public ResponseEntity<Devise> deleteDeviseByCode(@PathVariable("codeDev")String codeDevise){
		try {
			deviseDao.deleteDeviseBycode(codeDevise);
			return new ResponseEntity<Devise>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Devise>(HttpStatus.NOT_FOUND);//ou HttpStatus.INTERNAL_SERVER_ERROR
		}
	}
	

	//url : http://localhost:8080/serverSpringMvc/ws/rest/devise
	@RequestMapping(value="",method=RequestMethod.POST)
	public ResponseEntity<Devise> saveOrUpdateDevise(@RequestBody Devise d){
		try {
			Devise dExistante = deviseDao.findDeviseByCode(d.getCodeDevise());
			if(dExistante==null)
			     deviseDao.insertDevise(d);
			else 
				deviseDao.updateDevise(d);
			return new ResponseEntity<Devise>(d,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Devise>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	

}
