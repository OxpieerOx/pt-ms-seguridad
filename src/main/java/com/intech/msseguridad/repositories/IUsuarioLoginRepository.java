package com.intech.msseguridad.repositories;

import com.intech.msseguridad.models.UsuarioLoginModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioLoginRepository extends CrudRepository<UsuarioLoginModel, Long> {
}
