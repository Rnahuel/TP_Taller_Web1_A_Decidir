package ar.edu.unlam.tallerweb1.infrastructure.Transaccion;
import ar.edu.unlam.tallerweb1.domain.Presupuesto.Presupuesto;
import ar.edu.unlam.tallerweb1.domain.Transaccion.Transaccion;
import ar.edu.unlam.tallerweb1.infrastructure.Transaccion.RepositorioTransaccion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("repositorioTransaccion")
@Transactional
public class RepositorioTransaccionImpl implements RepositorioTransaccion {
    private final SessionFactory sessionFactory;

    @Autowired
    public RepositorioTransaccionImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Transaccion> buscarTransaccionPorDetalle(String detalle) {
        // Se obtiene la sesion asociada a la transaccion iniciada en el servicio que invoca a este metodo y se crea un criterio
        // de busqueda de Transaccion donde el detalle sea igual al del objeto recibido como parametro
        final Session session = sessionFactory.getCurrentSession();
        return (List<Transaccion>) session.createCriteria(Transaccion.class)
                .add(Restrictions.eq("detalle", detalle))
                .list();
    }

    @Override
    public void guardarTransaccion(Transaccion transaccion) {
        sessionFactory.getCurrentSession().save(transaccion);
    }

    @Override
    public void modificar(Transaccion transaccion) {
        sessionFactory.getCurrentSession().update(transaccion);
    }


    @Override
    public List<Transaccion> listarTransaccion(){

        final Session session = sessionFactory.getCurrentSession();
        return (List<Transaccion>) session.createCriteria(Transaccion.class).list();

    }

}