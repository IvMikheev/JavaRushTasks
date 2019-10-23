package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {

    public static void main(String[] args) {
        new BotClient().run();
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    public class BotSocketThread extends SocketThread {

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Hello. I'm a bot. I understand the commands: date, day, month, year, time, hours, minutes, seconds.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            SimpleDateFormat df = new SimpleDateFormat();
            Calendar calendar = Calendar.getInstance();
            if (message.contains(":")) {
                String[] data = message.split(": ");
                if (data.length > 1) {
                    String userName = data[0] + ": ";
                    String messageText = data[1];
                    switch (messageText) {
                        case "date":
                            df.applyPattern("d.MM.YYYY");
                            sendTextMessage("Information for " + userName + df.format(calendar.getTime()));
                            break;
                        case "day":
                            df.applyPattern("d");
                            sendTextMessage("Information for " + userName + df.format(calendar.getTime()));
                            break;
                        case "month":
                            df.applyPattern("MMMM");
                            sendTextMessage("Information for " + userName + df.format(calendar.getTime()));
                            break;
                        case "year":
                            df.applyPattern("YYYY");
                            sendTextMessage("Information for " + userName + df.format(calendar.getTime()));
                            break;
                        case "time":
                            df.applyPattern("H:mm:ss");
                            sendTextMessage("Information for " + userName + df.format(calendar.getTime()));
                            break;
                        case "hours":
                            df.applyPattern("H");
                            sendTextMessage("Information for " + userName + df.format(calendar.getTime()));
                            break;
                        case "minutes":
                            df.applyPattern("m");
                            sendTextMessage("Information for " + userName + df.format(calendar.getTime()));
                            break;
                        case "seconds":
                            df.applyPattern("s");
                            sendTextMessage("Information for " + userName + df.format(calendar.getTime()));
                            break;
                    }
                }
            }
        }
    }
}
