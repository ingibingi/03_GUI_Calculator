package GUIForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class Calculator {
    //GUI
    private JTextField resultsField;
    private JButton clearBtn;
    private JButton potenzBtn;
    private JButton percentBtn;
    private JButton multiplyBtn;
    private JButton minusBtn;
    private JButton plusBtn;
    private JButton divideBtn;
    private JButton n9Btn;
    private JButton n8Btn;
    private JButton n7Btn;
    private JButton n6Btn;
    private JButton n5Btn;
    private JButton n4Btn;
    private JButton n3Btn;
    private JButton n2Btn;
    private JButton n1Btn;
    private JButton n0Btn;
    private JButton kommaBtn;
    private JButton signBtn;
    private JButton equalsBtn;
    private JPanel calculatorView;

    //Variables
    private Double leftOperand;
    private Double rightOperand;
    private Operation calcOperation;

    public Calculator(){
        n9Btn.addActionListener(new NumberBtnClicked(n9Btn.getText()));
        n8Btn.addActionListener(new NumberBtnClicked(n8Btn.getText()));
        n7Btn.addActionListener(new NumberBtnClicked(n7Btn.getText()));
        n6Btn.addActionListener(new NumberBtnClicked(n6Btn.getText()));
        n5Btn.addActionListener(new NumberBtnClicked(n5Btn.getText()));
        n4Btn.addActionListener(new NumberBtnClicked(n4Btn.getText()));
        n3Btn.addActionListener(new NumberBtnClicked(n3Btn.getText()));
        n2Btn.addActionListener(new NumberBtnClicked(n2Btn.getText()));
        n1Btn.addActionListener(new NumberBtnClicked(n1Btn.getText()));
        n0Btn.addActionListener(new NumberBtnClicked(n0Btn.getText()));

        percentBtn.addActionListener(new OperationBtnClicked(Operation.PERCENTAGE));
        multiplyBtn.addActionListener(new OperationBtnClicked(Operation.MULTIPLICATION));
        divideBtn.addActionListener(new OperationBtnClicked(Operation.DIVISION));
        minusBtn.addActionListener(new OperationBtnClicked(Operation.SUBTRACTION));
        plusBtn.addActionListener(new OperationBtnClicked(Operation.ADDITION));

        equalsBtn.addActionListener(new EqualsBtnClicked());
        clearBtn.addActionListener(new ClearBtnClicked());
        signBtn.addActionListener(new SignBtnClicked());
        kommaBtn.addActionListener(new KommaBtnClicked());
    }

    private class NumberBtnClicked implements ActionListener {
        private String value;
        public NumberBtnClicked(String value){
            this.value = value;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if((leftOperand == null) || (leftOperand == 0.0)){
                value = resultsField.getText() + value;
            }else{
                rightOperand = Double.valueOf(value);
            }
            resultsField.setText(value);
        }
    }

    private class OperationBtnClicked implements ActionListener {

        private Operation operation;

        public OperationBtnClicked(Operation operation) {
            this.operation = operation;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            calcOperation = operation;
            leftOperand = Double.valueOf(resultsField.getText());
        }
    }

    private class ClearBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            resultsField.setText("");
            leftOperand = 0.0;
            rightOperand = 0.0;
        }
    }

    private class KommaBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            resultsField.setText(resultsField.getText() + ".");

        }
    }

    private class EqualsBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Double output = calcOperation.getOperator().applyAsDouble(leftOperand, rightOperand);
            resultsField.setText(output%1==0?String.valueOf(output.intValue()):String.valueOf(output));
            leftOperand = 0.0;
            rightOperand = 0.0;
        }
    }

    private class SignBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            resultsField.setText("-"+ resultsField.getText());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().calculatorView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
