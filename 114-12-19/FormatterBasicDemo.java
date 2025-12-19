import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class FormatterBasicDemo {
    public static void main(String[] args) {
        File input = new File("scores.txt");
        File output = new File("outputScoores.txt");

        // 先讀取並放入 List<Student>
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String name = null;
                int score = -1;
                if (line.contains(",")) {
                    String[] parts = line.split(",");
                    name = parts[0].trim();
                    try { score = Integer.parseInt(parts[1].trim()); } catch (NumberFormatException e) { score = -1; }
                } else {
                    String[] parts = line.split("\\s+");
                    if (parts.length >= 2) {
                        name = parts[0].trim();
                        try { score = Integer.parseInt(parts[1].trim()); } catch (NumberFormatException e) { score = -1; }
                    }
                }

                if (name != null && score >= 0) {
                    Student s = new Student(name, score, scoreToGrade(score));
                    students.add(s);
                }
            }
        } catch (IOException e) {
            System.out.println("讀取檔案時發生錯誤: " + e.getMessage());
            return;
        }

        // 再以 Formatter 寫入
        try (Formatter formatter = new Formatter(output)) {
            formatter.format("=== 學生成績單 ===%n");
            formatter.format("%n");
            formatter.format("%-10s %5s %8s%n", "姓名", "分數", "等級");

            for (Student s : students) {
                formatter.format("%-10s %5d %8s%n", s.name, s.score, s.grade);
            }

            System.out.println("已寫入: " + output.getName());

        } catch (FileNotFoundException e) {
            System.out.println("無法建立或找到檔案: " + e.getMessage());
        }
    }

    // Student 類別
    static class Student {
        String name;
        int score;
        String grade;

        Student(String name, int score, String grade) {
            this.name = name;
            this.score = score;
            this.grade = grade;
        }
    }

    private static String scoreToGrade(int score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        if (score >= 0)  return "F";
        return "?";
    }
}
