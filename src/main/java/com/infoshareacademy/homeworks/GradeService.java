package com.infoshareacademy.homeworks;

import java.text.DecimalFormatSymbols;
import java.text.DecimalFormat;
import java.util.*;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;
import static java.lang.Integer.parseInt;


public class GradeService {
    public String[][] calculateAverage(String[][] data) {

        if  (data== null || data.length== 0) {
            return new String[][]{{}};
        }

        DecimalFormatSymbols decimalSymbol = new DecimalFormatSymbols(Locale.getDefault());
        decimalSymbol.setDecimalSeparator('.');

        DecimalFormat myFormat = new DecimalFormat("0.00");
        myFormat.setDecimalFormatSymbols(decimalSymbol);

        Set gradesData = Arrays
                .stream(data)
                .collect(groupingBy(g -> g[0], averagingDouble(g -> parseInt(g[1]))))
                .entrySet();

        String[][] result = new String[gradesData.size()][2];
        List<String> listS = new ArrayList<>();

        gradesData
                .forEach(h -> listS.add(h.toString()));

        Collections.sort(listS);

        int i = 0;
        for (String s : listS) {
            result[i][0] = s.split("=")[0];
            result[i][1] = (myFormat.format(Double.parseDouble(s.split("=")[1])));
            i++;
        }
        return result;
    }
}