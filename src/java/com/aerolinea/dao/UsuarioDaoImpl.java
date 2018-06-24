package com.aerolinea.dao;

import com.aerolinea.entidad.Paises;
import com.aerolinea.entidad.Roles;
import com.aerolinea.entidad.Usuarios;
import com.aerolinea.util.HibernateUtil;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDaoImpl implements UsuarioDao {

    @Override
    public void guardarUsuario(Usuarios u) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            s.beginTransaction();
            s.saveOrUpdate(u);
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
    }

    @Override
    public List<Usuarios> consultarUsuarios() {
        List<Usuarios> lista;
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            s.beginTransaction();
            Query q = s.createQuery(
                    "select u from Usuarios u join fetch u.paises join fetch u.roles"); // fecth para hacer el join con los campos de referencia, al ser hibernate lazy
            lista = q.list();
            s.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            return null;
        } finally {
            s.close();
        }
    }

    @Override
    public Usuarios validarUsuario(Usuarios u) {
        Usuarios usuario = null;
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            s.beginTransaction();
            Query q = s.createQuery("select u from Usuarios u"
                    + " where idusuario=:usuario and clave =:clave");
            q.setParameter("usuario", u.getIdusuario());
            q.setParameter("clave", sha1(u.getClave()));
            usuario = (Usuarios) q.uniqueResult();
            s.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
        return usuario;
    }

    @Override
    public List<Roles> getRoles() {
        List<Roles> lista;
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            s.beginTransaction();
            Query q = s.createQuery("from Rol");
            lista = q.list();

            s.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            return null;
        } finally {
            s.close();
        }
    }

    @Override
    public List<Paises> getPaises() {
        List<Paises> lista;
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            s.beginTransaction();
            Query q = s.createQuery("from Paises");
            lista = q.list();
            s.getTransaction().commit();
            return lista;
        } catch (Exception e) {
            return null;
        } finally {
            s.close();
        }
    }

    @Override
    public Usuarios getUsuario(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void EliminarUsuario(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
