package com.example.proyecto_dam_tarea01;

import android.os.Parcel;
import android.os.Parcelable;

public class MatriculaData implements Parcelable {
    private String alumno;
    private String escuela;
    private String carrera;
    private double gastosAdicionales;
    private double montoGastosAdicionales;
    private int numCuotas;
    private double costoCarrera;
    private double pension;
    private double totalPagar;
    private boolean carnetBiblioteca;
    private boolean carnetMedioPasaje;

    public MatriculaData() {
        // Constructor vacío
    }

    protected MatriculaData(Parcel in) {
        alumno = in.readString();
        escuela = in.readString();
        carrera = in.readString();
        gastosAdicionales = in.readDouble();
        montoGastosAdicionales = in.readDouble();
        numCuotas = in.readInt();
        costoCarrera = in.readDouble();
        pension = in.readDouble();
        totalPagar = in.readDouble();
        carnetBiblioteca = in.readByte() != 0;
        carnetMedioPasaje = in.readByte() != 0;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setGastosAdicionales(double gastosAdicionales) {
        this.gastosAdicionales = gastosAdicionales;
    }
    public void setMontoGastosAdicionales(double montoGastosAdicionales) {
        this.montoGastosAdicionales = montoGastosAdicionales;
    }

    public void setNumCuotas(int numCuotas) {
        this.numCuotas = numCuotas;
    }

    public void setCostoCarrera(double costoCarrera) {
        this.costoCarrera = costoCarrera;
    }

    public void setPension(double pension) {
        this.pension = pension;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public void setCarnetBiblioteca(boolean carnetBiblioteca) {
        this.carnetBiblioteca = carnetBiblioteca;
    }

    public void setCarnetMedioPasaje(boolean carnetMedioPasaje) {
        this.carnetMedioPasaje = carnetMedioPasaje;
    }

    public String getAlumno() {
        return alumno;
    }

    public String getEscuela() {
        return escuela;
    }

    public String getCarrera() {
        return carrera;
    }

    public double getGastosAdicionales() {
        return gastosAdicionales;
    }

    public double getMontoGastosAdicionales() {
        return montoGastosAdicionales;
    }

    public int getNumCuotas() {
        return numCuotas;
    }

    public double getCostoCarrera() {
        return costoCarrera;
    }

    public double getPension() {
        return pension;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public boolean isCarnetBiblioteca() {
        return carnetBiblioteca;
    }

    public boolean isCarnetMedioPasaje() {
        return carnetMedioPasaje;
    }

    public static final Creator<MatriculaData> CREATOR = new Creator<MatriculaData>() {
        @Override
        public MatriculaData createFromParcel(Parcel in) {
            return new MatriculaData(in);
        }

        @Override
        public MatriculaData[] newArray(int size) {
            return new MatriculaData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(alumno);
        dest.writeString(escuela);
        dest.writeString(carrera);
        dest.writeDouble(gastosAdicionales);
        dest.writeDouble(montoGastosAdicionales);
        dest.writeInt(numCuotas);
        dest.writeDouble(costoCarrera);
        dest.writeDouble(pension);
        dest.writeDouble(totalPagar);
        dest.writeByte((byte) (carnetBiblioteca ? 1 : 0));
        dest.writeByte((byte) (carnetMedioPasaje ? 1 : 0));
    }
}