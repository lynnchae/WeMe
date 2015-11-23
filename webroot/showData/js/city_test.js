require.config({
	paths : {
		echarts : './js'
	}
});
var list = JSON.parse(decodeURIComponent(window.location.search.split('=')[1]||0));
var  placeList =[],geoCoord='',count = [],emptyCircle=[],dataLen = list.length,i = 0, j=0;
if(dataLen){	
	 for (;i < dataLen;i++) {
		var cityValue=  list[i].split(',');
		var homeCity = list[0].split(',')[0] , cityName = cityValue[0],cityCount = cityValue[1];
		for(var j=i;j<cityList.length;j++){
			if(cityName==cityList[j].cityName){
				var longitude = new Number(cityList[j].longitude).toFixed(4),latitude =  new Number(cityList[j].latitude).toFixed(4);
				count.push([{"name":homeCity},{"name":cityName,"value":cityCount}]);
				if(homeCity!=cityName){
					placeList.push([{"name":homeCity},{"name":cityName}]);
					emptyCircle.push({"name":cityName,"value":cityCount});
				}			
				if(i==0){				
					geoCoord+='{"'+cityName+'":['+longitude+','+latitude+']';
				}else{
					geoCoord+=',"'+cityName+'":['+longitude+','+latitude+']';
				}
			}	
		}
	} 
}else{
	geoCoord+='{';
}
require(
	[
		'echarts',
		'echarts/chart/map'
	],
	function (ec) {
	var myChart2 = ec.init(document.getElementById('mainMap'));
	myChart2.setOption({
    backgroundColor: '#1b1b1b',
    color: ['gold','aqua','lime'],
    title : {
		show : false,
        text: '去过的城市',
        subtext:'来自微密WEME',
        x:'center',        
    },
    tooltip : {
		show : false,
        trigger: 'item',
        formatter: '{b}'
    },
    toolbox: {
        show : false,
        orient : 'vertical',
        x: 'right',
        y: 'center',
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    dataRange: {
		show : false,
        min : 0,
        max : 100,
        calculable : true,
        color: ['#ff3333', 'orange', 'yellow','lime','aqua'],
        textStyle:{
            color:'#fff'
        }
    },
    series : [
        {
            name: '全国',
            type: 'map',
            roam: true,
            hoverable: false,
            mapType: 'china',
            itemStyle:{
                normal:{
                    borderColor:'rgba(100,149,237,1)',
                    borderWidth:0.5,
                    areaStyle:{
                        color: '#1b1b1b'
                    }
                }
            },
            data:[],
            markLine : {
                smooth:true,
                symbol: ['none', 'circle'],  
                symbolSize : 1,
                itemStyle : {
                    normal: {
                        color:'#fff',
                        borderWidth:1,
                        borderColor:'rgba(30,144,255,0.5)'
                    }
                },
                data : placeList
            },
            geoCoord: JSON.parse(geoCoord+'}')
        },
        {
            name: 'city Top10',
            type: 'map',
            mapType: 'china',
            data:[],
            markLine : {
                smooth:true,
                effect : {
                    show: true,
                    scaleSize: 1,
                    period: 30,
                    color: '#fff',
                    shadowBlur: 10
                },
                itemStyle : {
                    normal: {
                        borderWidth:1,
                        lineStyle: {
                            type: 'solid',
                            shadowBlur: 10
                        }
                    }
                },
                data : count
            },
            markPoint : {
                symbol:'emptyCircle',
                symbolSize : function (v){
                    return 10 + v/10
                },
                effect : {
                    show: true,
                    shadowBlur : 0
                },
                itemStyle:{
                    normal:{
                        label:{show:false}
                    },
                    emphasis: {
                        label:{position:'top'}
                    }
                },
                data : emptyCircle
            }
        }
    ]
});
});