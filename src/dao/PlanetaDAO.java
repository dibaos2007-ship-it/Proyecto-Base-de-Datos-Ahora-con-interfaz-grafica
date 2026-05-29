package dao;

import modelo.Planeta;
import java.util.List;
//grhtrhfvfv
public interface PlanetaDAO {
    Planeta consultarPorNombre(String nombrePlaneta);
    List<Planeta> consultarTodos();
    boolean agregarPlaneta(Planeta nuevoPlaneta);
    List<Planeta> filtrarPorCriterio(String criterio, String valor);
}