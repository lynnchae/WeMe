require.config({
	paths : {
		echarts : './js'
	}
});
var list = JSON.parse(decodeURIComponent(window.location.search.split('=')[1]||0));
var len = list.length, i = 0, placeList = '',geoCoord='',province=len?list[0]["provinceName"].replace('市','').replace('省',''):'china';
if(len){
	for (; i < len; i++) {
		if(i==0){
			placeList +='[{"name":"热点'+i+'","value":'+i+'}';
			geoCoord +='{"热点'+i+'":['+new Number(list[i]["longitude"] / 10000000).toFixed(2)+','+ new Number(list[i]["latitude"] / 10000000).toFixed(2)+']';
		}else{
			placeList +=',{"name":"热点'+i+'","value":'+i+'}';
			geoCoord +=',"热点'+i+'":['+new Number(list[i]["longitude"] / 10000000).toFixed(2)+','+ new Number(list[i]["latitude"] / 10000000).toFixed(2)+']';
		}	
	}
	placeList = JSON.parse(placeList+']');
	geoCoord = JSON.parse(geoCoord+'}');
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
    series : [
        {
            name: '热点',
            type: 'map',
			roam: true,
            mapType: province,
            selectedMode : 'single',
            itemStyle:{         
				normal : {
					label:{show:true, textStyle: {fontSize: 8,color : '#FFFFFF'}},
					borderColor : 'rgba(100,149,237,1)',
					borderWidth : 0.5,
					areaStyle : {
						color : '#1b1b1b'
					 }
				},
                emphasis:{label:{show:true},color : '#1e90ff'}
            },
            data:[],
			markPoint:{
				symbol : 'emptyCircle',
					symbolSize : function (v) {
						return 10 + v / 100
					},
					effect : {
						show : true,
						shadowBlur : 0
					},
					itemStyle : {
						normal : {
							label : {
								show : false
							}
						}
					},
					data : placeList
				},
			geoCoord :geoCoord
		}
    ]
});
	});