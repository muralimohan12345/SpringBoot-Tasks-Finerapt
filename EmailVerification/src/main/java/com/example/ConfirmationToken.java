package com.example;

import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="confirmationToken")

public class ConfirmationToken {
	
//	Logger log =Logger.getAnonymousLogger();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="token_id")
  private long tokenId;
	   @Column(name="confirmation_token")
  private String confirmationToken;	
       @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;
       
       
  @OneToOne(targetEntity=UserEntity.class, fetch=FetchType.EAGER) 
  @JoinColumn(nullable=false, name="user_id")
  private UserEntity userEntity;
  
  public ConfirmationToken() {
	  
  }
  
  public ConfirmationToken(UserEntity userEntity) {
//	       log.info("inside the confirmation token constructor");
      this.userEntity = userEntity;
      createdDate = new Date();
//           log.info("inside the date");
      confirmationToken = UUID.randomUUID().toString();
//            log.info("inside the UUID");
  }

public long getTokenId() {
	return tokenId;
}

public void setTokenId(long tokenId) {
	this.tokenId = tokenId;
}

public String getConfirmationToken() {
	return confirmationToken;
}

public void setConfirmationToken(String confirmationToken) {
	this.confirmationToken = confirmationToken;
}

public Date getCreatedDate() {
	return createdDate;
}

public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}

public UserEntity getUserEntity() {
	return userEntity;
}

public void setUserEntity(UserEntity userEntity) {
	this.userEntity = userEntity;
}
      
  
	
}
