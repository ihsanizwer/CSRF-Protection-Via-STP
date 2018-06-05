package servlet;

import validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Transaction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isValidTransaction = false;
        Cookie cookies[] = req.getCookies();
        for(Cookie cookie: cookies){
            if(cookie.getName().equals("STPSesID")){
                if(Validator.isValidSession(cookie.getValue(),req.getParameter("session_id"))){
                    isValidTransaction = true;
                }
            }
        }
        if(isValidTransaction){
            Double amount =  Double.parseDouble(req.getParameter("amount"));
            //Add transfer amount code
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.append("<!DOCTYPE html>")
                    .append("<html>")
                    .append("<body>")
                    .append("<h1>Transfer successful. The following amount of credits has been transferred : "+amount+"</h1>")
                    .append("</body>")
                    .append("</html>");
        }else{
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.append("<!DOCTYPE html>")
                    .append("<html>")
                    .append("<body>")
                    .append("<h1>An Error has occured! Contact developer!</h1>")
                    .append("</body>")
                    .append("</html>");
        }
    }
}
