/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concursos;

public class PuntuacionEquipo {
    private final String equipo;
    private int puntos;

    public PuntuacionEquipo(String equipo) {
        this.equipo = equipo;
        this.puntos = 0;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        PuntuacionEquipo clon = new PuntuacionEquipo(this.equipo);
        clon.setPuntos(this.puntos);
        return clon; 
    }
    
    
    
    public void addPunto(int a){
        this.puntos += a;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    
    public String getEquipo() {
        return equipo;
    }

    public int getPuntos() {
        return puntos;
    }
    
    
}
