package tnt.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import tnt.springframework.spring5webapp.model.Author;
import tnt.springframework.spring5webapp.model.Book;
import tnt.springframework.spring5webapp.model.Publisher;
import tnt.springframework.spring5webapp.repositories.AuthorRepository;
import tnt.springframework.spring5webapp.repositories.BookRepository;
import tnt.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {
        Publisher hc=new Publisher("Harper Collins", "HC address");
        publisherRepository.save(hc);
        Author eric = new Author("Eric", "Evans");
        Book book1 = new Book("Domain Driven Design", "1234", hc);
        eric.getBooks().add(book1);
        book1.getAuthors().add(eric);
        hc.setBook(book1);
        authorRepository.save(eric);
        bookRepository.save(book1);
        publisherRepository.save(hc);

        Publisher wrox=new Publisher("Wrox", "Wrox address");
        publisherRepository.save(wrox);
        Author rod=new Author("Rod", "Johnson");
        Book noEJB=new Book("J2EE Development without EJB", "2344", wrox);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        wrox.setBook(noEJB);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(wrox);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
