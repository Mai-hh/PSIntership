package com.maihao.calculator;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    // 运算符优先级
    private static final char[][] operatorPriority = {
            {'>', '>', '<', '<', '<', '>', '>'},
            {'>', '>', '<', '<', '<', '>', '>'},
            {'>', '>', '>', '>', '<', '>', '>'},
            {'>', '>', '>', '>', '<', '>', '>'},
            {'<', '<', '<', '<', '<', '=', 'E'},
            {'E', 'E', 'E', 'E', 'E', 'E', 'E'},
            {'<', '<', '<', '<', '<', 'E', '='},
    };

    // 数字栈
    Stack<Double> numStack = new Stack<>();
    // 运算符栈
    Stack<Character> operatorStack = new Stack<>();
    // 括号计数
    int bracketSum = 0;

    Scanner scanner = new Scanner(System.in);

    public void calculate() {

        // 运算符栈初始化读入开始符
        operatorStack.push('#');

        // 读取运算式子
        String formula = scanner.next() + "#";
        if (formula.contains(")(")) {
            System.out.println("不支持" + ")(" + "格式");
            return;
        }

        // 用一个缓存区来支持浮点数
        StringBuilder numBuffer = new StringBuilder();

        int index = 0;
        while (index < formula.length()) {

            if (formula.charAt(index) == ' ') {
                // 读到空格
                index++;
            } else if (Character.isDigit(formula.charAt(index)) || formula.charAt(index) == '.') {
                // 读到数字或者小数点
                numBuffer.delete(0, numBuffer.length());
                while(index < formula.length() && (Character.isDigit(formula.charAt(index)) || formula.charAt(index) == '.')) {
                    numBuffer.append(formula.charAt(index++));
                }
                double num = Double.parseDouble(numBuffer.toString());
                numStack.push(num);
            } else {
                // 读到运算符
                char currentOperator = formula.charAt(index);

                if (currentOperator == '(' || currentOperator == ')') {
                    bracketSum ++;
                }

                int curOperatorNum = getOperatorNum(currentOperator);
                // 如果输入了非法字符，就退出
                if (curOperatorNum == -1) {
                    break;
                }

                boolean loop = true;
                while (loop) {

                    // 拿到栈顶运算符
                    char preOperator = operatorStack.peek();
                    int preOperatorNum = getOperatorNum(preOperator);

                    // 比较运算符优先级
                    switch (operatorPriority[preOperatorNum][curOperatorNum]) {
                        case '=':
                            loop = false;
                            operatorStack.pop();
                            break;
                        case '<':
                            loop = false;
                            operatorStack.push(currentOperator);
                            break;
                        case '>': // 仅当栈顶运算符优先级更大时，进行一波数字栈的更新，并继续判断运算符优先级关系
                            loop = true;
                            double num2 = numStack.pop();
                            double num1 = numStack.pop();
                            char opr = operatorStack.pop();
                            double result = operate(num1, num2, opr);
                            numStack.push(result);
                            break;
                        default:
                            loop = false;
                            break;
                    }

                }
                index++;
            }
        }
        if (numStack.size() != 1 && operatorStack.size() != 0 || bracketSum % 2 != 0) {
            System.out.println("算式格式有误");
        } else if (numStack.isEmpty()) {
            System.out.println("请输入数字");
        } else {
            System.out.println(numStack.pop());
        }
    }

    private double operate(double num1, double num2, char opr) {
        switch(opr){
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                return 0.0;
        }
    }

    private int getOperatorNum(char currentOperator) {
        switch (currentOperator) {
            case '+':
                return 0;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 3;
            case '(':
                return 4;
            case ')':
                return 5;
            case '#':
                return 6;
            default:
                return -1;
        }
    }

    public static void main(String[] args) {

        new Calculator().calculate();

    }

}

