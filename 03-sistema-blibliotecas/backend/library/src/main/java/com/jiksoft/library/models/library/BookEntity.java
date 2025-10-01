package com.jiksoft.library.models.library;

import java.io.Serial;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jiksoft.library.models.AbstractFoundationEntity;
import com.jiksoft.library.serializers.library.BookSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "book")
@JsonSerialize(using = BookSerializer.class)
public class BookEntity extends AbstractFoundationEntity {

    @Serial
    private static final long serialVersionUID = 1L;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "available", nullable = false)
    private Boolean available = Boolean.TRUE;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "library_id", nullable = false)
    private LibraryEntity library;
}
