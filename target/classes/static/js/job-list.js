$(function () {
    reload()
})

function reload() {
    $.get("/job/list",
        function (result, status) {
            var data = result
            if (data.success) {
                renderList(data.data)
            }
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
        var platform = $(element).attr("platform")
        var brand = $(element).attr("brand")
        var jobExtInfo = element.jobExtInfo

        var tr = "<tr>"
        tr += "<td>" + id + "</td>"
        tr += "<td>" + name + "<div class='text-muted'>" + updateTime + "</div></td>"
        if (status == 1) {
            tr += "<td><span class='label label-success'>启用</span></td>"
        } else if (status == 0) {
            tr += "<td><span class='label label-default'>停用</span></td>"
        }
        tr += "<td>" + platform + "</td>"
        tr += "<td>" + brand + "</td>"

        tr += "<td><textarea style='font-family: Courier New' class='form-control' readonly>" + JSON.stringify(jobExtInfo, null, "    ") + "</textarea></td>"


        var updateBtn = "<button type='button' action='update' data-job-id='" + id + "' class='btn btn-primary' >修改</button> "
        var executeBtn = "<button type='button' action='execute' data-job-id='" + id + "' class='btn btn-primary' >执行</button> "

        tr += "<td>" + updateBtn + executeBtn + "</td>"
        tr += "</tr>"
        tbody.append(tr)
    })

    $("button[action=update]").click(function (event) {
        var target = $(event.target)
        var id = target.data("job-id")
        location.href = '/page/job/update?id=' + id
    })

    $("button[action=execute]").click(function (event) {
        confirm("跳转到swagger进行操作")
        location.href = '/swagger-ui.html#/job-controller/startUsingPOST'
    })
}