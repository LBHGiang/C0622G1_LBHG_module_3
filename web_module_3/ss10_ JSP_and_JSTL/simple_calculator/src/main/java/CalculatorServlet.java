import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CalculatorServlet", value = "/calculators")
public class CalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double operand1 = Double.parseDouble(request.getParameter("operand1"));
        double operand2 = Double.parseDouble(request.getParameter("operand2"));
        String operator = request.getParameter("operator");
        String result = "";
        try {
            result = String.format("%s %s %s = %s",operand1,operand2,operator,Calculate.calculate(operand1, operand2, operator));
        } catch (DivideBy0Exception e) {
            result = "Lỗi: Không thể chia cho 0";
        }
        request.setAttribute("message", result);
        RequestDispatcher dispatcher = request.getRequestDispatcher("calculator2.jsp");
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
