function getSchedule() {
    var scheduleId = $("#scheduleId").val()
    $.get("/schedule/get?scheduleId=" + scheduleId,
        function (result, status) {
            var data = result
            if (data.success) {
                renderSchedule(data.data)
            }
        })
}

function renderSchedule(schedule) {
    console.log("get: " + JSON.stringify(schedule))
    $("#name").val(schedule.name)
    $("#status").val(schedule.status)
    $("#executeType").val(schedule.executeType)
    $("#targetId").val(schedule.targetId)
    $("#targetType").val(schedule.targetType)
    $("#cron").val(schedule.cron)
}

$(function () {
    getSchedule()
    $("#btn-schedule-update").click(function (event) {
        var data = {
            id: $("#scheduleId").val(),
            name: $("#name").val(),
            status: $("#status").val(),
            executeType: $("#executeType").val(),
            targetId: $("#targetId").val(),
            targetType: $("#targetType").val(),
            cron: $("#cron").val()
        }
        console.log("request:" + JSON.stringify(data))
        $.ajax({
            url: "/schedule/update",
            type: 'POST',
            contentType: 'application/json;charset=utf-8',
            dataType: 'JSON',
            data: JSON.stringify(data),
            success: function (result, status) {
                if (result["success"] == false) {
                    alert(result["msg"])
                } else {
                    alert("修改成功")
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