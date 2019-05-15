
package concursos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConcursoSecuencial extends ConcursoLimitado {
    private Map<String, Integer> problemaActual;

    public ConcursoSecuencial(String nombre, int numProblemas, int tiempoConcurso, int intent) {
        super(nombre, numProblemas, tiempoConcurso, intent);
        problemaActual = new HashMap<>();
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        ConcursoSecuencial clon = new ConcursoSecuencial(this.nombre, this.numProblemas, this.tiempoConcurso, this.numIntento);
        clon.equipos = (ArrayList<String>) this.equipos.clone();
        clon.soluciones = (ArrayList<String>) this.soluciones.clone();
        for(int i = 0; i <  clon.equipos.size(); i++){
            clon.problemaActual.put(clon.equipos.get(i), 1);
        }
        return clon;
    }
    
    @Override
    public void agregarEquipos(String ...nombres){
        super.agregarEquipos(nombres);
        for (String name : nombres) {
            problemaActual.put(name, 1);
        }
    }
    
    @Override
    public Envio registrarEnvio(String equipo, int numeroDeProblema, String respuesta) {
        if(infoCorrecta(equipo, numeroDeProblema, respuesta) && this.enMarcha() && !respuestaAceptada(equipo, numeroDeProblema)){
            int actual = problemaActual.get(equipo);
            int intent = obtenerIntento(equipo, numeroDeProblema);
            if(actual == numeroDeProblema && actual <= numProblemas){
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
                    if(actual <= numProblemas && estado == Estado.ACEPTADO){
                        problemaActual.put(equipo, ++actual);
                    }
                    
                    intent++;
                    cambiarIntento(equipo, numeroDeProblema, intent);
                    return envio;
                }else{
                    if(++actual <= numProblemas)problemaActual.put(equipo, actual);
                }
            }
            return null;
        }else{
            return null;
        }
    }
    
    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println("Estado del concurso: ");
        for (String equipo : equipos) {
            System.out.println("    -" + equipo + " -> Problema actual: " + problemaActual.get(equipo));
        }
    }
}
