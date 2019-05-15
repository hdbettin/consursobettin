/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concursos;

public class Envio {
    private final String NombreEquipo;
    private final int numeroProblema;
    private final String respuesta;
    private final Estado evaluacion;

    public Envio(String NombreEquipo, int numeroProblema, String respuesta, Estado evaluacion) {
        this.NombreEquipo = NombreEquipo;
        this.numeroProblema = numeroProblema;
        this.respuesta = respuesta;
        this.evaluacion = evaluacion;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Envio clon = new Envio(this.NombreEquipo, this.numeroProblema, this.respuesta, this.evaluacion);
        return clon;
    }
    
    

    public String getNombreEquipo() {
        return NombreEquipo;
    }

    public int getNumeroProblema() {
        return numeroProblema;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public Estado getEvaluacion() {
        return evaluacion;
    }
    
}
