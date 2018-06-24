package com.aerolinea.dao;

import com.aerolinea.entidad.Paises;
import com.aerolinea.entidad.Roles;
import com.aerolinea.entidad.Usuarios;
import java.util.List;

public interface UsuarioDao {

    public void guardarUsuario(Usuarios u);

    public List<Usuarios> consultarUsuarios();

    public Usuarios validarUsuario(Usuarios u);

    public List<Roles> getRoles();

    public List<Paises> getPaises();

    public Usuarios getUsuario(String id);

    public void EliminarUsuario(String id);
}
