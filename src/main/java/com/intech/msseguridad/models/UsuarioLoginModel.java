package com.intech.msseguridad.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "usuario_login")
public class UsuarioLoginModel {

    @Id
    @Column(name = "loginid")
    private Integer loginid;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

}
