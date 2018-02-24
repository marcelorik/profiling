package com.profiling.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.profiling.model.DadosGrandes;
import com.profiling.repository.DadoRepository;
  
@Controller
public class WebController {
	
	@Autowired
	private DadoRepository rep;
	
    @RequestMapping(value="/",method = RequestMethod.GET)
    public String home(){
        return "index";
    }
    
    @ResponseBody
    @RequestMapping(value="/greeting",method = RequestMethod.GET)
    public String Greeting(){
        return "Message From SpringBoot Service - Hello World!";
    }
    
    @ResponseBody
    @RequestMapping(value="/hash_infinito",method = RequestMethod.GET)
    public String hashInfinito(){
    	
    	Map m = new HashMap();
		while (true)
			for (int i = 0; i < 10; i++)
				if (!m.containsKey(i)) {
					m.put(new Key(i), "Number:" + i);
					System.out.println( "Number:" + i);
				}
    }
    
    @ResponseBody
    @RequestMapping(value="/leak-class",method = RequestMethod.GET)
    public void leakClass(){
    	
    	try {
    		MemoryLeak ml = new MemoryLeak();
    		for(int i = 0; i < 100; i++) {
    			for(int k = 0; k < 100; k++) {
        			
    				ml.leakMemory();
    				ml.curValue++;
        			
        		}
    				
    			Thread.currentThread().sleep(2000); // 2 segundos
    		}
    		
    	} catch (Exception e) {
			System.out.println("Exception e " + e);
		}

    }
    
    @ResponseBody
    @RequestMapping(value="/thread-lixo",method = RequestMethod.GET)
    public void threadLixo(){
    	
    	for (int i = 0; i < 40; i++) {
    		new MapLeaker().createTasks();
    	}
         
    }
    
    @ResponseBody
    @RequestMapping(value="/thread-lixo-20x",method = RequestMethod.GET)
    public void threadLixo20x(){
    	
    	for (int i = 0; i < 80; i++) {
    		new MapLeaker().createTasks();
    	}
         
    }
    
    @ResponseBody
    @RequestMapping(value="/grandes-dados-banco",method = RequestMethod.GET)
    public List<DadosGrandes> grandesDados(){

    	List<DadosGrandes> findAll = null;
    	
    	for(int i = 0; i <= 40; i++) {
    		findAll = rep.findAll();
    	}
    	return findAll;
    }
    
    @ResponseBody
    @RequestMapping(value="/pequenos-dados-banco",method = RequestMethod.GET)
    public DadosGrandes pequenosDados(){

    	DadosGrandes findAll = null;
    	
    	for(int i = 0; i <= 40; i++) {
    		findAll = rep.findOne(10L);
    	}
    	
    	return findAll;
    }  
    
    class Key {
		Integer id;
		static final String texto = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer maximus mauris quam. Pellentesque cursus, ipsum dictum gravida volutpat, ante lectus efficitur tellus, quis scelerisque lacus dui ac magna. Vestibulum posuere turpis at imperdiet luctus. Sed euismod ante vel libero tempor volutpat. Quisque viverra feugiat libero, ut accumsan augue porta sit amet. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec sit amet aliquam quam, ut bibendum nisl. Cras metus ex, cursus ac cursus eget, pellentesque vitae tortor. Fusce tincidunt leo at bibendum ultricies. Vestibulum maximus velit urna, eu elementum tellus dignissim in. Suspendisse sem eros, ornare fringilla lorem non, finibus malesuada sem. Donec vel arcu cursus, placerat justo in, vestibulum ipsum. In sed volutpat diam, sed congue purus. In hac habitasse platea dictumst.";

		Key(Integer id) {
			this.id = id;
		}

		@Override
		public int hashCode() {
			return id.hashCode();
		}
	}
    
    
    
    
    
    
    class MemoryLeak {
    	HashMap hm = new HashMap();
    	long curValue;
    	
    	MemoryLeak() {
    		curValue = 0;
    	}
    	
    	void setReferencia (ClassGenerica cg) {
        	hm.put(curValue, cg);
        }
    	
    	void leakMemory() {
    		
    		ClassGenerica cg = new ClassGenerica();
    		setReferencia(cg);
    	}
    }
    
    
    class ClassGenerica {
    	
    	int a;
    	int b;
    	String s1;
    	String s2;
    	
    	ClassGenerica () {
    		a = 0;
    		b = 4;
    		s1 = "s1";
    		s2 = "s2";
    	}
    	
    }
    
}