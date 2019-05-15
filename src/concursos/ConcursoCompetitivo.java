package concursos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConcursoCompetitivo extends Concurso {
    
    private Map<Integer, Boolean> resuelto;
    
    public ConcursoCompetitivo(String nombre, int numProblemas, int tiempoConcurso) {
        super(nombre, numProblemas, tiempoConcurso);
        resuelto = new HashMap<>();
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        ConcursoCompetitivo clon = new ConcursoCompetitivo(this.nombre, this.numProblemas, this.tiempoConcurso);
        clon.equipos = (ArrayList<String>) this.equipos.clone();
        clon.soluciones = (ArrayList<String>) this.soluciones.clone();
        
        for(int i = 0; i <  clon.soluciones.size(); i++){
            clon.resuelto.put(i+1, false);
        }
        
        return clon;
    }
    
    @Override
    public void agregarSolucion(String solucion){
        if(soluciones.size() < numProblemas){
            soluciones.add(solucion);
            resuelto.put(soluciones.size(), false);
            if(soluciones.size() == numProblemas){
                iniciado = true;
                this.inicioTiempo = System.currentTimeMillis();
                this.finalTiempo = this.inicioTiempo + this.tiempoConcurso*60000;
                this.envios = new ArrayList<>();
                
            }
        }else{
            System.out.println("No se puede agregar mas soluciones");
        }
    }
    
    @Override
    public Envio registrarEnvio(String equipo, int numeroDeProblema, String respuesta) {
        if(infoCorrecta(equipo, numeroDeProblema, respuesta) && this.enMarcha() && !respuestaAceptada(equipo, numeroDeProblema)){
            
            
            if(!resuelto.get(numeroDeProblema)){
                Estado estado;
                if(this.conseguirSolucion(numeroDeProblema).equals(respuesta)){
                    estado = Estado.ACEPTADO;
                    resuelto.put(numeroDeProblema, true);
                    cambiarPuntos(equipo, 3);
                }else{
                    estado = Estado.RECHAZADO;
                    cambiarPuntos(equipo, -1);
                }
                Envio envio = new Envio(equipo, numeroDeProblema, respuesta, estado);
                envios.add(envio);
                return envio;
            }
            return null;
        }else{
            return null;
        }
    }
}
