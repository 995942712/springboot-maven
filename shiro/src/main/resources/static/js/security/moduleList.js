layui.use(['layer', 'form', 'table'], function () {
    var layer = layui.layer,
        $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        ztree,
        addIndex,
        editIndex;

    ztree = function (data) {
        var setting = {
            view: {
                fontCss: {"font-family": "微软雅黑", "font-size": "16px"}
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pid",
                    rootPId: null
                }
            },
            callback: {
                onClick: function (event, treeId, treeNode) {
                }
            }
        };
        zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, data);
    };

    $(function () {
        $.post("/module/tree", function (res) {
            if (res.success) {
                ztree(res.data);
            } else {
                layer.msg(res.message);
            }
        });
    });

    //批量删除
    var active = {
        addUser: function () {
            var addIndex = layer.open({
                title: "添加会员",
                type: 2,
                content: "/user/add",
                success: function (layero, addIndex) {
                    setTimeout(function () {
                        layer.tips('点击此处返回会员列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500);
                }
            });
            //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
            $(window).resize(function () {
                layer.full(addIndex);
            });
            layer.full(addIndex);
        },
        deleteSome: function () {
            var checkStatus = table.checkStatus('test'),
                data = checkStatus.data;
            if (data.length > 0) {
                console.log(JSON.stringify(data));
                for (var i = 0; i < data.length; i++) {
                    var d = data[i];
                    if (d.id == 1) {
                        layer.msg("不能删除超级管理员");
                        return false;
                    }
                }
                layer.confirm("你确定要删除这些菜单么？", {btn: ['是的,我确定', '我再想想']},
                    function () {
                        var deleteindex = layer.msg('删除中，请稍候', {icon: 16, time: false, shade: 0.8});
                        $.ajax({
                            type: "POST",
                            url: "/user/deleteSome",
                            dataType: "json",
                            contentType: "application/json",
                            data: JSON.stringify(data),
                            success: function (res) {
                                layer.close(deleteindex);
                                if (res.success) {
                                    layer.msg("删除成功", {time: 1000}, function () {
                                        location.reload();
                                    });
                                } else {
                                    layer.msg(res.message);
                                }
                            }
                        });
                    }
                )
            } else {
                layer.msg("请选择需要删除的菜单");
            }
        },
        selectUser: function () {
            var searchContent = $("#searchContent").val();
            t.where = {"s_key": searchContent};
            table.reload('test', t);
        }
    };

    $('.layui-inline .layui-btn').on('click', function () {
        var type = $(this).data('type');
        var style = $(this).attr("class");
        if (style.indexOf("disabled") >= 0) {
            layer.msg("按钮被禁用了");
            $(this).removeClass("layui-btn-disabled");
        } else {
            active[type] ? active[type].call(this) : '';
        }
    });

});
//========================tree=============================
layui.use(['tree', 'layer', 'table'], function () {
    var layer = layui.layer, $ = layui.jquery;
    var layout = [{
        name: '菜单名称',
        treeNodes: true,
        headerClass: 'value_col'
    }, {
        name: '链接地址',
        headerClass: 'value_col',
        colClass: 'value_col',
        style: 'width: 15%',
        render: function (row) {
            return undefined === row.href ? "" : row.href;
        }
    }, {
        name: '图标',
        headerClass: 'value_col',
        colClass: 'value_col',
        style: 'width: 5%;text-align: center;',
        render: function (row) {
            return undefined === row.icon ? "" : '<i class="layui-icon" style="font-size: 30px;">' + row.icon + '</i>';
        }
    }, {
        name: '排序',
        headerClass: 'value_col',
        colClass: 'value_col',
        style: 'width: 5%;text-align: center;',
        render: function (row) {
            return undefined === row.sort ? "" : row.sort;
        }
    }, {
        name: '创建时间',
        headerClass: 'value_col',
        colClass: 'value_col',
        style: 'width: 10%',
        render: function (row) {
            return undefined === row.createDate ? "" : new Date(row.createDate).Format("yyyy-MM-dd hh:mm:ss");
        }
    }, {
        name: '操作',
        headerClass: 'value_col',
        colClass: 'value_col',
        style: 'width: 30%;text-align: center;',
        render: function (row) {
            return '<a class="layui-btn layui-btn-normal layui-btn-sm" onclick="addChildMenu(' + row.id + ')"><i class="layui-icon">&#xe654;</i>添加子菜单</a>' +
                '<a class="layui-btn layui-btn-normal layui-btn-sm" onclick="editChildMenu(' + row.id + ')"><i class="layui-icon">&#xe642;</i>编辑菜单</a>' +
                '<a class="layui-btn layui-btn-danger layui-btn-sm" onclick="delMenu(' + row.id + ')"><i class="layui-icon">&#xe640;</i>删除</a>';
        }
    }];

    var setTree = function (data, layout) {
        $("#demo").empty();
        layui.treeGird({
            elem: '#demo',//传入元素选择器
            nodes: data,
            layout: layout
        });
    };

    $(function () {
        $.post("/module/treelist", function (res) {
            if (res.success) {
                setTree(res.data, layout);
            } else {
                layer.msg(res.message);
            }
        });
    });

    var active = {
        addUser: function () {
            var addIndex = layer.open({
                title: "添加系统菜单",
                type: 2,
                content: "/module/add",
                success: function (layero, addIndex) {
                    setTimeout(function () {
                        layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500);
                }
            });
            //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
            $(window).resize(function () {
                layer.full(addIndex);
            });
            layer.full(addIndex);
        }
    };

    $('.layui-inline .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});

var addChildMenu = function (data) {
    var addIndex = layer.open({
        title: "添加系统菜单",
        type: 2,
        content: "/module/add?parentId=" + data,
        success: function (layero, addIndex) {
            setTimeout(function () {
                layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {tips: 3});
            }, 500);
        }
    });
    //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
    $(window).resize(function () {
        layer.full(addIndex);
    });
    layer.full(addIndex);
};

var editChildMenu = function (data) {
    var editIndex = layer.open({
        title: "编辑菜单",
        type: 2,
        content: "/module/edit?id=" + data,
        success: function (layero, index) {
            setTimeout(function () {
                layer.tips('点击此处返回会员列表', '.layui-layer-setwin .layui-layer-close', {tips: 3});
            }, 500);
        }
    });
    //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
    $(window).resize(function () {
        layer.full(editIndex);
    });
    layer.full(editIndex);
};

var delMenu = function (data) {
    layer.confirm("你确定要删除该菜单么？这将会使得其下的所有子菜单都删除", {btn: ['是的,我确定', '我再想想']}, function () {
        $.post("/module/delete", {"id": data}, function (res) {
            if (res.success) {
                layer.msg("删除成功", {time: 1000}, function () {
                    location.reload();
                });
            } else {
                layer.msg(res.message);
            }
        });
    });
};
//格式化时间
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,//月份
        "d+": this.getDate(),//日
        "h+": this.getHours(),//小时
        "m+": this.getMinutes(),//分
        "s+": this.getSeconds(),//秒
        "q+": Math.floor((this.getMonth() + 3) / 3),//季度
        "S": this.getMilliseconds()//毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};