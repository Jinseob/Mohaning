function drawChart(items, prop, id){
	// prop.labelField : 'type_nm'
	// prop.dataField : 'score'
	// prop.borderColor : 'rgba(200, 0, 0, 0.1)'
	// prop.backgroundColor : 'rgba(200, 0, 0, 0.2)'
	// prop.label : '기사 특성'
	
	if(prop.labelField == undefined)prop.labelField = "type_nm";
//	if(prop.dataField == undefined)prop.dataField = "score";
	if(prop.dataField == undefined)prop.dataField = "ratio";
	if(prop.borderColor == undefined)prop.borderColor = "rgba(200, 0, 0, 0.1)";
	if(prop.backgroundColor == undefined)prop.backgroundColor = "rgba(200, 0, 0, 1)";
	if(prop.label == undefined)prop.label = "기사 특성";
	
	var ctx = document.getElementById(id).getContext('2d');
		
	var labels = new Array();
	var data = new Array();
	for(var i = 0; i < items.length; i++){
		var item = items[i];
		labels.push(item[prop.labelField]);
		data.push(item[prop.dataField]);
	}
	var myChart = new Chart(ctx, {
	    type: 'radar',
	    data: {
	        labels: labels,
	        datasets: [{
	        	label: prop.label,
	            data: data,
//	            borderColor : prop.borderColor,
	            borderColor : prop.borderColor,
	            backgroundColor: prop.backgroundColor,
	            pointRadius: 1,
	            pointStyle: 'dash'
	        }
	        ]
	    },
	    options: {
	    	scale:{
	    		ticks:{
//	    			beginAtZero: true,
	    			display: false,
	    			min :0,
 	    			max :100
	    		},
	    		gridLines: {
	    			display: false
	    		},
	    	},
	    	legend:{
	    		display: false
	    	},
	    	tooltips: {
	    		enabled: false
	    	}
	    }
	});
}