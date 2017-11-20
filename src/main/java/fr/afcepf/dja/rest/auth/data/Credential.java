package fr.afcepf.dja.rest.auth.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class Credential {
	private String username;
	private String password;
	//...
	
}
