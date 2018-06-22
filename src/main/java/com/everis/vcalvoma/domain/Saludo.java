package com.everis.vcalvoma.domain;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("saludo")
public class Saludo {
	
	  protected String content;
	    
	    /**
		 * Default constructor 
		 */
		protected Saludo() {
			this.content = "Hola!";
		}

	    public Saludo(String content) {
	        this.content = content;
	    }

	    public String getContent() {
	        return content;
	    }
}
