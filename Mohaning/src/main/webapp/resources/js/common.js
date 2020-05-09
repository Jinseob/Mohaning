function drawChart(items, prop, id){
	// prop.labelField : 'type_nm'
	// prop.dataField : 'score'
	// prop.borderColor : 'rgba(200, 0, 0, 0.1)'
	// prop.backgroundColor : 'rgba(200, 0, 0, 0.2)'
	// prop.label : '기사 특성'
	
	var color = "200, 0, 0";
	
	if(prop.labelField == undefined)prop.labelField = "type_nm";
//	if(prop.dataField == undefined)prop.dataField = "score";
	if(prop.dataField == undefined)prop.dataField = "ratio";
	if(prop.borderColor == undefined)prop.borderColor = "rgba(" + color + ", 0.1)";
	if(prop.backgroundColor == undefined)prop.backgroundColor = "rgba(" + color + ", 1)";
	if(prop.label == undefined)prop.label = "기사 특성";
	
	var ctx = document.getElementById(id).getContext('2d');
		
	var labels = new Array();
	var data = new Array();
	var maxScore = 0;
	for(var i = 0; i < items.length; i++){
		var item = items[i];
		labels.push(item[prop.labelField]);
		data.push(item[prop.dataField]);
		if(maxScore < item[prop.dataField]){
			maxScore = item[prop.dataField];
		}
	}
	
	// 특성의 색깔 혼합 하는 부분.
	if(maxScore > 0){
		var colors = new Array();
		var r, g, b;
		var cnt = 0;
		for(var i = 0; i < items.length; i++){
			var item = items[i];
			if(maxScore == item[prop.dataField]){
				r = Number(item["color"].split(",")[0]);
				g = Number(item["color"].split(",")[1]);
				b = Number(item["color"].split(",")[2]);
				cnt++;
			}
		}
		color = parseInt(r / cnt) + "," + parseInt(g / cnt) + "," + parseInt(b / cnt);
		prop.borderColor = "rgba(" + color + ", 0.1)";
		prop.backgroundColor = "rgba(" + color + ", 1)";
	}
	// 특성의 색깔 혼합 하는 부분. 여기까지.
	
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