package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.InMemoryPersonDao;
import dataAccess.PersonDao;
import entity.Person;

/**
 * Servlet implementation class PersonServlet
 */
@WebServlet("/personServlet")
public class PersonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PersonDao personDao;

    @Override
    public void init() throws ServletException {
        super.init();
        personDao = new InMemoryPersonDao(); // InMemoryPersonDao nesnesini başlatın
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        int age = Integer.parseInt(request.getParameter("age"));

        // Kişiyi kaydet veya güncelle
        boolean savedOrUpdated = saveOrUpdatePerson(name, surname, phone, address, age);

        if (savedOrUpdated) {
            response.sendRedirect("success.html"); // Başarılı sayfasına yönlendir
        } else {
            request.setAttribute("error", "Kişi kaydedilemedi veya güncellenemedi!");
            request.getRequestDispatcher("addPerson.html").forward(request, response);
        }
    }

    private boolean saveOrUpdatePerson(String name, String surname, String phone, String address, int age) {
        // Kişiyi güncellemek için önce mevcut kişiyi kontrol edin
        Person existingPerson = personDao.getPersonByNameAndSurname(name, surname);

        if (existingPerson != null) {
            // Kişi mevcutsa güncelle
            existingPerson.setPhone(phone);
            existingPerson.setAddress(address);
            existingPerson.setAge(age);
            personDao.updatePerson(existingPerson);
            return true;
        } else {
            // Kişi mevcut değilse yeni kişiyi ekle
            Person newPerson = new Person(name, surname, phone, address, age);
            personDao.addPerson(newPerson);
            return true;
        }
    }
}
