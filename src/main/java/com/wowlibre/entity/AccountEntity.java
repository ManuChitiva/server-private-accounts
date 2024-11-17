package com.wowlibre.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.*;
import java.time.*;

@Data
@Entity
@Table(name = "account")
public class AccountEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "salt")
    private byte[] salt;
    @Column(name = "verifier")
    private byte[] verifier;
    @Column(name = "totp_secret")
    private String otpSecret;
    @Column(name = "email")
    private String email;
    @Column(name = "joindate")
    private LocalDate joinDate;
    @Column(name = "last_ip")
    private String lastIp;
    @Column(name = "failed_logins")
    private String failedLogins;
    private boolean locked;
    @Column(name = "last_login")
    private LocalDate lastLogin;
    private boolean online;
    private String expansion;
    @Column(name = "mutetime")
    private Long muteTime;
    @Column(name = "mutereason")
    private String muteReason;
    @Column(name = "muteby")
    private String muteBy;
    private String os;

    @PrePersist
    public void prePersist() {
        this.joinDate = LocalDate.now();
        this.failedLogins = "0";
        this.lastIp = "";
        this.os = "";
        this.muteBy = "";
        this.muteReason = "";
        this.expansion = "2";
        this.muteTime = 0L;
    }

    public AccountEntity() {
    }
}
