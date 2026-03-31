package com.bank.bankapplication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BasicEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdTime;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedTime;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;

    @PrePersist
    public  void prePersist(){
        this.createdTime = LocalDateTime.now();
        if(this.createdTime == null){
            this.createdBy ="SYSTEM";
        }
    }

    @PreUpdate
    public void preUpdate(){

        this.updatedTime = LocalDateTime.now();
        if(this.updatedTime == null){
            this.updatedBy = "UPDATE-SYSTEM";
        }
    }
}
