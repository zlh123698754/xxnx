package com.xxnx.controller;

import com.xxnx.entity.vo.message;
import com.xxnx.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/login")
public class userServlet extends HttpServlet {
    //实例化
    private static final UserService userService = new UserService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String  school_id = req.getParameter("school_id");
        String  accout = req.getParameter("accout");
        String  passwd = req.getParameter("passwd");

        message message = null;
        try {
            message = userService.userLogin(Integer.valueOf(school_id),accout,passwd);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        if(message.getCode() == 1){//成功
            req.getSession().setAttribute("user",message.getObject());
            resp.sendRedirect("index.jsp");
        }else {//失败
            req.setAttribute("message",message);
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
