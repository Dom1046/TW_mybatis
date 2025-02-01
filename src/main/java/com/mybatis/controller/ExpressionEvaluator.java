package com.mybatis.controller;

import java.util.Stack;

public class ExpressionEvaluator {
    public static double evaluate(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            return 0;
        }

        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        StringBuilder currentNumber = new StringBuilder();
        boolean lastWasOperator = true;  // 음수 처리를 위해 true로 시작

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch) || ch == '.') {
                currentNumber.append(ch);
                lastWasOperator = false;
            } else if (isOperator(ch)) {
                // 음수 부호 처리
                if (ch == '-' && lastWasOperator) {
                    currentNumber.append(ch);
                    continue;
                }

                if (currentNumber.length() > 0) {
                    numbers.push(Double.parseDouble(currentNumber.toString()));
                    currentNumber.setLength(0);
                }

                while (!operators.isEmpty() &&
                        getPriority(operators.peek()) >= getPriority(ch)) {
                    calculateTop(numbers, operators);
                }

                operators.push(ch);
                lastWasOperator = true;
            }
        }

        if (currentNumber.length() > 0) {
            numbers.push(Double.parseDouble(currentNumber.toString()));
        }

        while (!operators.isEmpty()) {
            calculateTop(numbers, operators);
        }

        return numbers.isEmpty() ? 0 : numbers.pop();
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static int getPriority(char operator) {
        switch (operator) {
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }

    private static void calculateTop(Stack<Double> numbers, Stack<Character> operators) {
        if (numbers.size() < 2) return;

        double b = numbers.pop();
        double a = numbers.pop();
        char operator = operators.pop();

        double result;
        switch (operator) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }

        numbers.push(result);
    }
}
