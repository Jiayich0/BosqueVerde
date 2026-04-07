package bosquedeletras.facade;

public class SistemaBDL {

	private static SistemaBDL instancia;
	private ControlVendedor controlVendedor;
	private ControlLibro controlLibro;
	private ControlFactura controlFactura;

	private SistemaBDL() {
		this.controlVendedor = new ControlVendedor();
		this.controlLibro = new ControlLibro();
		this.controlFactura = new ControlFactura();
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
	
	public ControlFactura getControlFactura() {
		return controlFactura;
	}
}
