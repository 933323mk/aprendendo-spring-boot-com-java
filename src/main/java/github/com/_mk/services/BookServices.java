package github.com._mk.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import github.com._mk.model.Book;
import github.com._mk.data.dto.v1.BookDTO;
import org.springframework.stereotype.Service;
import github.com._mk.repository.BookRepository;
import github.com._mk.controllers.BookController;
import github.com._mk.exception.RequiredObjectIsNullException;
import static github.com._mk.mapper.ObjectMapper.parseObject;
import org.springframework.beans.factory.annotation.Autowired;
import static github.com._mk.mapper.ObjectMapper.parseListObjects;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.List;


@Service
public class BookServices {

    private final Logger logger = LoggerFactory.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;


    public BookDTO create(BookDTO book){
        if(book == null) throw new RequiredObjectIsNullException();
        logger.info("CREATING A BOOK!");
        var entity = parseObject(book, Book.class);
        var dto = parseObject(repository.save(entity), BookDTO.class);
        return book;
    }

    public BookDTO update(BookDTO book){
        if(book == null) throw new RequiredObjectIsNullException();
        logger.info("UPDATING A BOOK!");
        Book entity = repository.findById(book.getId())
                    .orElseThrow(() -> new RequiredObjectIsNullException("NOT RECORD FOUND FOR THIS ID!"));
        entity.setAuthor(book.getAuthor());
        entity.setLaunch_date(book.getLaunch_date());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        var dto = parseObject(repository.save(entity), Book.class);
        return book;
    }

    public void delete(Long id){
        logger.info("DELETING A BOOK!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new RequiredObjectIsNullException("NOT FOUND RECORD FOR THIS ID!"));
        repository.delete(entity);
    }

    public BookDTO findById(Long id){
        logger.info("FINDING A BOOK!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new RequiredObjectIsNullException("NOT FOUND RECORD FOR THIS ID!"));
        var dto = parseObject(entity, BookDTO.class);
        linksHATEOAS(dto);
        return dto;
    }

    public List<BookDTO> findAll(){
        logger.info("FINDING ALL BOOKS!");
        var book = parseListObjects(repository.findAll(), BookDTO.class);
        book.forEach(this::linksHATEOAS);
        return book;
    }

    private void linksHATEOAS(BookDTO dto){
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("Create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("Update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("Delete").withType("DELETE"));
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withRel("findById").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
    }
}
