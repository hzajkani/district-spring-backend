package com.coding.district.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "district")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class District implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<Vote> votes;

    @Column(name = "name")
    private String name;

    @Column(name = "area")
    private Double area;

    @Column(name = "population")
    private Double population;

    @Column(name = "foreigner_Pct")
    private Double foreignerPct;

    @Column(name = "ageLt18Pct")
    private Double ageLt18Pct;

    @Column(name = "ageGt75Pct")
    private Double ageGt75Pct;

    @Column(name = "areaSettlePct")
    private Double areaSettlePct;

    @Column(name = "area_Nature_Pct")
    private Double areaNaturePct;

    @Column(name = "living_Space")
    private Double livingSpace;

    @Column(name = "cars")
    private Double cars;

    @Column(name = "income")
    private Double income;

    @Column(name = "bip")
    private Double bip;

    @Column(name = "unemployment_rate")
    private Double unemploymentRate;

}
