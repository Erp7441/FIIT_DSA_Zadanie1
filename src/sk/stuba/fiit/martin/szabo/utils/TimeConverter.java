package sk.stuba.fiit.martin.szabo.utils;

public class TimeConverter{

    private TimeConverter(){}

    public static String nanoToSeconds(Long nanoseconds){
        Long decimal = getNumberLength(nanoseconds);
        String specifier = "%." + ((decimal < 9L) ? 9L : decimal) + "f";
        return String.format(specifier, (nanoseconds.floatValue() / 1000000000));
    }

    private static Long getNumberLength(Long number){
        Long count = 0L;
        while(number != 0){
            count++;
            number = number / 10;
        }
        return count;
    }
}
