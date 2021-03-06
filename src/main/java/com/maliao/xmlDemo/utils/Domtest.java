package com.maliao.xmlDemo.utils;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by minjj on 2016/10/22 0022.
 */
public class Domtest {


        public static void main(String[] args)
        {
            //创建一个 DocumentBuilderFactory对象
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            try
            {
                //创建DocumentBuilder对象
                DocumentBuilder db = dbf.newDocumentBuilder();
                //读取加载xml文件
                Document document = db.parse("src/main/java/com/maliao/xmlDemo/books.xml");
                //获取所有的book节点的集合
                NodeList bookList = document.getElementsByTagName("book");
                //t通过bookList.getLength()方法可以获取节点个数
                System.out.println("一共有" + bookList.getLength() + "本书");
                //遍历每一个book节点
                for (int i = 0; i < bookList.getLength(); i++) {
                    System.out.println("=================下面开始遍历第" + (i + 1) + "本书的内容=================");
                    //获取一个节点信息
                    Node book = bookList.item(i);

                    NamedNodeMap attrs = book.getAttributes();
                    System.out.println("第 " + (i + 1) + "本书共有" + attrs.getLength() + "个属性");
                    //遍历book节点
                    for (int j = 0; j < attrs.getLength(); j++)
                    {
                        Node attr = attrs.item(j);

                        System.out.print("属性名：" + attr.getNodeName());

                        System.out.println("--属性值" + attr.getNodeValue());
                    }

                    NodeList childNodes = book.getChildNodes();

                    System.out.println("第" + (i + 1) + "本书共有" +
                            childNodes.getLength() + "个子节点");
                    for (int k = 0; k < childNodes.getLength(); k++)
                    {
                        if (childNodes.item(k).getNodeType() != 1)
                            continue;
                        System.out.print("第" + (k + 1) + "个节点的节点名：" +
                                childNodes.item(k).getNodeName());

                        System.out.println("--节点值是：" + childNodes.item(k).getFirstChild().getNodeValue());
                    }

                    System.out.println("======================结束遍历第" + (i + 1) + "本书的内容=================");
                }
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
