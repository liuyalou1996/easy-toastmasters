/**
 * 发送get请求
 * @param url
 * @param data
 * @param callbackOnSuccess
 * @param callbackOnFailure
 */
function get(url, data, callbackOnSuccess, callbackOnFailure) {
    axios.get(url, {params: data}).then(response => {
        if (response.status !== 200) {
            layer.error("Fail to send request!");
        }
        callbackOnSuccess(response.data);
    }).then(callbackOnFailure);
}

/**
 * 发送post请求
 * @param url
 * @param data
 * @param callbackOnSuccess
 * @param callbackOnFailure
 */
function post(url, data, callbackOnSuccess, callbackOnFailure) {
    axios.post(url, data, {headers: {'content-type': 'application/json'}}).then(response => {
        if (response.status !== 200) {
            layer.error("Fail to send request!");
        }
        callbackOnSuccess(response.data);
    }).then(callbackOnFailure);
}
