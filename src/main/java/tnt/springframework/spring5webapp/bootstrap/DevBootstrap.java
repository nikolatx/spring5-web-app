package tnt.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import tnt.springframework.spring5webapp.model.Author;
import tnt.springframework.spring5webapp.model.Book;
import tnt.springframework.spring5webapp.repositories.AuthorRepository;
import tnt.springframework.spring5webapp.repositories.BookRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    private void initData() {
        Author eric = new Author("Eric", "Evans");
        Book book1 = new Book("Domain Driven Design", "1234", "Harper Collins");
        eric.getBooks().add(book1);
        book1.getAuthors().add(eric);
        authorRepository.save(eric);
        bookRepository.save(book1);

        Author rod=new Author("Rod", "Johnson");
        Book noEJB=new Book("J2EE Development without EJB", "2344", "Wrox");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
