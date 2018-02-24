$( document ).ready(function() {
	
	$("#grid-pequena").jqGrid({
        datatype: "local",
        height: 60 ,
        colNames:["Coluna1", "Coluna2"],
        colModel:[
            {name:"coluna_1", 	index:"coluna_1"},
            {name:"coluna_2", 	index:"coluna_2"}
        ]
    });
    
	$.blockUI.defaults.timeout = 0;
	$.blockUI.defaults.message = '<h1>Aguarde...</h1>';
	$.blockUI.defaults.fadeOut = 200; 
	$.blockUI.defaults.fadeIn = 5; 
	$.blockUI.defaults.css.border = '3px solid red';
	
	
	
	var x = [];
	var url = window.location;

	
	$("#hash_infinito").click(function(event){
		
		block();
		$.ajax({
            type : "GET",
            url : url + "/hash_infinito",
            success: function(data){
            	console.log(data);
            	unblock();
            }
        }); 
			
		
	});
	
	$("#grandes_dados_banco").click(function(event){
		
		block();
		$.ajax({
            type : "GET",
            url : url + "/grandes-dados-banco",
            success: function(data){
            	console.log(data);
            	unblock();
            }
        }); 
	});
	
	$("#pequenos_dados_banco").click(function(event){
		
		block();
		$.ajax({
            type : "GET",
            url : url + "/pequenos-dados-banco",
            success: function(data){
            	console.log(data);
            	unblock();
            }
        }); 
	});
	
	
	$("#thread_lixo").click(function(event){
		
		block();
		$.ajax({
            type : "GET",
            url : url + "/thread-lixo",
            success: function(data){
            	console.log(data);
            	unblock();
            }
        });
	});
	
	$("#thread_lixo_20x").click(function(event){
		
		block();
		$.ajax({
            type : "GET",
            url : url + "/thread-lixo-20x",
            success: function(data){
            	console.log(data);
            	unblock();
            }
        }); 
	});
	
	$("#botao_legal").click(function(event){
		
		block();
		$.ajax({
            type : "GET",
            url : url + "/greeting",
            success: function(data){
            	funcaoLegal();
            	unblock();
            }
        }); 
			
		
	});
	
	$("#botao_suspeito").click(function(event){
		
		block();
		$.ajax({
            type : "GET",
            url : url + "/greeting",
            success: function(data){
            	funcaoSuspeita();
            	unblock();
            }
        }); 
			
		
	});
	
	function unblock() {
		$.unblockUI();
	}
	
	function block() {
		$.blockUI();
	}
	
	function funcaoSuspeita() {
		var array = [];		
		var numeroGrande = 320000000;
		var aleatorio = "Texto aleatorio";
		
		array[numeroGrande] = aleatorio;
		console.log('Length array: ' + array.length)
		
		$.each( array, function( pos, valor ) {
			if(valor == aleatorio) {
				 $("#tbl-estorno_cartao").addRowData(undefined, valor, "last");
			}	
		});
	}
	
	function funcaoLegal() {
		var map = {};		
		var numeroGrande = 320000000;
		var aleatorio = "Texto aleatorio";
		
		map[numeroGrande] = aleatorio;
		console.log('Size map: ' + Object.keys(map).length)
		
		$.each( map, function( pos, valor ) {
			if(valor == aleatorio) {
				 $("#tbl-estorno_cartao").addRowData(undefined, valor, "last");
			}	
		});
	}
	
	$("#btnId").click(function(event){
        event.preventDefault();
        // Open Bootstrap Modal
        openModel();
        // get data from Server
        ajaxGet();
	})
	
    // Open Bootstrap Modal
    function openModel(){
    	$("#modalId").modal();
    }
    
    function fillData(data){
    	if(data!=null){
            $(".modal-body #greetingId").text(data);
    	}else{
            $(".modal-body #greetingId").text("Can Not Get Data from Server!");
    	}
    }
    
    
    
    
    
    function funcA() {
    	
    	console.log('Tempo inicial: ' + Date.now());
    	
    	funcB();
    	
    	console.log('Tempo final: ' + Date.now());
    }
    
    
    
})