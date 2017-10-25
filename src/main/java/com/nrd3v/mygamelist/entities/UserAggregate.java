package com.nrd3v.mygamelist.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_aggregate")
public class UserAggregate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "userAggregate", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;

    public UserAggregate() {
    }

    public UserAggregate(String name) {
        this.name = name;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserAggregate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
