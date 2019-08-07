package resources;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Icons {
	//----------------------------------------------------------------------------------------------
	public static final String LOGO                  = "logo.png";
	//
	public static final String NOVO              = "novo32.png";
	public static final String SALVAR		           = "salvar.png";
	public static final String CANCELAR     		    = "cancelar.png";
	public static final String LIMPAR     			= "limpar.png";
	public static final String EXCLUIR          = "excluir.png";
	public static final String RELATORIO          = "relatorio.png";
	public static final String CONFIGUREBUTTON16       = "editToolBar16.png";
	public static final String FECHAR			       = "fechar.png";
	public static final String ESTADOSFRAME16          = "estadosFrame16.png";
	public static final String MUNICIPIOSFRAME16       = "estadosFrame16.png";
	public static final String PESSOASFRAME16          = "pessoasFrame16.png";
	public static final String ANTERIOR			       = "anterior.png";
	public static final String PROXIMO          	   = "proximo.png";
	public static final String USUARIO          	   = "usuario24.png";
	public static final String PESSOA          	   = "pessoaOn.png";
	public static final String PESSOAS          	   = "pessoas32.png";
	public static final String JAVA          	   = "java32.png";
	public static final String ARVORE          	   = "arvore32.png";
	public static final String PARCELA          	   = "parcela32.png";
	public static final String FOLHA          	   = "folha32.png";
	public static final String FUNDO          	   = "fundo.png";
	public static final String AJUDA         	   = "ajuda32.png";
	public static final String ADICIONAR16         	   = "adicionar16.png";
	public static final String EXCLUIR16        	   = "excluir16.png";
	public static final String FECHAR16        	   = "fechar16.png";
	public static final String CHART16        	   = "chart16.png";
	public static final String CHART24        	   = "chart24.png";
	public static final String CHART32        	   = "chart32.png";
	public static final String FICHA_CAMPO        	   = "fichaCampo32.png";
	public static final String CALCULADORA        	   = "calculadora32.png";
	public static final String FIM        	   = "fim32.png";
	public static final String RELATORIO32        	   = "relatorio32.png";
	public static final String CHAVE        	   = "chave32.png";
	public static final String ARVORES32     	   = "arvores32.png";
	public static final String CALC32     	   = "calc32.png";
	

	//----------------------------------------------------------------------------------------------
	private static ClassLoader loader = Icons.class.getClassLoader();
	
	public static ImageIcon getImageIcon(String imageName) {
		if (imageName==null) return null;
		imageName = Resources.ICONS_FOLDER + "/" + imageName;
		ImageIcon imageIcon = null;
		try {
			imageIcon = new ImageIcon(loader.getResource(imageName)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageIcon;
	}

	public static Icon getIcon(String imageName) {
		return (Icon)getImageIcon(imageName);
	}

	public static Image getImage(String imageName) {
		return getImageIcon(imageName).getImage();
	}
}
