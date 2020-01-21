
package data.structer;

public class Pos {
    String infix ;
    String postfix ;

    public Pos() {
        this.infix = new String ("") ;
        this.postfix = new String ("") ;
    }

    public void setInfix(String infix) {
        this.infix = infix;
    }

    public String getInfix() {
        return infix;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    public String infixToPostfix(){
        String p = "";
        LinkedStack<String> stack = new LinkedStack<>();
        String[] tokens = this.infix.split(" ");
        String top;
        for (String token : tokens) {
            if(isOpenP(token)){
                stack.push("(");
            }else if(isCloseP(token)){
                top = stack.top();
                while(!"(".equals(top)){
                    top = stack.pop();
                    p += top + " ";
                    top = stack.top();
                }
                stack.pop();// (
            }else if(isOperator(token)){
                top = stack.top();
                if(stack.isEmpty() || top.equals("(")){
                    stack.push(token);
                }else{
                    top = stack.top();
                    while( !stack.isEmpty() && shouldWePopFromStack(token, top)
                            && !top.equals("("))
                    {

                        top = stack.pop();
                        p += top + " ";
                        top = stack.top();
                    }
                    stack.push(token);

                }


            }else{
                p += token +" ";
            }
        }

        while(!stack.isEmpty()){
            p += stack.pop() + " ";
        }
        setPostfix(p);
        return p.trim();
    }

    private boolean shouldWePopFromStack(String token, String stackTop ){
        if("+-".contains(stackTop) && "*/".contains(token))
            return false;
        else if("+-".contains(stackTop) && "+-".contains(token))
            return true;
        else if("*/".contains(stackTop) && "+-".contains(token))
            return true;
        else if("*/".contains(stackTop) && "*/".contains(token))
            return true ;
        else if("*/".contains(stackTop) && "sin cos tan cot ln ep log sqrt".contains(token))
            return false;
        else if("sin cos tan cot ln ep log sqrt".contains(stackTop) && "*/".contains(token))
            return true;
        else if("+-".contains(stackTop) && "sin cos tan cot ln ep log sqrt".contains(token))
            return false;
        else if("sin cos tan cot ln ep log sqrt".contains(stackTop) && "+-".contains(token))
            return true;
        else if("sin cos tan cot ln ep log sqrt".contains(stackTop) && "sin cos tan cot ln ep log sqrt".contains(token))
            return true;
        else if("^".contains(stackTop) && "*/".contains(token))
            return true;
        else if("^".contains(stackTop) && "+-".contains(token))
            return true;
        else if("^".contains(stackTop) && "sin cos tan cot ln ep log sqrt".contains(token))
            return false;
        return false;
    }

    private boolean isOperator(String token){
        return "*+-/^sincostancotlneplogsqrt".contains(token);
    }

    private boolean isOpenP(String token){
        return token.equals("(");
    }

    private boolean isCloseP(String token){
        return token.equals(")");
    }

    public double evaluatePostfix(){

        LinkedStack<Double> stack = new LinkedStack<>();
        String[] tokens = this.postfix.split(" ");
        Double v = 0.0;
        for (String token : tokens) {
            if(isOperator(token) && "sincostancotlneplogsqrt".contains(token)){
                Double a = stack.pop();
                Double b = stack.top() ;
                // b operator a
                switch(token){
                    case "+":
                        v = (b + a);
                        break;
                    case "-":
                        v = b - a;
                        break;
                    case "/":
                        v = b / a;
                        break;
                    case "*":
                        v = b * a;
                        break;
                    case "sin":
                        double temp = a * Math.PI / 180;
                        v = Math.sin(temp);
                        break;
                    case "cos":
                        double temp1 = a * Math.PI / 180;
                        v = Math.cos(temp1);
                        break;
                    case "tan":
                        double temp2 = a * Math.PI / 180;
                        v = Math.tan(temp2);
                        break;
                    case "cot":
                        double temp3 = a * Math.PI / 180;
                        v = 1 / Math.tan(temp3);
                        break;
                    case "ln":
                        v = Math.log1p(a);
                        break;
                    case "ep":
                        v = Math.exp(a);
                        break;
                    case "log":
                        v = Math.log10(a);
                        break;
                    case "sqrt":
                        v = Math.sqrt(a);
                        break;
                    case "^":
                        v = Math.pow(b  , a ) ;
                        break;
                }
                stack.push(v);
            }

            else if(isOperator(token) && "+/*-^".contains(token)){
                Double a = stack.pop();
                Double b = stack.pop() ;
                // b operator a
                switch(token){
                    case "+":
                        v = b + a;
                        break;
                    case "-":
                        v = b - a;
                        break;
                    case "/":
                        v = b / a;
                        break;
                    case "*":
                        v = b * a;
                        break;
                    case "sin":
                        double temp = a * Math.PI / 180;
                        v = Math.sin(a);
                        break;
                    case "cos":
                        double temp1 = a * Math.PI / 180;
                        v = Math.cos(temp1);
                        break;
                    case "tan":
                        double temp2 = a * Math.PI / 180;
                        v = Math.tan(temp2);
                        break;
                    case "cot":
                        double temp3 = a * Math.PI / 180;
                        v = 1 / Math.tan(temp3);
                        break;
                    case "ln":
                        v = Math.log1p(a);
                        break;
                    case "ep":
                        v = Math.exp(a);
                        break;
                    case "log":
                        v = Math.log10(a);
                        break;
                    case "sqrt":
                        v = Math.sqrt(a);
                        break;
                    case "^":
                        v = Math.pow(b  , a ) ;
                        break;
                }
                stack.push(v);
            }

            else{ // token is operand
                v = Double.parseDouble(token);
                stack.push(v);
            }
        }


        return stack.pop();
    }
    
    public double evaluatePostfixDraw(){

        LinkedStack<Double> stack = new LinkedStack<>();
        String[] tokens = this.postfix.split(" ");
        Double v = 0.0;
        for (String token : tokens) {
            if(isOperator(token) && "sincostancotlneplogsqrt".contains(token)){
                Double a = stack.pop();
                Double b = stack.top() ;
                // b operator a
                switch(token){
                    case "+":
                        v = b + a;
                        break;
                    case "-":
                        v = b - a;
                        break;
                    case "/":
                        v = b / a;
                        break;
                    case "*":
                        v = b * a;
                        break;
                    case "sin":
                        double temp = a * Math.PI / 180;
                        v = 100 * Math.sin(temp);
                        break;
                    case "cos":
                        double temp1 = a * Math.PI / 180;
                        v =100 * Math.cos(temp1);
                        break;
                    case "tan":
                        double temp2 = a * Math.PI / 180;
                        v = 100 * Math.tan(temp2);
                        break;
                    case "cot":
                        double temp3 = a * Math.PI / 180;
                        v = 100 * (1 / Math.tan(temp3));
                        break;
                    case "ln":
                        v = Math.log1p(a);
                        break;
                    case "ep":
                        v = Math.exp(a);
                        break;
                    case "log":
                        v = Math.log10(a);
                        break;
                    case "sqrt":
                        v = Math.sqrt(a);
                        break;
                    case "^":
                        v = Math.pow(b  , a ) ;
                        break;
                }
                stack.push(v);
            }

            else if(isOperator(token) && "+/*-^".contains(token)){
                Double a = stack.pop();
                Double b = stack.pop() ;
                // b operator a
                switch(token){
                    case "+":
                        v = b + a;
                        break;
                    case "-":
                        v = b - a;
                        break;
                    case "/":
                        v = b / a;
                        break;
                    case "*":
                        v = b * a;
                        break;
                    case "sin":
                        double temp = a * Math.PI / 180;
                        v = 100 * Math.sin(a);
                        break;
                    case "cos":
                        double temp1 = a * Math.PI / 180;
                        v = 100 * Math.cos(temp1);
                        break;
                    case "tan":
                        double temp2 = a * Math.PI / 180;
                        v =100 *  Math.tan(temp2);
                        break;
                    case "cot":
                        double temp3 = a * Math.PI / 180;
                        v = 100 * (1 / Math.tan(temp3));
                        break;
                    case "ln":
                        v = Math.log1p(a);
                        break;
                    case "ep":
                        v = Math.exp(a);
                        break;
                    case "log":
                        v = Math.log10(a);
                        break;
                    case "sqrt":
                        v = Math.sqrt(a);
                        break;
                    case "^":
                        v = Math.pow(b  , a ) ;
                        break;
                }
                stack.push(v);
            }

            else{ // token is operand
                v = Double.parseDouble(token);
                stack.push(v);
            }
        }


        return stack.pop();
    }

    public void setinfix(String expr) {
         this.infix = infix;
    }
}

