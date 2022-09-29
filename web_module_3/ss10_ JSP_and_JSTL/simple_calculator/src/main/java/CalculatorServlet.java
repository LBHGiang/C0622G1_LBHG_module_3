import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CalculatorServlet", value = "calculator")
public class CalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        double operand1 = Double.parseDouble(request.getParameter("operand1"));
        double operand2 = Double.parseDouble(request.getParameter("operand2"));
        String operator = request.getParameter("operator");
        double result = 0;
        try {
            result = Calculate.calculate(operand1, operand2, operator);
        } catch (DivideBy0Exception e) {
            request.setAttribute("message", e);
        }
        request.setAttribute("message", "Result: " + operand1 + operator + operand2 + "=" + result);
        RequestDispatcher dispatcher = request.getRequestDispatcher("calculator.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
