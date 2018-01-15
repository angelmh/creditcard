package com.jrzdeveloper.creditcard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Angel on 23/10/2017.
 */

public class PersonaCliente implements Parcelable {
    private String nombre;
    private String apellido;
    private String calleynumero;
    private String ciudad;
    private String estado;
    private int codigopostal;

    public PersonaCliente(String nombre, String apellido, String calleynumero, String ciudad, String estado, int codigopostal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.calleynumero = calleynumero;
        this.ciudad = ciudad;
        this.estado = estado;
        this.codigopostal = codigopostal;
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

    public String getCalleynumero() {
        return calleynumero;
    }

    public void setCalleynumero(String calleynumero) {
        this.calleynumero = calleynumero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(int codigopostal) {
        this.codigopostal = codigopostal;
    }

    protected PersonaCliente(Parcel in) {
        nombre = in.readString();
        apellido = in.readString();
        calleynumero = in.readString();
        ciudad = in.readString();
        estado = in.readString();
        codigopostal = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(apellido);
        dest.writeString(calleynumero);
        dest.writeString(ciudad);
        dest.writeString(estado);
        dest.writeInt(codigopostal);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PersonaCliente> CREATOR = new Parcelable.Creator<PersonaCliente>() {
        @Override
        public PersonaCliente createFromParcel(Parcel in) {
            return new PersonaCliente(in);
        }

        @Override
        public PersonaCliente[] newArray(int size) {
            return new PersonaCliente[size];
        }
    };
}