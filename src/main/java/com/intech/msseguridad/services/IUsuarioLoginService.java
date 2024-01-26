package com.intech.msseguridad.services;

import com.intech.msseguridad.models.UsuarioLoginModel;

import java.util.List;

public interface IUsuarioLoginService {
    public List<UsuarioLoginModel> getAcces();

    public Boolean validatedCredentials(String Nickname, String Password);
}
