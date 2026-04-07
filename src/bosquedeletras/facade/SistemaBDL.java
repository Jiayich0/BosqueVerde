package bosquedeletras.facade;

import java.util.List;

import bosquedeletras.model.Vendedor;

public class SistemaBDL {

	private static SistemaBDL instancia;
	private ControlVendedor controlVendedor;
	private ControlLibro controlLibro;

	private SistemaBDL() {
		this.controlVendedor = new ControlVendedor();
		this.controlLibro = new ControlLibro();
	}

	public static SistemaBDL getInstance() {
		if (instancia == null) {
			instancia = new SistemaBDL();
		}
		return instancia;
	}
	
	public ControlVendedor getControlVendedor() {
		return controlVendedor;
	}

	public ControlLibro getControlLibro() {
		return controlLibro;
	}
}
