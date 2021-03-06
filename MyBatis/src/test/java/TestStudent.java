import com.dao.student.StudentDao;
import com.domain.Student;
import com.utils.SqlSessionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

/**
 * FileName: TestStudent
 * Author:   嘉平十七
 * Date:     2020/7/10 16:44
 * Description:
 * notes：
 */

public class TestStudent {
    @Test
    public void Test1() throws IOException {
        /*访问mybatis读取student数据*/
        //定义mybatis主配置文件名称，从类路径的根开始（target/classes）
        String config="mybatis.xml";
        //读取这个config表示的文件
        InputStream in = Resources.getResourceAsStream(config);
        //创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        //创建SqlSessionFactory对象
        SqlSessionFactory factory=builder.build(in);
        //获取SqlSession对象，非自动提交事务的SqlSession对象
        SqlSession sqlSession=factory.openSession();
//        SqlSession sqlSession=factory.openSession(true);//自动提交事务的对象

        //指定要执行的sql语句，sql映射文件中的namespace+"."+标签id
        String sqlId="com.dao.student.StudentDao"+"."+"selectStudents";
//        String sqlId="com.dao.student.StudentDao.selectStudents";
        //指定sql
        List<Student> studentList = sqlSession.selectList(sqlId);
        //输出结果
        studentList.forEach(student -> System.out.println(student));
        //关闭SqlSession对象
        sqlSession.close();
    }
    @Test
    public void Test2(){
        //插入
        SqlSession sqlSession=SqlSessionUtils.getSqlSession();
        String sqlId="com.dao.student.StudentDao.insertStudent";
        Student student=new Student();

        Scanner scanner=new Scanner(System.in);
        int id=scanner.nextInt();
        String name=scanner.next();
        String email=scanner.next();
        int age= scanner.nextInt();

        student.setAll(id,name,email,age);
        
        int num = sqlSession.insert(sqlId, student);
        //mybatis默认是不提交事务的，所以执行后没有结果
        sqlSession.commit();
        System.out.println("影响行数："+num);
    }
    @Test
    public void Test3(){
        //删除
        SqlSession sqlSession=SqlSessionUtils.getSqlSession();
        String sqlId="com.dao.student.StudentDao.deleteStudent";
        Scanner scanner=new Scanner(System.in);
        int id=scanner.nextInt();
        int num = sqlSession.delete(sqlId,id);
        sqlSession.commit();
        System.out.println("影响行数："+num);
    }
    @Test
    public void Test4(){
        //更新
        SqlSession sqlSession=SqlSessionUtils.getSqlSession();
//        String sqlId="com.dao.student.StudentDao.updateStudent";
//
//        Student student=new Student();
//        Scanner scanner=new Scanner(System.in);
//
//        int id=scanner.nextInt();
//        String name=scanner.next();
//        String email=scanner.next();
//        int age= scanner.nextInt();
//
//        student.setAll(id,name,email,age);
//        int num = sqlSession.update(sqlId,student);
//        sqlSession.commit();
//        System.out.println("影响行数："+num);
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student student=new Student();
        student.setAll(1001,"张三","zhangsan@qq.com",30);
        int num = dao.updateStudent(student,1001);
        sqlSession.commit();
        sqlSession.close();
        System.out.println("影响行数："+num);
    }
    @Test
    public void Test5(){
        //使用mybatis的动态代理机制，可以不创建接口的实现类就调用接口中的方法
        SqlSession sqlSession=SqlSessionUtils.getSqlSession();
        StudentDao dao=sqlSession.getMapper(StudentDao.class);  //这个对象，就是jdk的动态代理类型
        List<Student> studentList = dao.selectStudents();   //这里调用的方法，就是映射文件中定义的方法，所以这里最好要保证接口中的方法名和文件中的id一致
        studentList.forEach(student -> System.out.println(student));
    }
}