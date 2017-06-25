package com.ascend.springbootdemo;

import com.ascend.springbootdemo.entities.Author;
import com.ascend.springbootdemo.repositories.AuthorRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
}

@Component
class initCLR implements CommandLineRunner {

    private AuthorRepo authorRepo;

    public initCLR(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public void run(String... strings) throws Exception {
        Author author1 = new Author();
        author1.setFirstName("firstName1");
        author1.setLastName("lastName1");
        Author author2 = new Author();
        author2.setFirstName("firstName2");
        author2.setLastName("lastName2");
        Author author3 = new Author();
        author3.setFirstName("firstName3");
        author3.setLastName("lastName3");
        Author author4 = new Author();
        author4.setFirstName("firstName4");
        author4.setLastName("lastName4");

        authorRepo.save(Arrays.asList(author1, author2, author3, author4));
        authorRepo.findAll().forEach(System.out::println);
    }
}
