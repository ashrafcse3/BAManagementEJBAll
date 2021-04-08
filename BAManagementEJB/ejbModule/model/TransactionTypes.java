package model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
public class TransactionTypes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

//	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
	
	private String name;

	public TransactionTypes() {
	}

	public TransactionTypes(String name) {
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