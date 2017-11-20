package fr.afcepf.dja.data;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
//+eventuelles annotations JPA (@Entity , ...)
@XmlType(namespace="http://data.dja.afcepf.fr/")
//par convention le namespace vaut "http://" + package_java_a_l-envers + "/"
@XmlRootElement(name="resCalculTva")
public class ResCalculTva {
		private Double ht;
		private Double taux;
		private Double tva;
		private Double ttc;
		
}
