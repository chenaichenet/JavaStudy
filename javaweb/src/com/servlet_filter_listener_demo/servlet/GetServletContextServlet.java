/*
 * AServlet中添加SercletContex对象
 * BServlet中取出数据*/
package com.servlet_filter_listener_demo.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetServletContextServlet")
public class GetServletContextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext application=this.getServletContext();
        String name=(String)application.getAttribute("name");
        System.out.println(name);
    }
}
