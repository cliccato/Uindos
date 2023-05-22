package app.Calculator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorListener implements ActionListener {
    private CalculatorFrame C;

    public CalculatorListener(CalculatorFrame C) {
        this.C = C;
    }

    public void actionPerformed(ActionEvent e) {
        calculateResult();
    }

    private void calculateResult() {
        String input = C.getDisplay().getText();
        String[] parts = input.split("[+\\-*/]");

        if (parts.length < 2) {
            // Non ci sono abbastanza numeri per il calcolo
            return;
        }

        int num1;
        int num2;
        try {
            num1 = Integer.parseInt(parts[0]);
            num2 = Integer.parseInt(parts[1]);
        } catch (NumberFormatException ex) {
            // Errore di parsing dei numeri
            C.getDisplay().setText("Error");
            return;
        }

        int result = 0;
        if (input.contains("+")) {
            result = num1 + num2;
        } else if (input.contains("-")) {
            result = num1 - num2;
        } else if (input.contains("*")) {
            result = num1 * num2;
        } else if (input.contains("/")) {
            if (num2 == 0) {
                // Divisione per zero
                C.getDisplay().setText("Error");
                return;
            }
            result = num1 / num2;
        }

        C.getDisplay().setText(Integer.toString(result));
    }
}