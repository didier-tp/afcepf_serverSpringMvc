package fr.afcepf.dja.ws;

public interface IServiceDevise {
	
	//convertir(50.0 , "EUR" , "JPY" ); convertir 50 euros en yen japonais
	public double convertir(double montant , 
							String codeMonnaieSource, 
							String codeMonnaieCible);
	//...

}
