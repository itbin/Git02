<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/8 0008
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
    <head>
        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>修改用户</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $.get("city/findAll",{},function (data) {
                for(var i=0;i<data.length;i++){
                   var address= document.getElementsByName("address");
                <%--<option value="广东" ${user.address == '北京' ? "selected": ""}>北京</option>--%>

                    $("#address").append("<option value='"+data[i].address+"'"+address==data[i].address?"selected":""+"'>"+data[i].address+"</option>")
                }
            });

        });
        $(function () {
            $.ajax({
                type:'post',//请求方式
                url:'${pageContext.request.contextPath}/city/findAll',//访问的URL
                success:function (data) {
                    for (var i=0;i<data.length;i++){
                        $("#address").append("<option value='"+data[i].address+"'"++">"+data[i].address+"</option>")
                    }
                }
            })
        })
    </script>


    </head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="${pageContext.request.contextPath}/user/update" method="post" id="updateForm">
        <input type="hidden" name="id" value="${user.id}">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name"  value="${user.name}" readonly="readonly" placeholder="请输入姓名" />
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="gender" value="男" ${user.gender == '男' ? "checked" : ""}  />男
            <input type="radio" name="gender" value="女"  ${user.gender == '女' ? "checked" : ""}/>女
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age"  name="age" value="${user.age}" placeholder="请输入年龄" />
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" id="address" class="form-control" >

            </select>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" id="qq" name="qq"  value="${user.qq}" placeholder="请输入QQ号码"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" name="email" id="email" value="${user.email}" placeholder="请输入邮箱地址"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>