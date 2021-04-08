package model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
public class AccountTypes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;

	public AccountTypes() {
	}

	public AccountTypes(String name) {
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}