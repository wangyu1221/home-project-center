function getJob() {
    var jobId = $("#jobId").val()
    $.get("/job/get?jobId=" + jobId,
        function (result, status) {
            var data = result
            if (data.success) {
                renderJob(data.data)
            }
        })
}

function renderJob(job) {
    console.log("get: " + JSON.stringify(job))
    $("#name").val(job.name)
    $("#status").val(job.status)
    $("#platform").val(job.platform)
    $("#brand").val(job.brand)
    $("#subType").val(job.jobExtInfo.subType)
    $(job.jobExtInfo.mailTo).each(function (index, value) {
        $("#mailTo-" + value).prop("checked", true)
    })
    $(job.jobExtInfo.filters).each(function (index, value) {
        $("#filter-" + value).prop("checked", true)
    })
    $(job.jobExtInfo.outputs).each(function (index, value) {
        $("#output-" + value).prop("checked", true)
    })
    $("#forceQueryPrice").prop("checked", job.jobExtInfo.forceQueryPrice)
    $("#commitBidResult").prop("checked", job.jobExtInfo.commitBidResult)
    $("#oss").prop("checked", job.jobExtInfo.oss)
    $("#totalOutput").prop("checked", job.jobExtInfo.totalOutput)
    $("#format").val(job.jobExtInfo.format)
    $("#biddingName").val(job.jobExtInfo.biddingName)
    $("#biddingHelper").val(job.jobExtInfo.biddingHelper)
    $("#misc").val(JSON.stringify(job.jobExtInfo.misc, null, "    "))
}

$(function () {
    getJob()
    $("#btn-job-update").click(function (event) {
        var data = {
            id: $("#jobId").val(),
            status: $("#status").val(),
            name: $("#name").val(),
            platform: $("#platform").val(),
            brand: $("#brand").val(),
            subType: $("#subType").val(),
            mailTo: function () {
                var array = []
                $("input[name='mailTo']").each(function () {
                    if ($(this).prop("checked")) {
                        array.push(parseInt($(this).val()))
                    }
                })
                return array
            }(),
            filters: function () {
                var array = []
                $("input[name='filter']").each(function () {
                    if ($(this).prop("checked")) {
                        array.push($(this).val())
                    }
                })
                return array
            }(),
            outputs: function () {
                var array = []
                $("input[name='output']").each(function () {
                    if ($(this).prop("checked")) {
                        array.push($(this).val())
                    }
                })
                return array
            }(),
            forceQueryPrice: $("#forceQueryPrice").prop("checked"),
            commitBidResult: $("#commitBidResult").prop("checked"),
            oss: $("#oss").prop("checked"),
            totalOutput: $("#totalOutput").prop("checked"),
            format: $("#format").val(),
            biddingName: $("#biddingName").val(),
            biddingHelper: $("#biddingHelper").val(),
            misc: $("#misc").val()
        }
        console.log("request:" + JSON.stringify(data))
        $.ajax({
            url: "/job/update",
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
                    location.href = '/page/job/list'
                    // getJob()
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status)
            }
        })
    })
    $("#btn-job-back").click(function (event) {
        location.href = '/page/job/list'
    })
})