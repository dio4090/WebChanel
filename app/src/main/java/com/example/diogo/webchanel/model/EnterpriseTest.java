package com.example.diogo.webchanel.model;

public class EnterpriseTest {

    private String name;
    private int image; // vai armazenar o identificador do recurso
    private String address;
    public EnterpriseTest(String name, String address, int image) {
        this.name = name;
        this.address = address;
        this.image = image;
    }
// métodos getters e setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
