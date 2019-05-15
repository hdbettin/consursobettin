package concursos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Concurso {
    protected String nombre; 
    protected final int numProblemas;
    protected final int tiempoConcurso;
    protected ArrayList<String> equipos;
    protected ArrayList<String> soluciones;
    protected long inicioTiempo;
    protected long finalTiempo;
    
    protected ArrayList<Envio> envios;
    protected boolean iniciado;
    
    protected Map<String, PuntuacionEquipo> puntuacion;

    public Concurso(String nombre, int numProblemas, int tiempoConcurso) {
        this.nombre = nombre;
        this.numProblemas = numProblemas;
        this.tiempoConcurso = tiempoConcurso;
        this.equipos = new ArrayList<>();
        this.soluciones = new ArrayList<>();
        this.iniciado = false;
        this.envios = null;
        this.puntuacion = new HashMap<>();
    }
   
    
    public void agregarEquipos(String ...nombres){
        equipos.addAll(Arrays.asList(nombres));
        
        for (String name : nombres) {
            puntuacion.put(name, new PuntuacionEquipo(name));
        }
    }
    public void cambiarPuntos(String equipo, int puntos){
        PuntuacionEquipo punt = puntuacion.get(equipo);
        punt.addPunto(puntos);
        puntuacion.put(equipo, punt);
    }
    
    public void iniciar(){
        this.iniciado = true;
        this.inicioTiempo = System.currentTimeMillis();
        this.finalTiempo = this.inicioTiempo + this.tiempoConcurso*60000;
        this.envios = new ArrayList<>();
    }
    
    public void agregarSolucion(String solucion){
        if(soluciones.size() < numProblemas){
            soluciones.add(solucion);
        }else{
            System.out.println("No se puede agregar mas soluciones");
        }
    }
    
    
    public boolean enMarcha(){
        return System.currentTimeMillis() < this.finalTiempo;
    }
    
    public boolean haFinalizado(){
        boolean retornar = false;
        if(iniciado) retornar = System.currentTimeMillis() >= this.finalTiempo;
        return retornar;
    }
    
    public String conseguirSolucion(int num){
        return soluciones.get(num - 1);
    }
    
    public boolean eliminarEquipo(String nombre){
        return equipos.remove(nombre);
    }
    
    public abstract Envio registrarEnvio(String equipo, int numeroDeProblema, String respuesta);
    
    public boolean infoCorrecta(String equipo, int numeroDeProblema, String respuesta){
        return !"".equals(equipo) &&
                equipo != null &&
                equipos.contains(equipo) &&
                numeroDeProblema <= numProblemas &&
                numeroDeProblema > 0 &&
                !"".equals(respuesta) &&
                respuesta != null;
    }
    
    public boolean respuestaAceptada(String equipo, int numeroDeproblema){
        for(int i = 0; i < envios.size(); i++){
            Envio envio = envios.get(i);
            if(envio.getNombreEquipo().equals(equipo) && envio.getNumeroProblema() == numeroDeproblema && envio.getEvaluacion() == Estado.ACEPTADO){
                return true;
            }
        }
        return false;
    }

    public void imprimir(){
        System.out.println("---- " + nombre + " ----");
        System.out.println("Clasificación: ");
        for (String equipo : equipos) {
            System.out.println("    -" + equipo + " -> " + puntuacion.get(equipo).getPuntos());
        }
    }
    
    public ArrayList<Envio> getEnvios() {
        return envios;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getNumProblemas() {
        return numProblemas;
    }

    public int getTiempoConcurso() {
        return tiempoConcurso;
    }

    public ArrayList<String> getEquipos() {
        return equipos;
    }

    public ArrayList<String> getSoluciones() {
        return soluciones;
    }

    public boolean isIniciado() {
        return iniciado;
    }
    
    
}

