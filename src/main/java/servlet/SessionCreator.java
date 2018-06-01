package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.SecureRandom;

import data.SessionToCSRFMap;

public class SessionCreator extends HttpServlet {
    byte bytearray [] = new byte[20];

    SecureRandom random = new SecureRandom();


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();
        session.setAttribute("SomeBankSes_ID", bytearray );
        System.out.println(bytearray);
        random.nextBytes(bytearray);
        int a =5;
        //Write stuff here
    }
}
