import java.io.IOException;
import java.util.Scanner;
public class Main {
    static int translateRimtoArab(String snum) {
        int num = -1;
        String [] rome = new String[]{"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        for (int i = 0; i < rome.length; i++){
            if (snum.equals(rome[i])){
                num = i+1;
                break;
            }
        }
        return num;
    }
    static String translateArabtoRim(int result) {
        String Sresult = "0";
        String [] rometo9 = new String[]{"I","II","III","IV","V","VI","VII","VIII","IX"};
        String [] rometo99 = new String[]{"X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
        if (result == 100)
            return "С";
        for (int k = 0; result > 0; k++){
            for (int i = 1; i <=9; i++){
                if(result%10 == i && k == 0){
                    Sresult = rometo9[i-1];
                }
                if(result%10 == i && k == 1){
                    Sresult = rometo99[i-1] + Sresult;
                }
            }
            result = result/10;
        }
        return Sresult;
    }
    public static String calc(String input) throws IOException, NumberFormatException {
        char sign = '0';
        int signNomber = 0;
        for (int i = 0; i < input.length(); i++){
            if (input.charAt(i) == '+' || input.charAt(i) == '-' ||
                    input.charAt(i) == '*' || input.charAt(i) == '/' ){
                if(signNomber == 0){
                    signNomber = i;
                    sign = input.charAt(i);
                }
                else {throw new IOException();}
            }
        }

        if(signNomber == 0 || signNomber == input.length() - 1) {throw new IOException();}
        String[] numbers = new String[2];
        numbers[0] = input.substring(0,signNomber);
        numbers[1] = input.substring(signNomber + 1, input.length());
        int num1 = translateRimtoArab(numbers[0].trim());
        int num2 = translateRimtoArab(numbers[1].trim());
        boolean if0x = false;
        if (num1 != -1 && num2 != -1){
            if0x = true;
        }
        else if(num1 == -1 && num2 == -1){
            num1 = Integer.parseInt(numbers[0].trim());
            num2 = Integer.parseInt(numbers[1].trim());

            if ((num1 < 0 || num1 > 10) || (num2 < 0 || num2 > 10)){
                throw new IOException();
            }
        }
        else {throw new IOException();}

        int result;
        switch (sign){
            case '+': result = num1 + num2; break;
            case '-': result = num1 - num2; break;
            case '*': result = num1 * num2; break;
            case '/': result = num1/num2; break;
            default: throw new  IOException();
        }
        if (if0x && result >0){
            return translateArabtoRim(result);
        }
        else if(!if0x){
            return String.valueOf(result);
        }
        else {throw new IOException();}

    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        try {
            System.out.println(calc(input));
        } catch (IOException e) {
            System.out.println("throws Exception");
        } catch (NumberFormatException nfe) {
            System.out.println("throws Exception");
        }
    }

}