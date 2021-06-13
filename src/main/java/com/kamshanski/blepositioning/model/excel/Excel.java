package com.kamshanski.blepositioning.model.excel;

import com.kamshanski.blepositioning.model.storage.NiceStorage;
import com.kamshanski.blepositioning.utils.U;
import com.kamshanski.utils.structures.wrappers.Mutable;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Excel {
    Workbook wb;
    Sheet sheet;
    int emptyRows = 10, emptyCols = 1;
    int colsNum = 0;
    HashMap<String, Integer> columns = new HashMap<>();

    public Excel(int emptyRows, int emptyCols) {
        wb = new HSSFWorkbook();
        sheet = wb.createSheet("Recordings1");
        Row r = sheet.createRow(emptyRows);
        sheet.createRow(emptyRows+1);
    }

    public int putData(String name, Object numbers, int rowStart) {
        // Счётчик столбцов
        final int dataAmount = columns.size() + emptyCols;
        int col = columns.computeIfAbsent(name, n -> dataAmount);

        // Счётчик строк
        int i = (rowStart > 0) ? rowStart : emptyRows + 1;
        // Заголовочная строка
        if (rowStart == 0) {
            Row r = row(i++);
            r.createCell(col, CellType.STRING).setCellValue(name);
        }

        // Заполнение строк данными
        if (numbers instanceof double[]) {
            double[] data = (double[]) numbers;
            for (double datum : data) {
                Row r = row(i++);
                r.createCell(col, CellType.NUMERIC).setCellValue(datum);
            }
        } else {
            U.nout("ERROR: UNKNOWN NUMBERS SOURCE TYPE");
        }

        return i;

    }

    public void putRow(int rowNum, int colStart, double[] data) {
        Row r = row(rowNum);
        for (int i = 0, pos = colStart; i < data.length; i++, pos++) {
            r.createCell(i, CellType.NUMERIC)
             .setCellValue(data[pos]);
        }
    }

    public void putCell(int rowNum, int colStart, double data) {
        Row r = row(rowNum);
        r.createCell(colStart, CellType.NUMERIC)
                .setCellValue(data);
    }
    public void putCol(int rowNum, int colNum, double[] data) {
        for (int i = 0, pos = rowNum; i < data.length; i++, pos++) {
            row(i).createCell(colNum, CellType.STRING)
                    .setCellValue(data[pos]);
        }
    }

    public void putRow(int rowNum, int colStart, String[] data) {
        Row r = row(rowNum);
        for (int i = 0, pos = colStart; i < data.length; i++, pos++) {
            r.createCell(i, CellType.STRING)
                    .setCellValue(data[pos]);
        }
    }

    public void putCell(int rowNum, int colStart, String data) {
        Row r = row(rowNum);
        r.createCell(colStart, CellType.STRING)
                .setCellValue(data);
    }

    // Просто заносит данные с первой строки emptyRows. Не стирает и не дополняет имеющиеся данные, просто пишет поверх
//    public int putData(SimpleStorage ss, int firstRow) {
//        final int dataAmount = columns.size() + emptyCols;
//        String name = ss.name();
//        int col = columns.computeIfAbsent(name, n -> dataAmount);
//
//        // Счётчик строк
//        int i = (firstRow > 0) ? firstRow : emptyRows + 1;
//        // Заголовочная строка
//        Row r = row(i++);
//        r.createCell(col, CellType.STRING).setCellValue(name);
//
//        // Заполнение строк данными
//        int[] data = ss.getAll();
//        for (int datum : data) {
//            r = row(i++);
//            r.createCell(col, CellType.NUMERIC).setCellValue(datum);
//        }
//        return i;
//    }

    public void putData(NiceStorage storage) {
        storage.compute((size, store) -> {
            Mutable.Int colsCounter = new Mutable.Int(emptyCols + 1);
            store.forEach((id, ll) -> {
                int i = emptyRows + 2;
                Row r = sheet.getRow(i++);
                if (r == null) {
                    r = sheet.createRow(i);
                }
                r.createCell(colsCounter.v, CellType.STRING).setCellValue(String.valueOf(id));

                for (double[] doubles : ll) {
                    for (double aDouble : doubles) {
                        r = sheet.getRow(i);
                        if (r == null) {
                            r = sheet.createRow(i);
                        }
                        r.createCell(colsCounter.v, CellType.NUMERIC).setCellValue(aDouble);
                        i++;
                    }
                }
                colsCounter.v = colsCounter.v + 1;
            });
        });
    }

    public String save(String expName) {
        fillData();
        Path path = constructPath(expName);
        saveTo(path);
        return path.toString();
    }

    private void fillData() {
//        ArrayList<ArrayListDouble> arrs = new ArrayList<>(data.values());
//        int arrsSize = arrs.size();
//        for (int r = emptyRows + 1, i = 0; r < dataSize; r++, i++) {
//            Row row = sheet.createRow(r);
//            for (int j = 0, c = emptyCols; j < arrsSize; j++, c++) {
//                double rssi = arrs.get(j).get(i);
//                row.createCell(c, CellType.NUMERIC).setCellValue(rssi);
//            }
//        }
    }

    private Path constructPath(String expName) {
        Instant instant = Instant.now();
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss")
                .withZone(ZoneId.systemDefault());
        String dateTime = DATE_TIME_FORMATTER.format(instant);
        StringBuilder builder = new StringBuilder();
        builder .append("C:\\Users\\Dawan\\Desktop\\")
                .append(expName)
                .append("_")
                .append(dateTime);
        builder.append(".xls");
        return Paths.get(builder.toString());
    }

    private void saveTo(Path path) {
        try {
            OutputStream fileOut = Files.newOutputStream(path);
            wb.write(fileOut);
            fileOut.close();
            wb.close();
            U.nout("Experiment is recorded successfully");
        } catch (IOException e) {
            U.nout("FILE WRITE FAIL");
            e.printStackTrace();
        }
    }



    public Row row(int i) {
        Row r = sheet.getRow(i);
        if (r == null) {
            r = sheet.createRow(i);
        }
        return r;
    }
}
