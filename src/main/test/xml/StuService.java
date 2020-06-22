/**
 * FileName: StuService
 * Author:   ��ƽʮ��
 * Date:     2020/6/20 11:47
 * Description:
 * History:
 * notes��
 */
package main.test.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.util.List;

public class StuService {
    //��ѯ������id��ѯ��Ϣ
    public static Student getStu(String id) throws Exception {
        /*1.����������
        * 2.�õ�document
        * 3.��ȡ����id
        * 4.����list��ϣ�����list����
        * 5.�õ�ÿһ��id�Ľڵ�
        * 6.id�ڵ��ֵ
        * 7.�ж�id��ֵ�ʹ��ݵ�idֵ�Ƿ���ͬ
        * 8.��ͬ�����id�ĸ��ڵ�stu
        * 9.ͨ��stu��ȡname,ageֵ*/
        SAXReader saxReader=new SAXReader();    //����������
        Document document=saxReader.read("src/main/test/xml/student.xml");    //�õ�document

        List<Node> list=document.selectNodes("//id");
        Student student=new Student();
        for (Node node:list){
            String idv=node.getText();
            if (idv.equals(id)){
                Element stu=node.getParent();
                String name=stu.element("name").getText();
                String age=stu.element("age").getText();
                student.setId(id);
                student.setName(name);
                student.setAge(age);
            }
        }
        return student;
    }

    //����
    public static void addStu(Student student) throws Exception {
        SAXReader saxReader=new SAXReader();    //����������
        Document document=saxReader.read("src/main/test/xml/student.xml");    //�õ�document
        Element root=document.getRootElement(); //�õ����ڵ�
        Element stu=root.addElement("stu");     //�ڸ��ڵ������stu
        //��stu���������id��name��age��ǩ
        Element id=stu.addElement("id");
        Element name=stu.addElement("name");
        Element age=stu.addElement("age");
        //��id��name��age���������ֵ
        id.setText(student.getId());
        name.setText(student.getName());
        age.setText(student.getAge());
        //��дxml
        OutputFormat format=OutputFormat.createPrettyPrint();
        XMLWriter xmlWriter=new XMLWriter(new FileOutputStream("src/main/test/xml/student.xml"),format);
        xmlWriter.write(document);
        xmlWriter.close();
    }
    //ɾ��������ѧ��idɾ��
    public static void delStu(String id) throws Exception {
        SAXReader saxReader=new SAXReader();    //����������
        Document document=saxReader.read("src/main/test/xml/student.xml");    //�õ�document
        List<Node> list=document.selectNodes("//id");   //��ȡ���е�id ����xpath://id
        for (Node node:list){   //����list���ϣ�node��ÿһ��idԪ��
            String idv=node.getText();  //�õ�id
            if (idv.equals(id)){
                Element stu=node.getParent();   //�õ�stu�ڵ�
                Element student=stu.getParent();//�õ�stu���ڵ�student
                student.remove(stu);    //ɾ��
            }
        }
        //��д
        OutputFormat format=OutputFormat.createPrettyPrint();
        XMLWriter xmlWriter=new XMLWriter(new FileOutputStream("src/main/test/xml/student.xml"),format);
        xmlWriter.write(document);
        xmlWriter.close();
    }
}