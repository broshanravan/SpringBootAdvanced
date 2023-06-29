package com.mehr.SpringBootAdvanced.beans;


public class GreetingBean {
    private String name;
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GreetingBean(String name, String message) {
        this.name = name;
        this.message = message;
    }

    @Override
    public String toString() {
        return "GreetingBean{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
