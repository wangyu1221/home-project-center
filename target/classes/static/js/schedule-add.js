$(function () {
    $("#btn-schedule-add").click(function (event) {
        var data = {
            name: $("#name").val(),
            status: $("#status").val(),
            executeType: $("#executeType").val(),
            targetId: $("#targetId").val(),
            targetType: $("#targetType").val(),
            cron: $("#cron").val()
        }
        console.log("request:" + JSON.stringify(data))
        $.ajax({
            url: "/schedule/add",
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            dataType: 'JSON',
            data: JSON.stringify(data),
            success: function (result, status) {
                if (result["success"] == false) {
                    alert(result["msg"])
                } else {
                    alert("添加成功")
                    // var processorId = $("#processorId").val()
                    location.href = '/page/schedule/list'
                    // getJob()
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status)
            }
        })
    })
    $("#btn-schedule-back").click(function (event) {
        location.href = '/page/schedule/list'
    })
})