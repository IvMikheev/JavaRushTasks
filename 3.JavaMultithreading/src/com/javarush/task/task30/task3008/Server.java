package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try (ServerSocket srvSocket = new ServerSocket(ConsoleHelper.readInt())) {
            ConsoleHelper.writeMessage("Server started!");
            while (true) {
                new Handler(srvSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry map : connectionMap.entrySet()) {
            Connection connection = (Connection) map.getValue();
            try {
                connection.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Unable to send message!");
            }
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String userName = "";
            ConsoleHelper.writeMessage(socket.getRemoteSocketAddress().toString());
            try {
                Connection connection = new Connection(socket);
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("An error occurred while trying to connect to the remote address. Try again.");
                try {
                    socket.close();
                    ConsoleHelper.writeMessage("Connection to the remote server is closed!");
                } catch (IOException ioEx) {
                    ConsoleHelper.writeMessage("Unable to close the connection!");
                }
            }
            if (!userName.isEmpty()) {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            }
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Message request = new Message(MessageType.NAME_REQUEST);
            Message response;
            while (true) {
                connection.send(request);
                response = connection.receive();
                String name = response.getData();
                if (response.getType() == MessageType.USER_NAME && !name.isEmpty() && !connectionMap.containsKey(name)) {
                    connectionMap.put(name, connection);
                    connection.send(new Message(MessageType.NAME_ACCEPTED));
                    return response.getData();
                }
            }
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry map : connectionMap.entrySet()) {
                String clientName = (String) map.getKey();
                if (!clientName.equals(userName)) {
                    Message message = new Message(MessageType.USER_ADDED, userName);
                    connection.send(message);
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message response = connection.receive();
                if (response.getType() == MessageType.TEXT) {
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + response.getData()));
                } else ConsoleHelper.writeMessage("Wrong message type!");
            }
        }
    }
}
