package com.jrzdeveloper.creditcard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Angel on 23/10/2017.
 */

public class Card implements Parcelable {
    private long numero;
    private int mes;
    private int anio;
    private int codigo;

    public Card(long numero, int mes, int anio, int codigo) {
        this.numero = numero;
        this.mes = mes;
        this.anio = anio;
        this.codigo = codigo;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    protected Card(Parcel in) {
        numero = in.readLong();
        mes = in.readInt();
        anio = in.readInt();
        codigo = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(numero);
        dest.writeInt(mes);
        dest.writeInt(anio);
        dest.writeInt(codigo);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
}