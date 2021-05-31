package javaprogramming.java.work;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class App 
{
	public static String postfixToInfix(String expression) {
	    String answer = "";
	    Stack<String> stack = new Stack<>();
	    String[] op = {"+", "-", "*", "/", "(", ")"};

	    for (char c : expression.toCharArray()) {
	        if (Arrays.asList(op).contains(c + "")) {

	            if (c == ')') {
	                while (!stack.isEmpty() && stack.peek().charAt(0) != '(') {
	                    answer += stack.pop().charAt(0);
	                }
	                stack.pop();

	            } else {

	                while (!stack.isEmpty() && compareToOperation(c, stack.peek().charAt(0))) {
	                    answer += stack.pop();
	                }
	                stack.push(c + "");
	            }
	        } else {
	            answer += c;
	        }
	    }

	    while (!stack.isEmpty()) {
	        answer += stack.pop();
	    }
	    return answer;
	}

	public static boolean compareToOperation(char op1, char op2) {
        switch (op1) {
            case '+':
                if (op2 == '+' || op2 == '-' || op2 == '*' || op2 == '/') {
                    return true;
                }
            case '-':
                if (op2 == '-' || op2 == '+' || op2 == '*' || op2 == '/') {
                    return true;
                }
            case '*':
                if (op2 == '*' || op2 == '/') {
                    return true;
                }
            case '/':
                if (op2 == '*' || op2 == '/') {
                    return true;
                }
        }
        return false;
    }
	
	public static int getInfixResult(String expression) {
        int answer = 0;
        String[] op = {"+", "-", "*", "/"};
        Stack<Integer> stack = new Stack<>();

        for (char c : expression.toCharArray()) {
            if (Arrays.asList(op).contains(c + "")) {
                int second = stack.pop();
                int first = stack.pop();

                switch (c) {
                    case '+':
                        stack.push(first + second);
                        break;
                    case '-':
                        stack.push(first - second);
                        break;
                    case '*':
                        stack.push(first * second);
                        break;
                    case '/':
                        stack.push(first / second);
                        break;
                }
            } else {
                stack.push(Integer.parseInt(c + ""));
            }
        }

        answer = stack.pop();
        return answer;
    }
	
    public static void main( String[] args )
    {
        Stack stack = new Stack<>();
    	stack.clear();
    	Scanner sc = new Scanner(System.in);
    	System.out.println( "식을 입력하세요.(0을 입력하는 즉시 종료)\n" );
    	
    	while (true){
    	char input1=0;
    	char input2=' ';
    	while(input2 != '0'){
    	System.out.println( "숫자" );
    	input1 = sc.next().charAt(0);
    	if(Character.isDigit(input1)==false) {
    		System.out.println("다시 입력하세요.");
    		continue;
    		}
    	stack.push(input1);
    	System.out.println( "문자" );
    	input2 = sc.next().charAt(0);
    	if(input2=='0') {
    		System.out.println("식 종료.\n");
    		break;
    	}
    	else if(Character.isLetterOrDigit(input2)==true){
    		System.out.println("다시 입력하세요.");
    		continue;
    		}
    	stack.push(input2);
    	}
    	
    	String str = null ;
    	
    	System.out.println(stack);
    	str = String.valueOf(stack);

    			
    	String str_reverse = "";
        for (int i=0;i<str.length();i++)
            str_reverse += stack.pop();

        System.out.println(str_reverse);
    		
    
    	postfixToInfix(str);
    	
    	getInfixResult(str);
    	
    	char more=' ';
    	System.out.println( "계속하시겠습니까?" );
    	more = sc.next().charAt(0);
    	if(more=='n' || more=='N') break;
    	
    	
    }
}

}