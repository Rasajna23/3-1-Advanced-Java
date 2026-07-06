class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    int sub(int a, int b) {
        return a - b;
    }

    int mul(int a, int b) {
        return a * b;
    }

    int div(int a, int b) {
        return a / b;
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Calculator c = new Calculator();

        System.out.println("Sender : Hi");
        System.out.println("Receiver : Hello! What do you want to calculate?");

        char ch = 'y';

        while (ch == 'y' || ch == 'Y') {

            if (ch == 'y' || ch == 'Y') {

                System.out.print("Enter operator : ");
                char op = sc.next().charAt(0);

                System.out.print("Enter first number: ");
                int a = sc.nextInt();

                System.out.print("Enter second number: ");
                int b = sc.nextInt();

                switch (op) {
                    case '+':
                        System.out.println("Answer = " + c.add(a, b));
                        break;

                    case '-':
                        System.out.println("Answer = " + c.sub(a, b));
                        break;

                    case '*':
                        System.out.println("Answer = " + c.mul(a, b));
                        break;

                    case '/':
                        if (b != 0)
                            System.out.println("Answer = " + c.div(a, b));
                        else
                            System.out.println("Division by zero is not possible.");
                        break;

                    default:
                        System.out.println("Invalid operator.");
                }
            }

            System.out.print("To continue type y otherwise type n: ");
            ch = sc.next().charAt(0);
        }

        System.out.println("Byee");
        sc.close();
    }
}