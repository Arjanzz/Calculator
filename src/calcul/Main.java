package calcul;

import java.util.Scanner;
class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String str = scanner.nextLine(); // Ввод строки
        calc(str);
    }

    public static String calc(String inputString) {
        String[] arrstr = inputString.split(" ", 3); // Разделение строки на 3 части
        int num[] = getInt(arrstr[0], arrstr[2]); // присваиваем массиву три числа (первое число возвращает 0 или 1 в зависимости от того римские цифры или нет)
        if (num[1] <= 10 && num[2] <=10) // если числа меньше чем 10
        {
            char operation = getOperation(arrstr[1]); // присваиваем операцию
            Res result = new Res();
            result.num1 = num[1];
            result.num2 = num[2];
            result.operation = operation;
            result.runResult(num[0]);
        }
        else {
            System.out.println("Вы ввели число больше чем 10.");
        }
        return null;
    }

    // Получение числа из строки
    public static int[] getInt(String arr1, String arr2){
        int num1 = 0;
        int num2 = 0;
        int ss;
        try
        {
            num1 = Integer.parseInt(arr1); // присвоить значение если это число
            num2 = Integer.parseInt(arr2); // присвоить значение если это число
            ss = 1;
        }
        catch(NumberFormatException nfe)
        {
            num1 = romanToDecimal(arr1); // присвоить число если это римская цифра
            num2 = romanToDecimal(arr2); // присвоить число если это римская цифра
            ss = 0;
        }
        return new int[] {ss, num1, num2}; // вернуть два числа
    }

    // Перевод римских цифр в арабские
    public static int romanToDecimal(java.lang.String romanNumber) {
        int decimal = 0;
        int lastNumber = 0;
        String romanNumeral = romanNumber.toUpperCase(); // Перевод римских цифр в верхний регистр
        for (int x = romanNumeral.length() - 1; x >= 0 ; x--) {
            char convertToDecimal = romanNumeral.charAt(x); // Перебирание каждой цифры
            switch (convertToDecimal) {
                case 'X':
                    decimal = processDecimal(10, lastNumber, decimal); // Вызов функции для определения чему равна данная цифра в сочетании с предыдущей цифрой
                    lastNumber = 10;
                    break;
                case 'V':
                    decimal = processDecimal(5, lastNumber, decimal);
                    lastNumber = 5;
                    break;

                case 'I':
                    decimal = processDecimal(1, lastNumber, decimal);
                    lastNumber = 1;
                    break;
                default:
                    System.out.println("Вы допустили ошибку при вводе числа.");

            }
        }
        return decimal;
    }

    // Объединение римских цифр
    public static int processDecimal(int decimal, int lastNumber, int lastDecimal) {
        if (lastNumber > decimal) { // Если цифра предыдущая больше чем текущая
            return lastDecimal - decimal; // То вернуть "предыдущая - текущая"
        } else { // Иначе
            return lastDecimal + decimal; // То вернуть "предыдущая + текущая"
        }
    }

    // Проверка операции
    public static char getOperation(String arr){
        char operation = arr.charAt(0);
        if (operation != '+' && operation != '-' && operation != '*' && operation != '/') // Если символ не равен +, -, *, / то это ошибка
        {
            System.out.println("Вы допустили ошибку при вводе операции.");

        }
        return operation;
    }
}
class Res {
    int num1;
    int num2;
    char operation;

    void runResult(int ss) {
        int result;
        switch (operation){
            case '+':
                result = num1+num2;
                break;
            case '-':
                result = num1-num2;
                break;
            case '*':
                result = num1*num2;
                break;
            case '/':
                result = num1/num2;
                break;
            default:
                result = 0;
        }
        if (ss == 1) // если приняло число "1", значит это арабская СС
        {
            System.out.println("Результат операции: "+result);
        }
        else { // иначе это римская и необходимо конвертировать из арабской в римскую результат
            System.out.println("Результат операции: "+convert(result));
        }
    }

    public static String romanDigit(int n, String one, String five, String ten){

        if(n >= 1)
        {
            if(n == 1)
            {
                return one;
            }
            else if (n == 2)
            {
                return one + one;
            }
            else if (n == 3)
            {
                return one + one + one;
            }
            else if (n==4)
            {
                return one + five;
            }
            else if (n == 5)
            {
                return five;
            }
            else if (n == 6)
            {
                return five + one;
            }
            else if (n == 7)
            {
                return five + one + one;
            }
            else if (n == 8)
            {
                return five + one + one + one;
            }
            else if (n == 9)
            {
                return one + ten;
            }
            else if (n == 10)
            {
                return ten;
            }
        }
        return "";
    }

    public static String convert(int number) {
        if (number < 0 || number > 3999) {
            return "This number cannot be converted";
        }

        String romanOnes = romanDigit(number %10, "I", "V", "X");// вызов процедуры
        number /= 10;

        String romanTens = romanDigit(number, "X", "L", "C");

        String result = romanTens + romanOnes; //"сложение" того что получилось
        return result;
    }
}