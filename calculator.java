// Java program to create a simple calculator
// with basic +, -, /, * using java swing elements

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.Stack;
import java.text.DecimalFormat;
class calculator extends JFrame implements ActionListener {
    // create a frame
    static JFrame f;

    // create a textfield
    static JTextField l;

    // store oprerator and operands
    String s0, s1, s2;

    // default constrcutor
    calculator()
    {
        s0 = s1 = s2 = "";
    }

    // main function
    public static void main(String args[])
    {
        // create a frame
        f = new JFrame("calculator");

        try {
            // set look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // create a object of class
        calculator c = new calculator();

        // create a textfield
        l = new JTextField(16);

        // set the textfield to non editable
        l.setEditable(false);

        // create number buttons and some operators
        JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, ba, bs, bd, bm, be, beq, beq1, bparo, bparc, bsin, bpow,
                bcos, btan, bcot, bln, blog, bback;

        // create number buttons
        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");

        // equals button
        beq1 = new JButton("=");

        // create operator buttons
        ba = new JButton("+");
        bs = new JButton("-");
        bd = new JButton("/");
        bm = new JButton("*");
        beq = new JButton("C");
        bparo = new JButton("(");
        bparc = new JButton(")");
        bpow = new JButton("^");
        bsin = new JButton("sin");
        bcos = new JButton("cos");
        btan = new JButton("tan");
        bcot = new JButton("cot");
        bln = new JButton("ln");
        blog = new JButton("log");
        bback = new JButton("⌫");
        // create . button
        be = new JButton(".");

        // create a panel
        JPanel p = new JPanel();

        // add action listeners
        beq.addActionListener(c);
        bm.addActionListener(c);
        bd.addActionListener(c);
        bs.addActionListener(c);
        ba.addActionListener(c);
        b9.addActionListener(c);
        b8.addActionListener(c);
        b7.addActionListener(c);
        b6.addActionListener(c);
        b5.addActionListener(c);
        b4.addActionListener(c);
        b3.addActionListener(c);
        b2.addActionListener(c);
        b1.addActionListener(c);
        b0.addActionListener(c);
        be.addActionListener(c);
        beq1.addActionListener(c);
        bparo.addActionListener(c);
        bparc.addActionListener(c);
        bsin.addActionListener(c);
        bpow.addActionListener(c);
        bcos.addActionListener(c);
        btan.addActionListener(c);
        bcot.addActionListener(c);
        bln.addActionListener(c);
        blog.addActionListener(c);
        bback.addActionListener(c);

        // add elements to panel
        p.add(l);
        p.add(ba);
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(bs);
        p.add(b4);
        p.add(b5);
        p.add(b6);
        p.add(bm);
        p.add(b7);
        p.add(b8);
        p.add(b9);
        p.add(bd);
        p.add(be);
        p.add(b0);
        p.add(beq);
        p.add(bback);
        p.add(bpow);
        p.add(bsin);
        p.add(bcos);
        p.add(btan);
        p.add(bcot);
        p.add(bln);
        p.add(blog);
        p.add(bparo);
        p.add(bparc);
        p.add(be);
        p.add(beq1);

        // set Background of panel
        p.setBackground(Color.yellow);

        // add panel to frame
        f.add(p);

        f.setSize(220, 320);
        f.show();
    }
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("C")) l.setText("");
        else if(s.equals("⌫") && l.getText().length() > 0)l.setText(l.getText().substring(0, l.getText().length()-1));
        else if(s.equals("⌫") && l.getText().length() == 0);
        else if(!s.equals("=") && !isF(s))l.setText(l.getText() + s);
        else if(s.equals("=")){
            DecimalFormat df = new DecimalFormat("0.000000000");
            String s1;
            String res, r;
            double d;
            try {
                s1 = infixToPostfix(l.getText());
                System.out.println(s1);
                d = evaluatePostfix(s1);
                res = df.format(d);
                System.out.println(res + "\n");
            } catch(Exception ex){
                System.out.println("error");
                System.out.println(ex + "\n");
            }
            //int res = evaluatePostfix(s1);
           // l.setText(Integer.toString(res));

        }
        else{
            switch(s){
                case "sin":
                    l.setText(l.getText() + "sin(");
                    break;
                case "cos":
                    l.setText(l.getText() + "cos(");
                    break;
                case "tan":
                    l.setText(l.getText() + "tan(");
                    break;
                case "cot":
                    l.setText(l.getText() + "cot(");
                    break;
                case "ln":
                    l.setText(l.getText() + "ln(");
                    break;
                case "log":
                    l.setText(l.getText() + "log(");
                    break;
            }
        }
    }

    static int Prec(char ch)
    {
        switch (ch)
        {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    // The main method that converts given infix expression
    // to postfix expression.
    static String infixToPostfix(String exp)
    {
        // initializing empty String for result
        String result = "";

        // initializing empty stack
        Stack<Character> stack = new Stack<>();
        char c, c1;
        for (int i = 0; i<exp.length(); ++i)
        {
            c = exp.charAt(i);
            c1 = '?';
            if( i + 1 < exp.length())  c1 = exp.charAt(i+1);
            // If the scanned character is an operand, add it to output.
            if (Character.isLetterOrDigit(c) || c == '.' || isUnary(exp, i)) {
                result += c;
                if(isUnary(exp, i) && c1 == '(') exp = unaryReplacer(exp, i);
                if(Character.isDigit(c) && c1 == '(' || Character.isDigit(c) && c1 == ')'){
                        exp = otherReplacer(exp, i);
                        c1 = '*';
                }
                if ((isOperator(c1) && !isUnary(exp, i + 1)) ||
                        (Character.isLetter(c) && !Character.isLetterOrDigit(c1))) result += " " ;
            }
                // If the scanned character is an '(', push it to the stack.
            else if (c == '(')
                stack.push(c);

                //  If the scanned character is an ')', pop and output from the stack
                // until an '(' is encountered.
            else if (c == ')')
            {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result += " " + stack.pop();

                if (!stack.isEmpty() && stack.peek() != '(')
                    return "Invalid Expression"; // invalid expression
                else {
                    stack.pop();
                    result += " ";
                }
            }
            else // an operator is encountered
            {
                while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())){
                    if(stack.peek() == '(')
                        return "Invalid Expression";
                    result += stack.pop() + " ";
                }
                stack.push(c);
            }

        }

        // pop all the operators from the stack
        while (!stack.isEmpty()){
            if(stack.peek() == '(')
                return "Invalid Expression";
            result += " " + stack.pop();
        }
        return result;
    }

    static double evaluatePostfix(String exp)
    {
        //create a stack
        Stack<Double> stack=new Stack<>();
        exp = exp.replaceAll("\\s+", " ");
        String tokens[] = exp.split(" ");
        Character c = '?';
        // Scan all characters one by one
        for(int i = 0; i < tokens.length; i++)
        {
            String s = tokens[i];
            // If the scanned token is an operand push it to the stack.
            if(isNumber(s)) stack.push(Double.parseDouble(s));
            else if(isF(s)){
                switch(s){
                    case "sin":
                        tokens[i+1] = Double.toString(Math.sin(Double.parseDouble(tokens[i + 1])));
                        break;
                    case "cos":
                        tokens[i+1] = Double.toString(Math.cos(Double.parseDouble(tokens[i + 1])));
                        break;
                    case "tan":
                        tokens[i+1] = Double.toString(Math.tan(Double.parseDouble(tokens[i + 1])));
                        break;
                    case "cot":
                        tokens[i+1] = Double.toString(1 / Math.tan(Double.parseDouble(tokens[i + 1])));
                        break;
                    case "ln":
                        tokens[i+1] = Double.toString(Math.log(Double.parseDouble(tokens[i + 1])));
                        break;
                    case "log":
                        tokens[i+1] = Double.toString(Math.log10(Double.parseDouble(tokens[i + 1])));
                        break;
                }
            }

                //  If the scanned character is an operator, pop two
                // elements from stack apply the operator
            else
            {
                double val1 = stack.pop();
                double val2 = stack.pop();
                c = s.charAt(0);

                switch(c)
                {
                    case '+':
                        stack.push(val2+val1);
                        break;

                    case '-':
                        stack.push(val2- val1);
                        break;

                    case '/':
                        stack.push(val2/val1);
                        break;

                    case '*':
                        stack.push(val2*val1);
                        break;
                    case '^':
                        stack.push(Math.pow(val2, val1));
                        break;
                }
            }
        }
        return stack.pop();
    }

    public static boolean isNumber(String s){
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(0) == '-' && i == 0 && s.length() > 1)continue;
            else if(s.charAt(i) != '.' && !Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }
    public static boolean isF(String s){

        switch(s){
            case "sin":
            case "cos":
            case "tan":
            case "cot":
            case "ln":
            case "log":
                return true;
        }
        return false;
    }

    public static boolean isOperator(Character c){

        switch(c){
            case '+':
            case '*':
            case '/':
            case '^':
            case '-':
                return true;

        }
        return false;
    }

    public static boolean isUnary(String exp, int i){

        Character c = exp.charAt(i);
        if(c == '-'){
            if(i == 0) return true;
            else if(exp.charAt(i-1) == '(') return true;
            else if(isOperator(exp.charAt(i-1))) return true;

        }
        return false;
    }

    //rewrites -(2+2) to -1*(2+2) for the infixToPostfix function to understand
    public static String unaryReplacer(String originalString, int index) {
        String s = originalString.substring(0, index) + originalString.substring(index+1);
        StringBuffer newString = new StringBuffer(s);
        newString.insert(index, "-1*");
        return newString.toString();
    }

    //rewrites 2(2+2) to 2*(2+2) for the infixToPostfix function to understand
    public static String otherReplacer(String originalString, int index) {
        StringBuffer newString = new StringBuffer(originalString);
        Character c = originalString.charAt(index + 1);
        if(c == ')') newString.insert(index + 2, "*");
        else newString.insert(index + 1, "*");
        return newString.toString();
    }
}
