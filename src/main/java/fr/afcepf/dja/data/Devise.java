package fr.afcepf.dja.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlType(namespace="http://data.dja.afcepf.fr/")
@XmlRootElement(name="devise")
@NamedQuery(name="Devise.findByTauxMini",
            query="SELECT d FROM Devise d WHERE d.tauxChange >= :tauxMin")
public class Devise {
	
	@Id
	@Column(length=32)
	private String codeDevise; //"EUR" , "USD"
	
	private Double tauxChange;
}
