<html xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"
      xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"
      xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <title>freemarker测试</title>
    <meta charset="UTF-8">
</head>
<p>
    这是测试模板6
    msg:${msg}</br>
    Tbitem的title数据：${tbitem.title}</br>
    <#--for(TbItem集合进行遍历)-->
    <#list items as item>
       这是下标${item_index}--> ${item.title}</br>
    </#list>
    <#--这是取出map中的数据-->
    <#list itemMap?keys as key >
        map集合下标${key}--->${itemMap[key].title}</br>
    </#list>
    <#--if-- else--展不展示-->
    <#list items as item >
        <#if item_index%2=0>
            偶数：下标为偶数输出${item_index}--》${item.title}</br>
        <#else>
            奇数下标：${item_index}</br>
        </#if>
    </#list>
    <#--显示绑定的时间-->
    --日期：${date?date}</br>
    --时间:${date?time}</br>
    --日期时间:${date?datetime}</br>
    --上面是默认格式这个是自定义格式：${date?string("yyyy年MM月dd日HH时mm分ss秒")}</br>
    <#--null值的处理 ${绑定test!}：！如果是null就默认空字符串---跟EL表达式一样
    ${绑定test!指定的默认值}：如果是null,就展示默认值
    -->
    null值-->${test!}不存在的值使用"!"变为空格 </br>
    ${test!"这是null值"} 《--也可以在！后设置其他显示

    <#--include标签 和jsp中一致  <#include "url =====">-->
</p>
</html>