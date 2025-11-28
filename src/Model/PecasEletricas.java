package Model;

public class PecasEletricas extends Pecas   {

	private String voltagem;

	public PecasEletricas(String nomePeca, float valorPecas, String unid, int quat_Pecas, float valor_unid,
			String tipo_elemento, String voltagem) {
		super(nomePeca, valorPecas, unid, quat_Pecas, valor_unid, tipo_elemento);
		
		this.voltagem = voltagem;
	}

	public String getVoltagem() {
		return voltagem;
	}

	public void setVoltagem(String voltagem) {
		this.voltagem = voltagem;
	}

	@Override
	public String toString() {
		return "PecasEletricas [voltagem=" + voltagem + ", getNomePeca()=" + getNomePeca() + ", getValorPecas()="
				+ getValorPecas() + ", getUnid()=" + getUnid() + ", getTipo_elemento()=" + getTipo_elemento()
				+ ", getQuat_Pecas()=" + getQuat_Pecas() + ", toString()=" + super.toString() + ", getValor_unid()="
				+ getValor_unid() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
}
