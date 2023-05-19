package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;

@WebServlet("/servlet1")
public class Servlet1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Kullanıcıyı doğrula
        User user = authenticateUser(username, password);

        if (user != null) {
            // Oturum oluştur ve kullanıcıyı kaydet
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            response.sendRedirect("addPerson.xhtml"); // Yeni kişi ekleme sayfasına yönlendir
        } else {
            request.setAttribute("error", "Geçersiz kullanıcı adı veya şifre!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("address.xhtml");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    private User authenticateUser(String username, String password) {
        // Kullanıcı doğrulama işlemlerini gerçekleştir
        // Bu örnekte, basit bir kullanıcı doğrulama işlemi yapılacak

        // Örnek kullanıcı bilgileri
        String validUsername = "admin";
        String validPassword = "password";

        if (username.equals(validUsername) && password.equals(validPassword)) {
            // Kullanıcı doğrulandı, yeni bir User nesnesi oluştur
            User user = new User();
            user.setUsername(username);
            // Diğer kullanıcı bilgilerini de ayarlayabilirsiniz

            return user;
        } else {
            return null; // Kullanıcı doğrulanmadı
        }
    }
}
