<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<div>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="importAll();">商品导入索引</a>
</div>
<script>
    function importAll() {
        $.post("/index/importAll",null,function (data) {
            if(data.status == 200){
                $.messager.alert('提示','导入索引成功!');
            }else {
                $.messager.alert('提示','导入索引失敗!');
            }
        });
    }
</script>