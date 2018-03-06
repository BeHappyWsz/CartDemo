package wsz.entity;

import java.io.Serializable;

public class Items implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	
	private String name;
	
	private String city;
	
	private Double price;

	private int number;
	
	private String picture;

	
	public Items() {
		
	}
	
	public Items(int id, String name, String city, Double price, int number, String picture) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.price = price;
		this.number = number;
		this.picture = picture;
	}
	
	@Override
	public int hashCode() {
		return this.getId()+this.getName().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj instanceof Items) {
			Items i= (Items)obj;
			if(this.getId() == i.getId() && this.getName().equals(i.getName())) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	
}
