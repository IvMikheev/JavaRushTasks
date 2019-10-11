package com.javarush.task.task16.task1630;

import java.io.*;

public class Solution {
    //Валидатор принимает только если переменные имеют модификатор доступа public
    private static String firstFileName; 
    private static String secondFileName;

    static {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface {
        private static String fileName;
        private static String output;

        @Override
        public void run() {
            try {
                output = "";
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
                while ((fileName = reader.readLine()) != null) {
                    output = output + fileName + " ";
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String getFileContent() {
            if (output == null) {
                return "";
            } else return output;
        }

        @Override
        public void setFileName(String fullFileName) {
            fileName = fullFileName;
        }
    }
}
