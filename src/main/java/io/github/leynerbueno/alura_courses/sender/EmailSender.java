package io.github.leynerbueno.alura_courses.sender;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmailSender {

    public static void send(String recipientEmail, String subject, String body) {
        System.out.println("Simulating sending email to [%s]:\n".formatted(recipientEmail));
        System.out.println("""
                Subject: %s\n 
                Body:\n %s
                """.formatted(subject, body));
    }
}
