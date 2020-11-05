package com.lambdaschool.javazoos.models;

import javax.persistence.*;

@Entity
@Table(name = "telephones")
public class Telephone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long phoneid;

    private String phonetype;

    private String phonenumber;

    @ManyToOne
    @JoinColumn(name = "zooid",
                nullable = false)
    private Zoo zoo;


}
