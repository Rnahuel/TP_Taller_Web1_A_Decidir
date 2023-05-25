package ar.edu.unlam.tallerweb1.delivery.Transaccion;

import ar.edu.unlam.tallerweb1.domain.Transaccion.Transaccion;
import org.springframework.beans.factory.annotation.Autowired;
import ar.edu.unlam.tallerweb1.domain.Transaccion.ServicioDeTransaccion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorDeTransaccion {

    private ServicioDeTransaccion servicioDeTransaccion;

    @Autowired
    public ControladorDeTransaccion(ServicioDeTransaccion servicioDeTransaccion){
        this.servicioDeTransaccion=servicioDeTransaccion;
    }
    @RequestMapping(path="/agregar", method = RequestMethod.GET)
    public ModelAndView crearTransaccion() {
        ModelMap map= new ModelMap();
        // map.put("datosTransaccion", new DatosTransaccion());
        map.put("datosTransaccion", new Transaccion());
        return new ModelAndView("home", map);
    }

    @RequestMapping(path="/agregar", method = RequestMethod.POST)
    public ModelAndView registrarUnaTransaccion(@ModelAttribute("datosTransaccion") Transaccion transaccion) {
        servicioDeTransaccion.registrarTransaccion(transaccion.getMonto(), transaccion.getDetalle(), transaccion.getFecha(), transaccion.getMoneda(), transaccion.getConcepto(), transaccion.getCategoria());
        ModelMap map= new ModelMap();
        map.put("msg", "Transaccion exitosa");
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(path="/home", method = RequestMethod.GET)
    public ModelAndView listarUnaTransaccion() {
        ModelMap map= new ModelMap();
        List<Transaccion> transacciones = servicioDeTransaccion.listarTransacciones();
        map.put("transacciones", transacciones);
        map.put("datosTransaccion", new Transaccion());
        return new ModelAndView("home", map);
    }
}
