import java.util.Scanner;
class calculate
{
int number1;
int number2;

calculate(int number1, int number2)
{
this.number1=number1;
this.number2=number2;
}

int math()
{
return number1+number2;
}

int math(int number3)
{
return number1*number2*number3;
}

void disply()
{
System.out.println("The first number is : "+number1+" and The second number is : "+number2);
}

public static void main(String args[])
{
Scanner vasco = new Scanner(System.in);
System.out.print("Enter the first number: ");
int n1 = vasco.nextInt();
System.out.print("Enter the second number: ");
int n2 = vasco.nextInt();

calculate cal = new calculate(n1,n2);

cal.disply();
System.out.println("The sum is : "+cal.math());
System.out.println("The product is : "+cal.math(2));
System.out.println("Github");
}
}

