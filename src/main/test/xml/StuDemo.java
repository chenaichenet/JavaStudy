/**
 * FileName: StuDemo
 * Author:   ��ƽʮ��
 * Date:     2020/6/20 12:14
 * Description: ����
 * History:
 * notes��
 */
package main.test.xml;

public class StuDemo {
    public static void main(String[] args) throws Exception{
//        testAdd();
//        testDel();
        testSel();
    }
    //��ѯ
    public static void testSel() throws Exception{
        Student student=StuService.getStu("001");
        System.out.println(student.toString());
    }
    //���
    public static void testAdd() throws Exception{
        Student student=new Student();
        student.setId("003");
        student.setName("����");
        student.setAge("40");
        StuService.addStu(student);
    }
    //ɾ��
    public static void testDel() throws Exception{
        StuService.delStu("003");
    }
}