package concursos;

import java.util.ArrayList;

public class Concursos {
    public static void main(String[] args) {
        Concurso a = new ConcursoLimitado("Sesión 1", 2, 5, 1);
        a.agregarSolucion("23");
        a.agregarSolucion("15");
        Concurso b = new ConcursoCompetitivo("Sesión 2", 3, 15);
        b.agregarSolucion("AACTG");
        b.agregarSolucion("null");
        b.agregarSolucion("[13, 98]");
        Concurso c = new ConcursoSecuencial("Sesión 3", 3, 30, 2);
        c.agregarSolucion("null");
        c.agregarSolucion("[0, 3]");
        c.agregarSolucion("AAA");
        
        ArrayList<Concurso> concursos = new ArrayList<>();
        concursos.add(a);
        concursos.add(b);
        concursos.add(c);
        
        for (Concurso concurso : concursos) {
            concurso.agregarEquipos("Equipo 1", "Equipo 2", "Equipo 3");
            concurso.iniciar();
            for(int i = 1; i <= concurso.getSoluciones().size(); i++){
                concurso.registrarEnvio("Equipo 1", i, "null");
                concurso.registrarEnvio("Equipo 2", i, "null");
                concurso.registrarEnvio("Equipo 3", i, "null");
            }
        }
        
        concursos.forEach((concurso) -> {
            concurso.imprimir();
        });
        
    }
}
