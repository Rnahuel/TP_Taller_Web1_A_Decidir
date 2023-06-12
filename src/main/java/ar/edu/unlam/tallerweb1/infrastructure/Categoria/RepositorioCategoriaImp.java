package ar.edu.unlam.tallerweb1.infrastructure.Categoria;

import ar.edu.unlam.tallerweb1.domain.Categorias.Categoria;
import ar.edu.unlam.tallerweb1.domain.Presupuesto.Presupuesto;
import ar.edu.unlam.tallerweb1.domain.Transaccion.Transaccion;
import ar.edu.unlam.tallerweb1.infrastructure.Presupuesto.RepositorioPresupuesto;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("repositorioCategoria")
@Transactional
public class RepositorioCategoriaImp implements RepositorioCategoria{
    private final SessionFactory sessionFactory;
    private RepositorioPresupuesto repositorioPresupuesto;

    @Autowired
    public RepositorioCategoriaImp(SessionFactory sessionFactory,RepositorioPresupuesto repositorioPresupuesto){
        this.sessionFactory = sessionFactory;
        this.repositorioPresupuesto=repositorioPresupuesto;
    }


    @Override
    public List<Categoria> listarCategoria(){
        final Session session = sessionFactory.getCurrentSession();
        return (List<Categoria>) session.createCriteria(Categoria.class).list();
    }
    @Override
    public List<Categoria> listarCategoriaParaPresupuestos(){
            final Session session = sessionFactory.getCurrentSession();
            List<Categoria> categoriasSinAsignar = new ArrayList<>();

            List<Presupuesto> presupuestos = repositorioPresupuesto.listarPresupuesto();

            // Obtener todas las categorías existentes
            List<Categoria> todasCategorias = session.createCriteria(Categoria.class).list();

            for (Categoria categoria : todasCategorias) {
                boolean asignada = false;

                // Verificar si la categoría está asignada en algún presupuesto
                for (Presupuesto presupuesto : presupuestos) {
                    if (categoria.equals(presupuesto.getCategoria())) {
                        asignada = true;
                        break;
                    }
                }

                // Si la categoría no está asignada, agregarla a la lista
                if (!asignada) {
                    categoriasSinAsignar.add(categoria);
                }
            }

            return categoriasSinAsignar;
        }
        


        @Override
    public Categoria traerCategoriaPorId(long id){
        final Session session = sessionFactory.getCurrentSession();
        return session.get(Categoria.class, id);

    }
}
