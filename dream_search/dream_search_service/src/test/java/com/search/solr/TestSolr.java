package com.search.solr;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

public class TestSolr {
  /*  @Test
    public void DemoSolr(){
        *//**
         * ?????????solr??????????????solrservice ????????url
         * ????ж??collection???????????????collection
         *//*
        HttpSolrServer httpSolrServer = new HttpSolrServer("http://192.168.1.12:8080/solr");
        *//**
         * ????????????????????????????????
         *//*
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id","demo01");
        document.addField("item_title","5G???");
        document.addField("item_sell_point","???????");
        document.addField("item_price",6000);
        document.addField("item_image","http://www.baidu.com");
        document.addField("item_category_name","??????");
        document.addField("item_desc","???");
        try {
            httpSolrServer.add(document);
            httpSolrServer.commit();

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    *//**
     * ???????Solr????
     *//*
    @Test
    public void TestDeletSolr() throws IOException, SolrServerException {
        HttpSolrServer httpSolrServer = new HttpSolrServer("http://192.168.1.12:8080/solr");
        //???Id???????
        httpSolrServer.deleteById("demo01");
        //?????????????????
        //httpSolrServer.deleteByQuery("item_title:5G");
        httpSolrServer.commit();
    }
*/
}
