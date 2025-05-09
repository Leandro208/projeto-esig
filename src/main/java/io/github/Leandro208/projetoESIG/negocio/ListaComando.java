package io.github.Leandro208.projetoESIG.negocio;

public class ListaComando {
	public static final Comando CADASTRAR = new Comando(1, "io.github.Leandro208.projetoESIG.negocio.CadastroCrudOperation");
	public static final Comando ALTERAR = new Comando(2, "io.github.Leandro208.projetoESIG.negocio.CadastroCrudOperation");
	public static final Comando REMOVER = new Comando(3, "io.github.Leandro208.projetoESIG.negocio.CadastroCrudOperation");
	
	
	public static final Comando CADASTRO_PESSOA = new Comando(10, "io.github.Leandro208.projetoESIG.negocio.PessoaCrudOperation");
	public static final Comando ALTERAR_PESSOA = new Comando(11, "io.github.Leandro208.projetoESIG.negocio.PessoaCrudOperation");
	public static final Comando REMOVER_PESSOA = new Comando(12, "io.github.Leandro208.projetoESIG.negocio.PessoaCrudOperation");
}
