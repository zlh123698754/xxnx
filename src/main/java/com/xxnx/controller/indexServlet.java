package com.xxnx.controller;

import com.xxnx.entity.vo.message;
import com.xxnx.service.UserService;
import com.xxnx.service.indexService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class indexServlet extends HttpServlet {
    private static final indexService indexService = new indexService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        message message = indexService.userindex(
                req.getParameter("m1"),req.getParameter("m2"),req.getParameter("m3"),
                req.getParameter("jinwei"),req.getParameter("gsname"),req.getParameter("dzname"),
                Integer.parseInt(req.getParameter("user_id")));


            req.getSession().setAttribute("user",message.getObject());

    }
}

