package com.aerolinea.entidad;
// Generated 24-jun-2018 10:23:22 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Aeropuertos generated by hbm2java
 */
@Entity
@Table(name="aeropuertos"
    ,catalog="aerolinea"
)
public class Aeropuertos  implements java.io.Serializable {


     private Integer idaeropuerto;
     private Paises paises;
     private String aeropuerto;
     private String ciudad;
     private Set<Vuelos> vuelosesForIdorigen = new HashSet<Vuelos>(0);
     private Set<Vuelos> vuelosesForIddestino = new HashSet<Vuelos>(0);

    public Aeropuertos() {
    }

	
    public Aeropuertos(Paises paises, String aeropuerto, String ciudad) {
        this.paises = paises;
        this.aeropuerto = aeropuerto;
        this.ciudad = ciudad;
    }
    public Aeropuertos(Paises paises, String aeropuerto, String ciudad, Set<Vuelos> vuelosesForIdorigen, Set<Vuelos> vuelosesForIddestino) {
       this.paises = paises;
       this.aeropuerto = aeropuerto;
       this.ciudad = ciudad;
       this.vuelosesForIdorigen = vuelosesForIdorigen;
       this.vuelosesForIddestino = vuelosesForIddestino;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idaeropuerto", unique=true, nullable=false)
    public Integer getIdaeropuerto() {
        return this.idaeropuerto;
    }
    
    public void setIdaeropuerto(Integer idaeropuerto) {
        this.idaeropuerto = idaeropuerto;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idpais", nullable=false)
    public Paises getPaises() {
        return this.paises;
    }
    
    public void setPaises(Paises paises) {
        this.paises = paises;
    }

    
    @Column(name="aeropuerto", nullable=false, length=60)
    public String getAeropuerto() {
        return this.aeropuerto;
    }
    
    public void setAeropuerto(String aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    
    @Column(name="ciudad", nullable=false, length=45)
    public String getCiudad() {
        return this.ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="aeropuertosByIdorigen")
    public Set<Vuelos> getVuelosesForIdorigen() {
        return this.vuelosesForIdorigen;
    }
    
    public void setVuelosesForIdorigen(Set<Vuelos> vuelosesForIdorigen) {
        this.vuelosesForIdorigen = vuelosesForIdorigen;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="aeropuertosByIddestino")
    public Set<Vuelos> getVuelosesForIddestino() {
        return this.vuelosesForIddestino;
    }
    
    public void setVuelosesForIddestino(Set<Vuelos> vuelosesForIddestino) {
        this.vuelosesForIddestino = vuelosesForIddestino;
    }




}


