<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<div>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="importAll();">商品导入索引</a>
</div>
<div>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="deleteAll();">商品索引刪除</a>
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
    function deleteAll() {
        $.messager.confirm('确认','确定删除商品索引吗？',function(r) {
            if (r) {
                $.post("/index/deletsAll", null, function (data) {
                    if (data.status == 200) {
                        $.messager.alert('提示', '刪除索引成功!');
                    } else {
                        $.messager.alert('提示', '刪除索引失敗!');
                    }

                });
            }
        })
    }


</script>