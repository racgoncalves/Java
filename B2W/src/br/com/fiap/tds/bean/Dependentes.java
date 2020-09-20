package br.com.fiap.tds.bean;

import java.util.HashMap;
import java.util.List;

public class Dependentes {

	private String tipo;
	private byte[] certidaoCasamento;
	private byte[] certidaoNascimentoFilho;
	private byte[] rgGenitor;
	private HashMap<String, List<byte[]>> mapaDependentes;
	
	public Dependentes() {}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public byte[] getCertidaoCasamento() {
		return certidaoCasamento;
	}

	public void setCertidaoCasamento(byte[] certidaoCasamento) {
		this.certidaoCasamento = certidaoCasamento;
	}

	public byte[] getCertidaoNascimentoFilho() {
		return certidaoNascimentoFilho;
	}

	public void setCertidaoNascimentoFilho(byte[] certidaoNascimentoFilho) {
		this.certidaoNascimentoFilho = certidaoNascimentoFilho;
	}

	public byte[] getRgGenitor() {
		return rgGenitor;
	}

	public void setRgGenitor(byte[] rgGenitor) {
		this.rgGenitor = rgGenitor;
	}

	public HashMap<String, List<byte[]>> getMapaDependentes() {
		return mapaDependentes;
	}

	public void setMapaDependentes(HashMap<String, List<byte[]>> mapaDependentes) {
		this.mapaDependentes = mapaDependentes;
	}
		
}
