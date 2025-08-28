package github.com._mk.unittests.mapper;

import github.com._mk.data.dto.BookDTO;
import github.com._mk.model.Book;
import github.com._mk.unittests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static github.com._mk.mapper.ObjectMapper.parseListObjects;
import static github.com._mk.mapper.ObjectMapper.parseObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ObjectBookMapperTests {
	MockBook inputObject;

	@BeforeEach
	public void setUp() {
		inputObject = new MockBook();
	}

	@Test
	public void parseEntityToDTOTest() {
		BookDTO output = parseObject(inputObject.mockEntity(), BookDTO.class);
		assertEquals(Long.valueOf(0L), output.getId());
		assertEquals("Some Author0", output.getAuthor());
		assertNotNull(output.getLaunchDate());
		assertEquals(25D, output.getPrice());
		assertEquals("Some Title0", output.getTitle());
	}

	@Test
	public void parseEntityListToDTOListTest() {
		List<BookDTO> outputList = parseListObjects(inputObject.mockEntityList(), BookDTO.class);
		BookDTO outputZero = outputList.getFirst();

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Some Author0", outputZero.getAuthor());
        assertNotNull(outputZero.getLaunchDate());
        assertEquals(25D, outputZero.getPrice());
        assertEquals("Some Title0", outputZero.getTitle());

		BookDTO outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Some Author7", outputSeven.getAuthor());
        assertNotNull(outputSeven.getLaunchDate());
        assertEquals(25D, outputSeven.getPrice());
        assertEquals("Some Title7", outputSeven.getTitle());

		BookDTO outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Some Author12", outputTwelve.getAuthor());
        assertNotNull(outputTwelve.getLaunchDate());
        assertEquals(25D, outputTwelve.getPrice());
        assertEquals("Some Title12", outputTwelve.getTitle());
	}

	@Test
	public void parseDTOToEntityTest() {
		Book output = parseObject(inputObject.mockDTO(), Book.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Some Author0", output.getAuthor());
        assertNotNull(output.getLaunchDate());
        assertEquals(25D, output.getPrice());
        assertEquals("Some Title0", output.getTitle());
	}

	@Test
	public void parserDTOListToEntityListTest() {
		List<Book> outputList = parseListObjects(inputObject.mockDTOList(), Book.class);
		Book outputZero = outputList.getFirst();

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Some Author0", outputZero.getAuthor());
        assertNotNull(outputZero.getLaunchDate());
        assertEquals(25D, outputZero.getPrice());
        assertEquals("Some Title0", outputZero.getTitle());

		Book outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Some Author7", outputSeven.getAuthor());
        assertNotNull(outputSeven.getLaunchDate());
        assertEquals(25D, outputSeven.getPrice());
        assertEquals("Some Title7", outputSeven.getTitle());

		Book outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Some Author12", outputTwelve.getAuthor());
        assertNotNull(outputTwelve.getLaunchDate());
        assertEquals(25D, outputTwelve.getPrice());
        assertEquals("Some Title12", outputTwelve.getTitle());
	}
}