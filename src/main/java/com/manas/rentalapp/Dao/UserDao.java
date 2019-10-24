package com.manas.rentalapp.Dao;

public class UserDao {
	
	public UserDao(){
		
	}
	public UserDao(String email){
		this.email = email;
	}
	UserDao(long id){
		this.id = id;
	}
	UserDao(long id,String email){
		this.email = email;
		this.id = id;
	}

	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private long id;
	
}
