package github.com._mk.services;

import github.com._mk.data.dto.v1.BookDTO;
import github.com._mk.exception.RequiredObjectIsNullException;
import github.com._mk.model.Book;
import github.com._mk.repository.BookRepository;
import github.com._mk.unittests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

    MockBook input;

    @Mock
    BookRepository repository;

    @InjectMocks
    private BookServices services;

    @BeforeEach
    void setUp() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void create() {
        Book book = input.mockEntity(1);
        book.setId(1L);
        BookDTO dto = input.mockDTO(1);

        when(repository.save(book)).thenReturn(book);
        var result = services.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findById")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("POST")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("PUT")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("DELETE")));

        assertEquals("Author Test1", result.getAuthor());
        assertNotNull(result.getLaunch_date());
        assertEquals(25D, result.getPrice());
        assertEquals("Title Test1", result.getTitle());
    }

    @Test
    void TestCreateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> services.create(null));
        String expectedString = "NÃO É PERMITIDO PERSISTIR UM OBJETO NULO!";
        String actualString = exception.getMessage();

        assertTrue(actualString.contains(expectedString));
    }

    @Test
    void update() {
        Book book = input.mockEntity(1);
        book.setId(1L);
        BookDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(book));
        when(repository.save(book)).thenReturn(book);
        var result = services.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findById")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("POST")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("PUT")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("DELETE")));

        assertEquals("Author Test1", result.getAuthor());
        assertNotNull(result.getLaunch_date());
        assertEquals(25D, result.getPrice());
        assertEquals("Title Test1", result.getTitle());
    }

    @Test
    void TestUpdateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> services.update(null));
        String expectedString = "NÃO É PERMITIDO PERSISTIR UM OBJETO NULO!";
        String actualString = exception.getMessage();

        assertTrue(actualString.contains(expectedString));
    }

    @Test
    void delete() {
        Book book = input.mockEntity(1);
        book.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(book));
        services.delete(1L);

        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Book.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findById() {
        Book book = input.mockEntity(1);
        book.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(book));
        var result = services.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findById")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("POST")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("PUT")));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("DELETE")));

        assertEquals("Author Test1", result.getAuthor());
        assertNotNull(result.getLaunch_date());
        assertEquals(25D, result.getPrice());
        assertEquals("Title Test1", result.getTitle());

    }

    @Test
    void findAll() {
        List<Book> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<BookDTO> people = services.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var bookOne = people.get(1);

        assertNotNull(bookOne);
        assertNotNull(bookOne.getId());
        assertNotNull(bookOne.getLinks());

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findById")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("GET")));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("POST")));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("GET")));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("PUT")));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("DELETE")));

        assertEquals("Author Test1", bookOne.getAuthor());
        assertNotNull(bookOne.getLaunch_date());
        assertEquals(25D, bookOne.getPrice());
        assertEquals("Title Test1", bookOne.getTitle());

        var bookFour = people.get(4);

        assertNotNull(bookFour);
        assertNotNull(bookFour.getId());
        assertNotNull(bookFour.getLinks());

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findById")
                        && link.getHref().endsWith("/api/book/v1/4")
                        && link.getType().equals("GET")));

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("POST")));

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("GET")));

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("PUT")));

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/book/v1/4")
                        && link.getType().equals("DELETE")));

        assertEquals("Author Test4", bookFour.getAuthor());
        assertNotNull(bookFour.getLaunch_date());
        assertEquals(25D, bookFour.getPrice());
        assertEquals("Title Test4", bookFour.getTitle());

        var bookSeven = people.get(7);

        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getId());
        assertNotNull(bookSeven.getLinks());

        assertNotNull(bookSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findById")
                        && link.getHref().endsWith("/api/book/v1/7")
                        && link.getType().equals("GET")));

        assertNotNull(bookSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("POST")));

        assertNotNull(bookSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("GET")));

        assertNotNull(bookSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("PUT")));

        assertNotNull(bookSeven.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/book/v1/7")
                        && link.getType().equals("DELETE")));

        assertEquals("Author Test7", bookSeven.getAuthor());
        assertNotNull(bookSeven.getLaunch_date());
        assertEquals(25D, bookSeven.getPrice());
        assertEquals("Title Test7", bookSeven.getTitle());

    }
}