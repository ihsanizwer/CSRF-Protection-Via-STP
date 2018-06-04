package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import data.SessionToCSRFMap;

public class SessionDestroyer {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionToCSRFMap ctcm = SessionToCSRFMap.getInstance();

    }

}
