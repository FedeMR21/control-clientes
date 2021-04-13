package ar.com.g5;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cliente {
   @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Debes especificar el nombre")
    
    private String nombre;
    
    @NotNull(message = "Debes especificar el apellido")
    @Size(min = 1, max = 50, message = "El apellido debe medir entre 1 y 50")
    private String apellido;
    
    @NotNull(message = "Debes especificar el email")
    @Size(min = 1, max = 50, message = "El email debe medir entre 1 y 50")
    private String email;
    
    @NotNull(message = "Debes especificar el telefono")
    @Size(min = 5, max = 20, message = "El telefono debe medir entre 5 y 20")
    private String telefono;

    @NotNull(message = "Debes especificar el saldo")
    private Float saldo;

    public Cliente() {
    }


    public Cliente(String nombre, String apellido, String email, String telefono, Float saldo, Integer id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.saldo = saldo;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }
    
}
