<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Job列表</title>

    <!-- Bootstrap -->
    <#include "bootstrap.ftl">
    <script language="JavaScript" src="/static/js/job-list.js"></script>
</head>
<body style="padding-top: 0px">
<div class="container theme-showcase" role="main">
    <#include "header.ftl">
    <div class="" style="margin-bottom: 20px;text-align:  right;">
        <button id="add" type='button' disabled="" class='btn btn-default' style='display: inline-block'>添加Job</button>
    </div>
    <div class="col-md-13">
        <table class="table table-striped">
            <thead id="thead">
            <tr>
                <th>id</th>
                <th>名称</th>
                <th>状态</th>
                <th>平台</th>
                <th>品牌</th>
                <th>配置</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="tbody"></tbody>
        </table>
    </div>
</div>


</body>
</html>