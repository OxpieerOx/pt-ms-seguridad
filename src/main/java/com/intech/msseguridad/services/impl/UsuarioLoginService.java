package com.intech.msseguridad.services.impl;

import com.intech.msseguridad.models.UsuarioLoginModel;
import com.intech.msseguridad.repositories.IUsuarioLoginRepository;
import com.intech.msseguridad.services.IUsuarioLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioLoginService implements IUsuarioLoginService {

    @Autowired
    IUsuarioLoginRepository usuarioLoginRepository;



    @Override
    public List<UsuarioLoginModel> getAcces() {
        return (List<UsuarioLoginModel>) usuarioLoginRepository.findAll();
    }

    @Override
    public Boolean validatedCredentials(String Nickname, String Password) {
        List<UsuarioLoginModel> result = (List<UsuarioLoginModel>) usuarioLoginRepository.findAll();
        List<UsuarioLoginModel> resultFilter = result.stream()
                .filter(t -> t.getNickname().equals(Nickname) && t.getPassword().equals(Password))
                .collect(Collectors.toList());
        if (null == resultFilter || resultFilter.isEmpty()) {
            return false;
        }
        return true;
    }
}
