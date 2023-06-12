package ar.edu.unlam.tallerweb1.domain.Presupuesto;

import ar.edu.unlam.tallerweb1.domain.Categorias.Categoria;
import ar.edu.unlam.tallerweb1.domain.Moneda.Moneda;
import ar.edu.unlam.tallerweb1.infrastructure.Categoria.RepositorioCategoria;
import ar.edu.unlam.tallerweb1.infrastructure.Presupuesto.RepositorioPresupuesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioDePresupuestoImpl implements ServicioDePresupuesto {

    private final RepositorioPresupuesto repositorioPresupuesto;
    private final RepositorioCategoria repositorioCategoria;

    @Autowired
    public ServicioDePresupuestoImpl(RepositorioPresupuesto servicioPresupuestoDao, RepositorioCategoria repositorioCategoria) {
        this.repositorioPresupuesto = servicioPresupuestoDao;
        this.repositorioCategoria=repositorioCategoria;
    }


    @Override
    public Boolean establecerPresupuesto(Double monto, String fechaDesde, String fechaHasta, Moneda moneda,  Categoria categoria) {
        Boolean seRegistro = false;
        List <Presupuesto> validacionDeCategoria= repositorioPresupuesto.listarPresupuesto();
        if (monto > 0){
            Presupuesto presupuesto = new Presupuesto(monto, fechaDesde, fechaHasta, moneda, categoria);
            repositorioPresupuesto.guardar(presupuesto);
            seRegistro = true;
        }

        return seRegistro;
    }

    @Override
    public List<Presupuesto> listarPresupuestos() {
        return repositorioPresupuesto.listarPresupuesto();
    }
    @Override
    public List<Categoria> listarCategorias() {
        return repositorioCategoria.listarCategoriaPorPresupuesto();
    }





}
