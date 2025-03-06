package com.postit.sharedlibs.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    @Column(updatable = false,name = "account_id")
    private String account_id;

    @CreatedDate
    @Column(updatable = false, name = "created_date")
    private LocalDateTime created_date;

    @Column(updatable = false, name = "created_page_id")
    private String created_page_id;

    @Setter
	@Column(name = "last_modified_account_id")
    private String last_modified_account_id;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime last_modified_date;

    @Setter
    @Column(name = "last_modified_page_id")
    private String last_modified_page_id;
}
