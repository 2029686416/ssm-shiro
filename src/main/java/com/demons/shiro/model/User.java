package com.demons.shiro.model;

public class User {
	private String id;

    private String name;

    private Integer age;

    private String salt;
    
    private String na;
    
	public String getNa() {
		return na;
	}

	public void setNa(String na) {
		this.na = na;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}