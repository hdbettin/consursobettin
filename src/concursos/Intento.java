/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concursos;

public class Intento {
    private String equipo;
    private int numIntento;
    private int numPregunta;

    public Intento(String equipo, int numPregunta) {
        this.equipo = equipo;
        this.numIntento = 0;
        this.numPregunta = numPregunta;
    }
    
    public Intento(Intento intento){
        this.equipo = intento.equipo;
        this.numIntento = intento.numIntento;
        this.numPregunta = intento.numPregunta;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getNumIntento() {
        return numIntento;
    }

    public void setNumIntento(int numIntento) {
        this.numIntento = numIntento;
    }

    public int getNumPregunta() {
        return numPregunta;
    }

    public void setNumPregunta(int numPregunta) {
        this.numPregunta = numPregunta;
    }
    
}
