package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField display;

    private Calculator calculator;
    private boolean startNumber = true;
    private double number1;
    private String operator = "";
    private boolean point = false;
    private boolean singChange = false;


    @FXML
    private void initialize() {
        calculator = new Calculator();
    }

    @FXML
    public void processDigit(ActionEvent event) {

        String digitPressed = ((Button) event.getSource()).getText();
        System.out.println(digitPressed);
        if(point && !digitPressed.equals(".")){
            if (startNumber || display.getText().equals("0")) {
                display.setText(digitPressed);
            } else {
                display.setText(display.getText() + digitPressed);
            }
        } else if(!point) {
            if ((startNumber || display.getText().equals("0")) && !digitPressed.equals(".")) {
                display.setText(digitPressed);
            } else {
                display.setText(display.getText() + digitPressed);
                point = true;
            }
        }
        startNumber = false;
    }

    @FXML
    public void processOperator(ActionEvent event) {
        String operatorPressed = ((Button) event.getSource()).getText();
        System.out.println(operatorPressed);
        if (operatorPressed.equals("=")) {
           if (operator.isEmpty()) {
               return;
           }
           double number2 = Double.parseDouble(display.getText());
           double result = calculator.calculate(number1, number2, operator);
           display.setText(String.format("%1$,.10f", result));
           operator = "";
        } else {
            if (! operator.isEmpty()) {
                return;
            }
            number1 = Double.parseDouble(display.getText());
            operator = operatorPressed;
            startNumber = true;
            point = false;
        }
    }

    @FXML
    public void processAC(ActionEvent event) {
        display.setText("0");
        operator = "";
        startNumber = true;
        point = false;
        singChange = false;
    }

    @FXML
    void processSignChange(ActionEvent event) {
        if (!singChange && !display.getText().equals("0")) {
            display.setText("-" + display.getText());
            singChange = true;
        } else {
            display.setText(display.getText().replace("-",""));
            singChange = false;
        }
    }
}