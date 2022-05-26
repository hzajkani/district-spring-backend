package com.coding.district.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vote")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Vote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private District district;

    @Column(name = "party_type")
    private String partyType;

    @Column(name = "name")
    private String name;

    @Column(name = "ranked")
    private Double ranked;

    @Column(name = "vote_type")
    private Double voteType;

    @Column(name = "count")
    private Double count;

    @Column(name = "pct")
    private Double pct;
}
