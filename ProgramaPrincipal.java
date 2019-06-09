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
		
		fb.setVariable("temperatura",35);    //Escala: 0 à 50
		fb.setVariable("umidade", 4);		   //Escala: 0 à 4.2
		fb.setVariable("luminosidade",800);   //Escala: 0 à 1023        
        
        fis.evaluate(); //Utilização da inferência        

        Variable controladorIrrigacao = fb.getVariable("potenciaIrrigacao");
        JFuzzyChart.get().chart(controladorIrrigacao,controladorIrrigacao.getDefuzzifier(), true);

        double valor = fb.getVariable("potenciaIrrigacao").getValue();
        System.out.println("VALORES DE REFERÊNCIA:\n");
        System.out.println("DESLIGADO:  0 À 10");
        System.out.println("FRACA....: 11 À 16");
        System.out.println("MÉDIA....: 17 À 25");
        System.out.println("FORTE....: 26 À 30\n");
        System.out.println("---------------------------");
        System.out.printf("\nVALOR DE POTÊNCIA: %.2f\n", fb.getVariable("potenciaIrrigacao").getValue());
        
        if(valor <= 10) {
        	
        	System.out.println("POTÊNCIA DO IRRIGADOR: DESLIGADO");
        	
        }else if(valor <= 16){    
        	
        	System.out.println("POTÊNCIA DO IRRIGADOR: FRACA");
        	
        }else if(valor <= 25){
        	
        	System.out.println("POTÊNCIA DO IRRIGADOR: MÉDIA");
        	
        }else if(valor > 25){
        	
        	System.out.println("POTÊNCIA DO IRRIGADOR: FORTE");
        
        }
	}
}
