/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import beans.Creditcard;
import beans.User;
import db.UsersDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.Constants;

/**
 *
 * @author A7med
 */
@WebServlet(name = "CreditCardsServlet", urlPatterns = {"/admin/creditcards"})
public class CreditCardsServlet extends HttpServlet {

    UsersDao handler = new UsersDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Creditcard> cardsList = handler.getCreditcardsList();
        request.setAttribute("cardsList", cardsList);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("creditCards.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int isCardAdded = Constants.ERROR_FAILED;
        String _cardNumber = request.getParameter("cardNumber");
        String _cardValue = request.getParameter("cardValue");
        if (_cardNumber != null && _cardValue != null) {
            long cardNumber = Long.parseLong(_cardNumber);
            double cardValue = Double.parseDouble(_cardValue);
            isCardAdded = handler.addCreditcard(cardNumber, cardValue);
            request.setAttribute("isCardAdded", isCardAdded);
        }
        processRequest(request, response);
    }
}
