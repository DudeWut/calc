// Java program to create a simple calculator 
// with basic +, -, /, * using java swing elements 

import java.awt.event.*; 
import javax.swing.*; 
import java.awt.*; 
import java.util.Stack;
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
		JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, ba, bs, bd, bm, be, beq, beq1, bparo, bparc, bsin; 

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
      bsin = new JButton("sin");

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
		p.add(beq1); 
      p.add(bparo);
      p.add(bparc);
      p.add(bsin);

		// set Background of panel 
		p.setBackground(Color.yellow); 

		// add panel to frame 
		f.add(p); 

		f.setSize(200, 250); 
		f.show(); 
	} 
	public void actionPerformed(ActionEvent e) { 
      String s = e.getActionCommand();
      if(s.equals("C")) l.setText("");
      else if(s.equals("sin"))l.setText(l.getText() + "s");
      else if(!s.equals("="))l.setText(l.getText() + s);
      else if(s.equals("=")){
         //System.out.println(infixToPostfix(l.getText()));
         String s1 = infixToPostfix(l.getText());
         int res = evaluatePostfix(s);
        
         
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
        String result = new String(""); 
          
        // initializing empty stack 
        Stack<Character> stack = new Stack<>(); 
          
        for (int i = 0; i<exp.length(); ++i) 
        { 
            char c = exp.charAt(i); 
              
             // If the scanned character is an operand, add it to output. 
            if (Character.isLetterOrDigit(c)) 
                result += c; 
               
            // If the scanned character is an '(', push it to the stack. 
            else if (c == '(') 
                stack.push(c); 
              
            //  If the scanned character is an ')', pop and output from the stack  
            // until an '(' is encountered. 
            else if (c == ')') 
            { 
                while (!stack.isEmpty() && stack.peek() != '(') 
                    result += stack.pop(); 
                  
                if (!stack.isEmpty() && stack.peek() != '(') 
                    return "Invalid Expression"; // invalid expression                 
                else
                    stack.pop(); 
            } 
            else // an operator is encountered 
            { 
                while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())){ 
                    if(stack.peek() == '(') 
                        return "Invalid Expression"; 
                    result += stack.pop(); 
             } 
                stack.push(c); 
            } 
       
        } 
       
        // pop all the operators from the stack 
        while (!stack.isEmpty()){ 
            if(stack.peek() == '(') 
                return "Invalid Expression"; 
            result += stack.pop(); 
         } 
        return result; 
    } 
    
     static int evaluatePostfix(String exp) 
    { 
        //create a stack 
        Stack<Integer> stack=new Stack<>(); 
          
        // Scan all characters one by one 
        for(int i=0;i<exp.length();i++) 
        { 
            char c=exp.charAt(i); 
              
            // If the scanned character is an operand (number here), 
            // push it to the stack. 
            if(Character.isDigit(c)) 
            stack.push(c - '0'); 
              
            //  If the scanned character is an operator, pop two 
            // elements from stack apply the operator 
            else
            { 
                int val1 = stack.pop(); 
                int val2 = stack.pop(); 
                  
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
              } 
            } 
        } 
        return stack.pop();     
    } 
   
   public void evaluateExpressionn(String s){
     
   
   
   
   } 
   
   } 
