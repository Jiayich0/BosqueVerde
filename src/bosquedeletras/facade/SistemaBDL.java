package bosquedeletras.facade;

public class SistemaBDL {

	private static SistemaBDL instancia;
	private ControlVendedor controlVendedor;
	private ControlLibro controlLibro;
	private ControlFactura controlFactura;
	private ControlCategoria controlCategoria;
	private ControlEditorial controlEditorial;

	private SistemaBDL() {
		this.controlVendedor = new ControlVendedor();
		this.controlLibro = new ControlLibro();
		this.controlFactura = new ControlFactura();
		this.controlCategoria = new ControlCategoria();
		this.controlEditorial = new ControlEditorial();
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

	public ControlCategoria getControlCategoria() {
		return controlCategoria;
	}
	
	public ControlEditorial getControlEditorial() {
		return controlEditorial;
	}
}
