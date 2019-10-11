package com.javarush.task.task25.task2505;

/* 
Без дураков
*/

/*
1. Создай private class MyUncaughtExceptionHandler, который на перехват исключения должен подождать половину секунды,
а затем вывести на экран secretKey, имя трэда и сообщение возникшего исключения.
2. Разберись в последовательности выполняемого кода и обеспечь логирование возникновения исключения в п.1.
3. Метод main в тестировании не участвует.
*/

public class Solution {

    public static void main(String[] args) {
        MyThread myThread = new Solution().new MyThread("super secret key");
        myThread.start();
    }

    public class MyThread extends Thread {
        private String secretKey;

        public MyThread(String secretKey) {
            this.secretKey = secretKey;
            setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            setDaemon(false);
        }

        @Override
        public void run() {
            try {
                throw new NullPointerException("it's an example");
            } catch (NullPointerException e) {
                getUncaughtExceptionHandler().uncaughtException(currentThread(), e);
            }
        }

        private class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

            public MyUncaughtExceptionHandler() {
            }

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {}
                System.out.println(String.format("%s, %s, %s", secretKey, t.getName(), e.getMessage()));
            }
        }
    }
}
