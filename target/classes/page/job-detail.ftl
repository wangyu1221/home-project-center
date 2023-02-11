<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>job配置</title>
    <!-- Bootstrap -->
    <#include "bootstrap.ftl">
    <script src="/static/js/job-update.js"></script>
</head>
<body style="padding-top: 0px">
<#include "header.ftl">
<div class="container theme-showcase" role="main">
    <div class="col-md-13 form-horizontal">
        <div class="form-group">
            <h3>job配置修改</h3>
        </div>

        <input type="hidden" id="jobId" value="${jobId}"/>
        <div class="form-group">
            <h4>基础属性</h4>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">任务名</label>
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
            <label for="platform" class="col-sm-2 control-label">数据平台</label>
            <div class="col-sm-4">
                <select id="platform" class="form-control" style="width: auto; display: inline;">
                    <#list platforms as platform>
                        <option value="${platform.id}">${platform.name}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="brand" class="col-sm-2 control-label">任务品牌</label>
            <div class="col-sm-4">
                <select id="brand" class="form-control" style="width: auto; display: inline;">
                    <#list brands as brand>
                        <option value="${brand.brand}">${brand.name}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="form-group">
            <h4>ex_info</h4>
        </div>
        <div class="form-group">
            <label for="subType" class="col-sm-2 control-label">subType</label>
            <div class="col-sm-4">
                <input type="number" class="form-control" id="subType">
            </div>
        </div>
        <div class="form-group">
            <label for="mailTo" class="col-sm-2 control-label">mailTo</label>
            <div class="col-sm-4 checkbox">
                <#list mailTos as mailTo>
                    <label><input name="mailTo" type="checkbox" id="mailTo-${mailTo.id}" value="${mailTo.id}">${mailTo.mail}</label><br/>
                </#list>
            </div>
        </div>
        <div class="form-group">
            <label for="filters" class="col-sm-2 control-label">过滤策略</label>
            <div class="col-sm-4 checkbox">
                <#list filters as filter>
                    <label><input name="filter" type="checkbox" id="filter-${filter}" value="${filter}">${filter}</label>
                </#list>
            </div>
        </div>
        <div class="form-group">
            <label for="biddingHelper" class="col-sm-2 control-label">出价策略</label>
            <div class="col-sm-4">
<#--                <input type="text" class="form-control" id="biddingHelper" placeholder="留空使用默认出价策略">-->
                <select id="biddingHelper" class="form-control" style="width: auto; display: inline;">
                    <option value="">平台默认</option>
                    <#list biddingHelpers as biddingHelper>
                        <option value="${biddingHelper}">${biddingHelper}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="outputs" class="col-sm-2 control-label">输出策略</label>
            <div class="col-sm-4 checkbox">
                <#list outputs as output>
                    <label><input name="output" type="checkbox" id="output-${output}" value="${output}">${output}</label>
                </#list>
            </div>
        </div>
        <div class="form-group">
            <label for="forceQueryPrice" class="col-sm-2 control-label">强制重查拍机堂价格</label>
            <div class="col-sm-4 checkbox">
                <label><input type="checkbox" id="forceQueryPrice">forceQueryPrice</label><br/>
            </div>
        </div>
        <div class="form-group">
            <label for="commitBidResult" class="col-sm-2 control-label">提交报价</label>
            <div class="col-sm-4 checkbox">
                <label><input type="checkbox" id="commitBidResult">commitBidResult</label><br/>
            </div>
        </div>
        <div class="form-group">
            <label for="oss" class="col-sm-2 control-label">文件存到oss</label>
            <div class="col-sm-4 checkbox">
                <label><input type="checkbox" id="oss">oss</label>
            </div>
        </div>
        <div class="form-group">
            <label for="totalOutput" class="col-sm-2 control-label">合并到原始文件</label>
            <div class="col-sm-4 checkbox">
                <label><input type="checkbox" id="totalOutput">totalOutput</label><br/>
            </div>
        </div>
        <div class="form-group">
            <label for="format" class="col-sm-2 control-label">文件格式</label>
            <div class="col-sm-4">
                <select id="format" class="form-control" style="width: auto; display: inline;">
                    <#list formats as format>
                        <option value="${format.format}">${format.format}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="biddingName" class="col-sm-2 control-label">场次名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="biddingName" placeholder="匹配第三方拍卖场次名称">
            </div>
        </div>

        <div class="form-group">
            <label for="misc" class="col-sm-2 control-label">自定义配置项</label>
            <div class="col-sm-4">
                <textarea type="text" style='font-family: Courier New' class="form-control" id="misc" rows="10" placeholder="{}"></textarea>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-4">
                <button id="btn-job-back" class="btn btn-default">取消</button>
                <button id="btn-job-update" class="btn btn-primary">修改</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>