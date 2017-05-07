/**
 * Created by lw on 2017/5/7.
 */

$().ready(function () {
    $.post('resultAction_test',null,function (data) {
        alert(data) ;
    })
})

