package fr.afcepf.dja.rest.confidential;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class News {
	private String titre;
	private String texte;
	//...
}
