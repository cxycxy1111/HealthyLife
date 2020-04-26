var domain = "https://openreading.natapp4.cc/";

/**
 * 网络请求
 * @param method
 * @param url
 * @param param
 * @param successArr
 * @param successDic
 * @param error
 */
function httpRequest(url, param, successArr, successDic, error) {
    $.ajax({
        type: 'post',
        data: param,
        url: url,
        success: function (result) {
            console.log(result);
            var data = JSON.parse(result);
            if (result.startsWith("{", 0)) {
                successDic(data);
            } else if (result.startsWith("[", 0)) {
                successArr(data);
            } else {
                error(result);
            }
        },
        error: function (e) {
        }
    })
}