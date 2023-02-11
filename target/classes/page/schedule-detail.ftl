<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Schedule配置</title>
    <!-- Bootstrap -->
    <#include "bootstrap.ftl">
    <#if action = "add">
        <script src="/static/js/schedule-add.js"></script>
    </#if>
    <#if action = "update">
        <script src="/static/js/schedule-update.js"></script>
    </#if>
</head>
<body style="padding-top: 0px">
<#include "header.ftl">
<div class="container theme-showcase" role="main">
    <div class="col-md-13 form-horizontal">
        <div class="form-group">
            <h3>Schedule配置</h3>
        </div>
        <#if action = "update">
            <input type="hidden" id="scheduleId" value="${scheduleId}"/>
        </#if>
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">名称</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="name">
            </div>
        </div>
        <div class="form-group">
            <label for="status" class="col-sm-2 control-label">状态</label>
            <div class="col-sm-4">
                <select id="status" class="form-control" style="width: auto; display: inline;">
                    <option value="0">停用</option>
                    <option value="1">启用</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="executeType" class="col-sm-2 control-label">执行类型</label>
            <div class="col-sm-4">
                <select id="executeType" class="form-control" style="width: auto; display: inline;">
                    <option value="1">完整</option>
                    <option value="2">下载</option>
                    <option value="3">映射和报价</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="targetId" class="col-sm-2 control-label">targetId</label>
            <div class="col-sm-4">
                <input type="number" class="form-control" id="targetId">
            </div>
        </div>
        <div class="form-group">
            <label for="targetType" class="col-sm-2 control-label">targetType</label>
            <div class="col-sm-4">
                <select id="targetType" class="form-control" style="width: auto; display: inline;">
                    <option value="1">Job</option>
                    <option value="2">JobGroup</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="cron" class="col-sm-2 control-label">cron</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="cron">
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-4">
                <button id="btn-schedule-back" class="btn btn-default">取消</button>
                <#if action = "add">
                    <button id="btn-schedule-add" class="btn btn-primary">添加</button>
                </#if>
                <#if action = "update">
                    <button id="btn-schedule-update" class="btn btn-primary">修改</button>
                </#if>
            </div>
        </div>
    </div>
</div>
</body>
</html>