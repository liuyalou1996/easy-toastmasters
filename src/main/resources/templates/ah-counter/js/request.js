/**
 * 发送get请求
 * @param url
 * @param data
 * @param successCallback
 * @param failureCallback
 */
function get(url, data, successCallback, failureCallback) {
    axios.get(url, {params: data}).then(response => {
        if (response.status !== 200) {
            layer.error("Fail to send request!");
        }
    }).catch(error => layer.msg("Request failed!"));
}

/**
 * 发送post请求
 * @param url
 * @param data
 * @param callbackOnSuccess
 */
function post(url, data, callbackOnSuccess) {
    axios.post(url, data, {headers: {'content-type': 'application/json'}}).then(response => {
        if (response.status !== 200) {
            layer.error("Fail to send request!");
        }
        callbackOnSuccess(response.data);
    }).catch(error => layer.msg("Request failed!"));
}


function getAhWordsMapping(reportNo) {
    let url = `/ah-counter/report/ah-words-mapping/${reportNo}`;
    return axios.get(url, {params: null})
}

function getAhCounterReportOverview(reportNo, callback) {
    let url = `/ah-counter/report/overview/${reportNo}`;
    return axios.get(url, {params: null})
}

function getAhCounterReportDetail(reportNo, callback) {
    let url = `/ah-counter/report/detail/${reportNo}`;
    return axios.get(url, {params: null})
}
