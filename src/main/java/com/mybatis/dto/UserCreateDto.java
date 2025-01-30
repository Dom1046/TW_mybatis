package com.mybatis.dto;

import com.mybatis.domain.Pet;

public class UserCreateDto {
    private int user_id;
    private String name;
    private int age;
    private String job;
    private String sex;
    private Pet pet;

    public UserCreateDto() {
    }

    public UserCreateDto(String name, int age, String job, String sex, Pet pet) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.sex = sex;
        this.pet = pet;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getJob() {
        return job;
    }

    public String getSex() {
        return sex;
    }

    public Pet getPet() {
        return pet;
    }
}
