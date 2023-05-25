package ar.edu.unlam.tallerweb1.infrastructure.Transaccion;

import ar.edu.unlam.tallerweb1.domain.Transaccion.Transaccion;

import java.util.List;

public interface RepositorioTransaccion {

    public List<Transaccion> buscarTransaccionPorDetalle(String detalle) ;
    public void guardarTransaccion(Transaccion transaccion);
    public void modificar(Transaccion transaccion);

    public List<Transaccion> listarTransaccion();
}