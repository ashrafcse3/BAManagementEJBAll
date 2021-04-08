package model;

import java.sql.*;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class TransactionDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int Id;
    
	private int transactiontypeid;
	
	private double amount;
	
	private Timestamp transactionDateTime;
	
	private int frombankaccountnumber;
	
	private int tobankaccountnumber;

    public TransactionDetails() {
        
    }
    
    public TransactionDetails(int transactiontypeid, double amount, Timestamp transactionDateTime, int frombankaccountnumber, int tobankaccountnumber) {
        this.transactiontypeid = transactiontypeid;
        this.amount = amount;
        this.transactionDateTime = transactionDateTime;
        this.frombankaccountnumber = frombankaccountnumber;
        this.tobankaccountnumber = tobankaccountnumber;
    }
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int gettransactiontypeid() {
        return transactiontypeid;
    }

    public void settransactiontypeid(int transactiontypeid) {
        this.transactiontypeid = transactiontypeid;
    }
    
    public double getamount() {
        return amount;
    }

    public void setamount(double amount) {
        this.amount = amount;
    }
    
    public Timestamp gettransactionDateTime() {
        return transactionDateTime;
    }

    public void settransactionDateTime(Timestamp transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }
    
    public int getfrombankaccountnumber() {
        return frombankaccountnumber;
    }

    public void setfrombankaccountnumber(int frombankaccountnumber) {
        this.frombankaccountnumber = frombankaccountnumber;
    }
    
    public int gettobankaccountnumber() {
        return tobankaccountnumber;
    }

    public void settobankaccountnumber(int tobankaccountnumber) {
        this.tobankaccountnumber = tobankaccountnumber;
    }
    
}
