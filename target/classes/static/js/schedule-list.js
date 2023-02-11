$(function () {
    reload()
})

function reload() {
    $.get("/schedule/list",
        function (result, status) {
            var data = result
            if (data.success) {
                renderList(data.data)
            }
        })
    $("#add").click(function (event) {
        location.href = '/page/schedule/add'
    })
}

function renderList(jobs) {
    var tbody = $("#tbody")
    $("#tbody").empty()
    $(jobs).each(function (index, element) {

        var id = $(element).attr("id")
        var name = $(element).attr("name")
        var updateTime = $(element).attr("updateTime")
        var status = $(element).attr("status")
        var executeType = $(element).attr("executeType")
        var targetId = $(element).attr("targetId")
        var targetType = $(element).attr("targetType")
        var cron = $(element).attr("cron")
        var time = $(element).attr("time")


        var tr = "<tr>"
        tr += "<td>" + id + "</td>"
        tr += "<td>" + name + "<div class='text-muted'>" + updateTime + "</div></td>"
        if (status == 1) {
            tr += "<td><span class='label label-success'>启用</span></td>"
        } else if (status == 0) {
            tr += "<td><span class='label label-default'>停用</span></td>"
        }

        if (executeType == 1) {
            tr += "<td>完整</td>"
        } else if (executeType == 2) {
            tr += "<td>下载</td>"
        } else if (executeType == 3) {
            tr += "<td>映射和报价</td>"
        } else if (executeType == 0) {
            tr += "<td>" + executeType + "</td>"
        }

        tr += "<td>" + targetId + "</td>"

        if (targetType == 1) {
            tr += "<td>Job</td>"
        } else if (targetType == 2) {
            tr += "<td>JobGroup</td>"
        }
        tr += "<td>" + cron + "<div class='text-muted'> " + time +  "</div></td>"

        var updateBtn = "<button type='button' action='update' data-job-id='" + id + "' class='btn btn-primary' >修改</button> "
        // var executeBtn = "<button type='button' action='execute' data-job-id='" + id + "' class='btn btn-primary' >执行</button> "

        tr += "<td>" + updateBtn + "</td>"
        tr += "</tr>"
        tbody.append(tr)
    })

    $("button[action=update]").click(function (event) {
        var target = $(event.target)
        var id = target.data("job-id")
        location.href = '/page/schedule/update?id=' + id
    })

}