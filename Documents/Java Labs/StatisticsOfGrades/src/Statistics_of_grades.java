import java.util.Arrays;
import java.util.Scanner;

public class Statistics_of_grades {

    static Scanner scanner = new Scanner(System.in);

    /**
     * Check range int.
     *
     * @param score the score
     * @return the int
     */
    public static int checkRange(int score){
        int scoreInTens = score/10;
        int modulo = score%10;
        if (modulo > 0)
            return scoreInTens +1;
        return scoreInTens;
    }

    /**
     * Method to draw the bar graph from left to right one line at a time
     * Draw graph string.
     *
     * @param array the array
     */
    public static void drawGraph(int[] array){
        int maxCount = 0;
        for (int count : array){
            if(count > maxCount){
                maxCount = count;
            }
        }
        System.out.println("Graph:\n");
        for (int row = maxCount; row>0; row--){
            System.out.printf("%2d >", row);
            for (int i = 0; i < array.length; i++) {
                int k = maxCount - array[i];
                if (k > 0)
                    System.out.print("             ");
                else
                    System.out.print("   #######   ");
            }System.out.print("\n");
            maxCount --;
        }

//     Printing the horizontal axis
        System.out.println("   +-----------+-------------+-------------+-------------+-------------+");
        System.out.println("   I    0-20   I     21-40   I     41-60   I     61-80   I     81-100  I");
    }

    /**
     * Calculations string.
     * Calculate the minimum, maximum and average grades using streams in arrays
     * @param array the array
     */
    public static void calculations(int[] array){
        int minGrade = Arrays.stream(array).min().getAsInt();
        int maxGrade = Arrays.stream(array).max().getAsInt();
        double avgGrade = Arrays.stream(array).average().getAsDouble();

        System.out.println("\nThe maximum grade is " + maxGrade);
        System.out.println("The minimum grade is " + minGrade);
        System.out.println("The average grade is " + avgGrade + "\n");
    }

    /**
     * Validation int.
     * Check to make sure the input value is an integer
     * @return the int
     */
    public static int validation(){
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input (Should be an integer)!");
            scanner.next();
        }
        return scanner.nextInt();
    }

    /**
     * Take values int [ ].
     * Compliments the takeArrayValues method,
     * to make sure the number of input values is equal to the array size.
     * @param size the size
     * @return the int [ ]
     */
    public static int[] takeValues(int size){
        int[] scores = new int[size];
        System.out.printf("Enter "+ size + "students' scores Note: any extra score will not be considered: \n");
        for (int i = 0; i <= size; i++) {
            scores[i] = validation();
        }
        return scores;
    }

    /**
     * Take values int [ ].
     *
     * @param arraySize the array size
     * @return the int [ ]
     */
    public static int [] takeArrayValues(int arraySize){
        int[] scores = new int[arraySize];
        try{
            System.out.printf("Enter "+ arraySize + "students' scores Note: any extra score will not be considered: \n");
            for (int i = 0; i <= arraySize; i++) {
                scores[i] = validation();
            }
        } catch (Exception e){
            System.out.println("The number of values entered is more than the number of scores given! ");
            scores = takeValues(arraySize);
        }
        return scores;
    }

    public static void main(String[] args) {
//      Allow the user to enter the size of the array
        System.out.println("Enter the number of students' scores you want to work with: ");
        int arraySize = validation();

        int[] scores = takeArrayValues(arraySize);

        calculations(scores);

//      Declare stats array
        int [] stats = new int[5];

//      Loop through scores array and group the scores in the 4 score ranges
        for( int grade : scores)
            switch (checkRange(grade)){
                case 10,9 -> stats[4]++;
                case 8,7 -> stats[3]++;
                case 6,5 -> stats[2]++;
                case 4,3 -> stats[1]++;
                default -> stats[0]++;
            }

        drawGraph(stats);


    }
}
