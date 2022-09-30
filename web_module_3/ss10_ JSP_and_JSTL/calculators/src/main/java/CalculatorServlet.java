import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CalculatorServlet", urlPatterns = "/calculators")
public class CalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double operand1 = Double.parseDouble(request.getParameter("operand1"));
        double operand2 = Double.parseDouble(request.getParameter("operand2"));
        String operator = request.getParameter("operator");
        String result = "";
        try {
            result = String.format("%s %s %s = %s", operand1, operator, operand2, calculate(operand1, operand2, operator));
        } catch (DivideBy0Exception e) {
            result = e.getMessage();
        } catch (RuntimeException e) {
            result = e.getMessage();
        }
        request.setAttribute("message", result);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/calculator.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("message", "Mời bạn nhập phép tính!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/calculator.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static double calculate(double number1, double number2, String operator) throws DivideBy0Exception {

        switch (operator) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "*":
                return number1 * number2;
            case "/":
                if (number2 != 0) {
                    return number1 / number2;
                } else {
                    throw new DivideBy0Exception("Can't divide by zero");
                }
            default:
                throw new RuntimeException("Invalid operation");
        }
    }
}
