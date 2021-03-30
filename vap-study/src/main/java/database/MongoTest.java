package database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

/**
 * @program: van
 * @description:
 * @author: 遥远
 * @create: 2020-05-19 17:04
 */
public class MongoTest {

    public static void main(String[] args) {

        String jdbcUrl = "172.16.8.89:27017/admin";
        String jdbc = jdbcUrl.split("/")[0];
        String[] hostAndPorts = jdbc.split(":");
        ServerAddress serverAddress = new ServerAddress(hostAndPorts[0], Integer.valueOf(hostAndPorts[1]));

        MongoClientOptions options = MongoClientOptions.builder().build();

        MongoClient mongoClient = new MongoClient(serverAddress, options);
        mongoClient.getDatabase("test");
    }
}
