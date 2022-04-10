package com.gini.validationunit.domaine.postgress;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "parts")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wish_generator")
    @SequenceGenerator(name = "wish_generator", sequenceName = "wish_part", allocationSize = 50)
    @Column(name = "id")
    private Long id;
    private String partId;
    private String partName;
    private String partNumber;
    private String manufacturer;
    @Column(name = "price_ron")
    private BigDecimal priceRON;
    @Column(name = "price_euro")
    private BigDecimal priceEURO;
    @Column(name = "price_usd")
    private BigDecimal priceUSD;

    @ManyToMany
    private Set<User> users = new HashSet<>();

}
