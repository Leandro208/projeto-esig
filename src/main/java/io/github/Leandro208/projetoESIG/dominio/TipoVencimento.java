package io.github.Leandro208.projetoESIG.dominio;

public enum TipoVencimento {
	CREDITO(1), 
	DEBITO(2);
	
	private final int codigo;

	private TipoVencimento(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public static TipoVencimento valueOf(int codigo) {
		for(TipoVencimento v : TipoVencimento.values()) {
			if (v.getCodigo() == codigo) {
				return v;
			}
		}
		throw new IllegalArgumentException("Código de Status inválido!");
	}
}
