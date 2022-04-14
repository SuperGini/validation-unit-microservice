package com.gini.validationunit.domaine.postgress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "parts")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wish_generator")
    @SequenceGenerator(name = "wish_generator", sequenceName = "wish_part", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "part_id")
    private String partId;
    @Column(name = "part_name")
    private String partName;
    @Column(name = "part_number")
    private String partNumber;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "price_ron")
    private BigDecimal priceRON;
    @Column(name = "price_euro")
    private BigDecimal priceEURO;
    @Column(name = "price_usd")
    private BigDecimal priceUSD;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> users = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return Objects.equals(partNumber, part.partNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partNumber);
    }
}
