package github.com._mk.services;

import github.com._mk.controllers.BookController;
import github.com._mk.data.dto.v1.BookDTO;
import github.com._mk.exception.RequiredObjectIsNullException;
import github.com._mk.exception.ResourceNotFoundException;
import github.com._mk.model.Book;
import github.com._mk.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static github.com._mk.mapper.ObjectMapper.parseListObjects;
import static github.com._mk.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class BookServices {

    private final Logger logger = LoggerFactory.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;

    //@Autowired
    //BookMapper converter;

    public BookDTO create(BookDTO Book) {
        if(Book == null) throw new RequiredObjectIsNullException();

        logger.info("CREATING ONE Book!");
        var entity = parseObject(Book, Book.class);
        var dto = parseObject(repository.save(entity), BookDTO.class);
        linksHateoas(dto);
        return dto;
    }

//    public BookDTOV2 createV2(BookDTOV2 Book) {
//        if(Book == null) throw new RequiredObjectIsNullException();
//        logger.info("CREATING ONE Book V2!");
//        var entity = converter.convertDTOToEntity(Book);
//
//        return converter.convertEntityToDTO(repository.save(entity));
//    }

    public BookDTO update(BookDTO Book) {
        if(Book == null) throw new RequiredObjectIsNullException();
        logger.info("UPDATING ONE Book!");
        Book entity = repository.findById(Book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("NOT RECORDS FOUND FOR THIS ID"));

        entity.setAuthor(Book.getAuthor());
        entity.setLaunch_date(Book.getLaunch_date());
        entity.setPrice(Book.getPrice());
        entity.setId(Book.getId());
        entity.setTitle(Book.getTitle());

        var dto = parseObject(repository.save(entity), BookDTO.class);
        linksHateoas(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("DELETING ONE Book!");

        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NOT RECORDS FOUND FOR THIS ID"));
        repository.delete(entity);
    }

    public List<BookDTO> findAll() {
        logger.info("FINDING ALL books!");

        var Books = parseListObjects(repository.findAll(), BookDTO.class);
        Books.forEach(this::linksHateoas);
        return Books;
    }

    public BookDTO findById(Long id) {
        logger.info("FINDING ONE Book!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NOT RECORDS FOUND FOR THIS ID"));
        var dto = parseObject(entity, BookDTO.class);
        linksHateoas(dto);
        return dto;

    }

    private void linksHateoas(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withRel("findById").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));

    }
}
