/**
 * 初始化可点击的图表
 * @param chart chart对象
 * @param title 标题对象
 * @param dataAxis 横坐标label
 * @param data 横坐标数据
 * @param yMax 纵坐标最大值
 */
function initClickableBarChart(chart, title, dataAxis, data, yMax) {
    let dataShadow = [];
    for (let i = 0; i < data.length; i++) {
        dataShadow.push(yMax);
    }
    const options = {
        title: title,
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        xAxis: {
            data: dataAxis,
            axisLabel: {
                inside: false,
                color: '#fff'
            },
            axisTick: {
                show: false
            },
            axisLine: {
                show: false
            },
            z: 10
        },
        yAxis: {
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                color: '#999'
            }
        },
        dataZoom: [
            {
                type: 'inside'
            }
        ],
        series: [
            {
                type: 'bar',
                showBackground: true,
                label: {
                    show: true,
                    position: 'top',
                    color: '#e7dbdb'
                },
                itemStyle: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                        {offset: 0, color: '#83bff6'},
                        {offset: 0.5, color: '#188df0'},
                        {offset: 1, color: '#188df0'}
                    ])
                },
                emphasis: {
                    itemStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                            {offset: 0, color: '#2378f7'},
                            {offset: 0.7, color: '#2378f7'},
                            {offset: 1, color: '#83bff6'}
                        ])
                    }
                },
                data: data,
            }
        ]
    };

    const zoomSize = 6;
    chart.on('click', function (params) {
        chart.dispatchAction({
            type: 'dataZoom',
            startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
            endValue:
                dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
        });
    });
    chart.setOption(options);
}

/**
 * 初始化饼状图
 * @param chart Echart对象
 * @param title 标题信息
 * @param data 实际数据格式
 */
function initPieChart(chart, title, data) {
    const option = {
        title: title,
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'right'
        },
        series: [
            {
                name: '哼哈词使用排名前3',
                type: 'pie',
                radius: '50%',
                data: data,
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                },
                label: {
                    show: true,
                    formatter: '{b} : {c} 次'
                },
            }
        ]
    };
    chart.setOption(option);
}