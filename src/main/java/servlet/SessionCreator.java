package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;

import data.SessionToCSRFMap;

public class SessionCreator extends HttpServlet {
    private byte bytearray [] = new byte[20];
    private SecureRandom random = new SecureRandom();
    private SessionToCSRFMap stcm = SessionToCSRFMap.getInstance();


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sess ="";
        String csrfToken ="";
        boolean isLogged = false;

        HttpSession session = request.getSession(false);

        Cookie [] cookies = request.getCookies();
        for(Cookie cookie: cookies){
            if(cookie.getName().equals("STPSesID")){
                if(stcm.isLoggedIn(cookie.getValue())){
                    isLogged = true;
                }
            }
        }

        if(!isLogged) {
            //Will be authenticating user and Making a new session since We don't have a session at this moment
            String username = request.getParameter("user");
            String password = request.getParameter("pass");

            if (username.equals("Adam") && password.equals("helloCSRF")) {
                for (int i = 0; i < 20; i++)
                    sess = +bytearray[i] + "";
                random.nextBytes(bytearray);

                for (int i = 0; i < 20; i++)
                    csrfToken = +bytearray[i] + "";
                random.nextBytes(bytearray);
                stcm.addSession(sess, csrfToken);
                //creating a new session if not available
                session = request.getSession();
                session.setAttribute("SomeBankSes_ID", sess);
                Cookie ses = new Cookie("STPSesID", sess);
                //Setting cookie expiry
                ses.setMaxAge(30 * 60);
                response.addCookie(ses);
                //User authentication complete, redirecting user to the homepage
                response.sendRedirect("home.jsp");
            } else {
                //wrong username and password combo
                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.append("!DOCTYPE html\r\n")
                        .append("<html>\r\n")
                        .append("<body>\r\n")
                        .append("<h1>Error: Invalid credentials! Please <a href='index.jsp'> try again!</a>\r\n")
                        .append("</body>\r\n")
                        .append("</html>\r\n");
            }

        }else{

                response.sendRedirect("home.jsp");
                /*
                response.setContentType("text/html");
                response.setCharacterEncoding("UTF-8");

                PrintWriter writer = response.getWriter();
                writer.append("!DOCTYPE html\r\n")
                        .append("<html>\r\n")
                        .append("<body>\r\n")
                        .append("<h1>Error: Please delete your browser cookies and <a href='index.jsp'> try again!</a>\r\n")
                        .append("</body>\r\n")
                        .append("</html>\r\n");
                */

        }


    }
}
