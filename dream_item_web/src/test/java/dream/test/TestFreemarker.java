package dream.test;

import com.dream.pojo.TbItem;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TestFreemarker {
    @Test
    public void demoFreemarker() throws IOException, TemplateException {
        //1、创建一个Configuration（组态）对象,用来设置模板信息 构造方法中只有一个freemarker的版本号参数
        Configuration configuration = new Configuration(Configuration.getVersion());
        //通过Configuration来得到一个模板 通过他来读取和设置信息
        //2、设置模板所在目录
        configuration.setDirectoryForTemplateLoading(new
                File("D:\\IDEA\\dream\\dream_item_web\\src\\test\\resources"));
        //3、设置模板的字符编码
        configuration.setDefaultEncoding("UTF-8");
        //4、加载模板文件
        Template template = configuration.getTemplate("demo.ftl");
        //5、设置模板输出的目录以及输出的文件名（目录必须是已经存在的）
        FileWriter fileWriter = new FileWriter(new File("D:\\IDEA\\Demo_dream\\demo\\demo.html"));
        //将数据加到模板中
        HashMap hashMap = new HashMap<>();
        hashMap.put("msg","这是测试绑定Map键值对绑定数据");
        TbItem tbItem = new TbItem();
        tbItem.setTitle("这是测试使用map绑定对象");
        hashMap.put("tbitem",tbItem);

        //绑定集合
        TbItem tbItem1 = new TbItem();
        TbItem tbItem2 = new TbItem();
        TbItem tbItem3 = new TbItem();
        tbItem1.setTitle("测试绑定集合数据对象1");
        tbItem2.setTitle("测试绑定集合数据对象2");
        tbItem3.setTitle("测试绑定集合数据对象3");
        List<Object> items = new ArrayList<>();
        items.add(tbItem1);
        items.add(tbItem2);
        items.add(tbItem3);
        hashMap.put("items",items);
        //绑定的如果又是一个map
        HashMap<String, TbItem> itemMap = new HashMap<>();
        itemMap.put("1",tbItem1);
        itemMap.put("2",tbItem2);
        itemMap.put("3",tbItem3);
        hashMap.put("itemMap",itemMap);
        //将时间绑定
        hashMap.put("date",new Date());

        //6.生成文件
        template.process(hashMap,fileWriter);
        //7、关闭生成文件的流
        fileWriter.close();
    }

}
