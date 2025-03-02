public class Test {
    public static char grade(int averageGrade) {
        if (averageGrade > 90 && averageGrade < 100)
            return 'A';
        else if (averageGrade > 80 && averageGrade < 90)
            return 'B';
        else if (averageGrade > 70 && averageGrade < 80)
            return 'C';
        else if (averageGrade > 60 && averageGrade < 70)
            return 'D';
        else if (averageGrade > 0 && averageGrade < 60)
            return 'F';
        return 'I'; // 'I' for invalid input
    }
}

