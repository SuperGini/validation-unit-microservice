package com.gini.validationunit.domaine.postgress;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wish_generator")
    @SequenceGenerator(name = "wish_generator", sequenceName = "wish_user", allocationSize = 50)
    @Column(name = "id")
    private long id;
    private String username;

    @ManyToMany(mappedBy = "users")
    private Set<Part> parts = new HashSet<>();


}
