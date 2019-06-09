import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class ProgramaPrincipal {

	public static void main(String[] args) {		
		
		String arquivoFCL = "lib/RegrasFuzzy.fcl"; //Arquivo FCL
		FIS fis = FIS.load(arquivoFCL,true); 
		FunctionBlock fb = fis.getFunctionBlock(null);
		
		
		JFuzzyChart.get().chart(fis);
		
		fb.setVariable("temperatura",35);    //Escala: 0 � 50
		fb.setVariable("umidade", 4);		   //Escala: 0 � 4.2
		fb.setVariable("luminosidade",800);   //Escala: 0 � 1023        
        
        fis.evaluate(); //Utiliza��o da infer�ncia        

        Variable controladorIrrigacao = fb.getVariable("potenciaIrrigacao");
        JFuzzyChart.get().chart(controladorIrrigacao,controladorIrrigacao.getDefuzzifier(), true);

        double valor = fb.getVariable("potenciaIrrigacao").getValue();
        System.out.println("VALORES DE REFER�NCIA:\n");
        System.out.println("DESLIGADO:  0 � 10");
        System.out.println("FRACA....: 11 � 16");
        System.out.println("M�DIA....: 17 � 25");
        System.out.println("FORTE....: 26 � 30\n");
        System.out.println("---------------------------");
        System.out.printf("\nVALOR DE POT�NCIA: %.2f\n", fb.getVariable("potenciaIrrigacao").getValue());
        
        if(valor <= 10) {
        	
        	System.out.println("POT�NCIA DO IRRIGADOR: DESLIGADO");
        	
        }else if(valor <= 16){    
        	
        	System.out.println("POT�NCIA DO IRRIGADOR: FRACA");
        	
        }else if(valor <= 25){
        	
        	System.out.println("POT�NCIA DO IRRIGADOR: M�DIA");
        	
        }else if(valor > 25){
        	
        	System.out.println("POT�NCIA DO IRRIGADOR: FORTE");
        
        }
	}
}
