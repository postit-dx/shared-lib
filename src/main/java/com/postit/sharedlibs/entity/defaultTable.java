package com.postit.sharedlibs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class defaultTable {

    @Column(updatable = false)
    private String account_id;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_date;

    @Column(updatable = false)
    private String created_page_id;

    private String last_modified_account_id;

    @LastModifiedDate
    private LocalDateTime last_modified_date;

    private String last_modified_page_id;
}
