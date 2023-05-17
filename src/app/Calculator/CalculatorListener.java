package app.Calculator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorListener implements ActionListener {
    private CalculatorFrame C;

    public CalculatorListener(CalculatorFrame C) {
        this.C = C;
    }

    public void actionPerformed(ActionEvent e) {
        String input = C.getDisplay().getText();
        String[] parts = input.split("[+\\-*/]");
        int num1 = Integer.parseInt(parts[0]);
        int num2 = Integer.parseInt(parts[1]);
        int result = 0;
        if (input.contains("+")) {
            result = num1 + num2;
        } else if (input.contains("-")) {
            result = num1 - num2;
        } else if (input.contains("*")) {
            result = num1 * num2;
        } else if (input.contains("/")) {
            result = num1 / num2;
        }
        C.getDisplay().setText(Integer.toString(result));
    }
}