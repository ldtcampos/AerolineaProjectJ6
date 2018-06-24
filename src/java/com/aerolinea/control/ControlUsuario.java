package com.aerolinea.control;

import com.aerolinea.dao.UsuarioDao;
import com.aerolinea.dao.UsuarioDaoImpl;
import com.aerolinea.entidad.Paises;
import com.aerolinea.entidad.Roles;
import com.aerolinea.entidad.Usuarios;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControlUsuario {

    private UsuarioDao dao;

    @Autowired
    public void setDao(UsuarioDao dao) {
        this.dao = dao;
    }

    @RequestMapping("/login")
    public String logins(@RequestParam("txtUsuario") String usuario,
            @RequestParam("txtClave") String clave, HttpServletRequest req) {
        Usuarios u = new Usuarios();
        u.setIdusuario(usuario);
        u.setClave(clave);
        Usuarios usuarioLogueado = dao.validarUsuario(u);
        if (usuarioLogueado != null) {
            req.getSession().setAttribute("usuario", usuarioLogueado.getIdusuario());
            req.getSession().setAttribute("idrol", usuarioLogueado.getRoles().getIdrol());
            req.getSession().setAttribute("correo", usuarioLogueado.getEmail());
            req.getSession().setAttribute("nombre", usuarioLogueado.getNombres() + " " + usuarioLogueado.getApellidos()); //req.getSession().setMaxInactiveInterval(10); // 10 segundos

            return "redirect:/principal";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping(value = "/usuarios", method = GET)
    public ModelAndView ListaUsuarios() {
        ModelAndView mv = new ModelAndView("usuario");
        String msg = "Listado de usuarios";
        try {
            List<Usuarios> lista = dao.consultarUsuarios();
            mv.addObject("usuarios", lista);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        mv.addObject("mensaje", msg);
        return mv;
    }

    @RequestMapping(value = "/registrar", method = GET)
    public String mostrarFormRegistro(Map<String, Object> model) {
        Usuarios userForm = new Usuarios();
        model.put("userForm", userForm);
        try {
            List<Paises> p = dao.getPaises();
            List<Roles> r = dao.getRoles();
            model.put("paises", p);
            model.put("roles", r);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "registrarse";
    }

    @RequestMapping(value = "/addUsuario", method = RequestMethod.POST)
    
    public String addUsuario(@Valid @ModelAttribute("userForm") Usuarios u,
            BindingResult result) {
        if (result.hasErrors()) {
            return "registrarse";
        }
        try {
            u.setClave(UsuarioDaoImpl.sha1(u.getClave()));
            dao.guardarUsuario(u);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/usuarios";
    }
}
