package com.example.documenttest.store.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "documents")
@NoArgsConstructor
@AllArgsConstructor
public class Documents {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(nullable = false)
    private UUID id;

    @Column(name = "type")
    private String type;

    @Column(name = "number")
    private String number;

    @Column(name = "date")
    private Date date;

    @Column(name = "content")
    private String  content;
}
