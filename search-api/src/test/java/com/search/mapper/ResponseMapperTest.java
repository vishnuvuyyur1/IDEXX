package com.search.mapper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.search.model.ApiResponse;
import com.search.model.BookResponse;
import com.search.model.Item;
import com.search.model.VolumeInfo;

/**
 * Unit Testing the mapper with a stand alone setup using Mockito
 * 
 */
@ExtendWith(MockitoExtension.class)
public class ResponseMapperTest {

	@InjectMocks
	private ResponseMapper responseMapper;

	@Test
	void mapFromBookResponseTest() {
		BookResponse bookResp = mockBookResponse();
		List<ApiResponse> output = responseMapper.mapFromBookResponse(bookResp);
		assertEquals(1, output.size());
		assertEquals("Book Title", output.get(0).getTitle());
	}

	private BookResponse mockBookResponse() {
		BookResponse bookResp = new BookResponse();
		VolumeInfo volumeInfo = new VolumeInfo();
		volumeInfo.setPrintType("BOOK");
		volumeInfo.setTitle("Book Title");
		Item item = new Item();
		item.setVolumeInfo(volumeInfo);
		List<Item> items = new ArrayList<>();
		items.add(item);
		bookResp.setItems(items);
		return bookResp;
	}

}
