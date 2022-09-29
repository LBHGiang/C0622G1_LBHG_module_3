public class Calculate {
    public static double calculate(double number1, double number2, String operator) throws DivideBy0Exception {
        double result = 0;
        if (operator.equals("/")&&number2==0){
            throw new DivideBy0Exception("Lỗi: Không thể chia cho 0");
        }
        switch (operator) {
            default:
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                result = number1 / number2;
                break;
        }
        return result;
    }
}
