/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concursos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConcursoLimitado extends Concurso {
    protected int numIntento;
    private ArrayList<Intento> intentos;
    
    public ConcursoLimitado(String nombre, int numProblemas, int tiempoConcurso, int numIntento) {
        super(nombre, numProblemas, tiempoConcurso);
        this.numIntento = numIntento;
        intentos = new ArrayList<>();
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        ConcursoLimitado clon = new ConcursoLimitado(this.nombre, this.numProblemas, this.tiempoConcurso, this.numIntento);
        clon.equipos = (ArrayList<String>) this.equipos.clone();
        clon.soluciones = (ArrayList<String>) this.soluciones.clone();
        for(int i = 0; i <  clon.equipos.size(); i++){
            for(int j = 1; j <= clon.soluciones.size(); j++){ 
                clon.intentos.add(new Intento(clon.equipos.get(i), j));
            }
        }
        return clon;
    }
    
    public int obtenerIntento(String equipo, int prob){
        for(int j = 0; j < this.intentos.size(); j++){ 
            if(intentos.get(j).getEquipo().equals(equipo) && intentos.get(j).getNumPregunta() == prob){
                return intentos.get(j).getNumPregunta();
            }
        }
        this.intentos.add(new Intento(equipo, prob));
        
        return 0;
    }
    
    public void cambiarIntento(String equipo, int prob, int intent){
        for(int j = 0; j < this.intentos.size(); j++){ 
            if(intentos.get(j).getEquipo().equals(equipo) && intentos.get(j).getNumPregunta() == prob){
                intentos.get(j).setNumIntento(intent);
            }
        }
    }

    @Override
    public Envio registrarEnvio(String equipo, int numeroDeProblema, String respuesta) {
        if(infoCorrecta(equipo, numeroDeProblema, respuesta) && this.enMarcha() && !respuestaAceptada(equipo, numeroDeProblema)){
            
            int intent = obtenerIntento(equipo, numeroDeProblema);
            if(intent < numIntento){
                Estado estado;
                if(this.conseguirSolucion(numeroDeProblema).equals(respuesta)){
                    estado = Estado.ACEPTADO;
                    cambiarPuntos(equipo, 3);
                }else{
                    estado = Estado.RECHAZADO;
                    cambiarPuntos(equipo, -1);
                }
                Envio envio = new Envio(equipo, numeroDeProblema, respuesta, estado);
                envios.add(envio);
                intent++;
                cambiarIntento(equipo, numeroDeProblema, intent);
                return envio;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}
