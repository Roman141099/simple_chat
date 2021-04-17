package com.hack.chat.simple_chat;

import com.hack.chat.simple_chat.model.DataSource;
import com.hack.chat.simple_chat.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.time.LocalDateTime;

@SpringBootApplication(scanBasePackages = {"com.hack.chat.simple_chat"}, exclude = DataSourceAutoConfiguration.class)
public class SimpleChatApplication implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(SimpleChatApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        dataSource.createRoom((short) 1);
        dataSource.putMsg((short) 1, new Message(1, "Content 1", LocalDateTime.now(), null, "romagr", true));
        dataSource.putMsg((short) 1, new Message(2, "Content 2", LocalDateTime.now().plusMinutes(1), null, "makred", true));
        dataSource.putMsg((short) 1, new Message(3, "Content 3", LocalDateTime.now().plusMinutes(2), null, "romagr", true));
    }
}
