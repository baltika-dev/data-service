package com.baltika.dataservice.model.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alias")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Ally {
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Id
    @Column(name = "ally", unique = true)
    private String ally;
}
