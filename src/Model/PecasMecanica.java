package Model;

public class PecasMecanica extends Pecas  {
	
	private String Material;

	public PecasMecanica(String nomePeca, float valorPecas, String unid, int quat_Pecas, float valor_unid,
			String tipo_elemento, String material) {
		super(nomePeca, valorPecas, unid, quat_Pecas, valor_unid, tipo_elemento);
		
		Material = material;
	}

	public String getMaterial() {
		return Material;
	}

	public void setMaterial(String material) {
		Material = material;
	}

	@Override
	public String toString() {
		return "PecasMecanica [Material=" + Material + ", getNomePeca()=" + getNomePeca() + ", getValorPecas()="
				+ getValorPecas() + ", getUnid()=" + getUnid() + ", getTipo_elemento()=" + getTipo_elemento()
				+ ", getQuat_Pecas()=" + getQuat_Pecas() + ", toString()=" + super.toString() + ", getValor_unid()="
				+ getValor_unid() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}


	
	
	
}
