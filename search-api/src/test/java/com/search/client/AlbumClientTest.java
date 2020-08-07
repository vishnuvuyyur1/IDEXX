package com.search.client;

import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * Unit Testing the mapper with a stand alone setup using Junit
 * 
 */
public class AlbumClientTest {

	@Test
	public void nullArgument() throws IOException {

		assertThrows(MalformedURLException.class, () -> {
			AlbumClient.albumClient.get(null);
		});
	}

	@Test
	public void noSchemeArgument() throws IOException {
		assertThrows(MalformedURLException.class, () -> {
			AlbumClient.albumClient.get("itunes.apple.com");
		});
	}

	@Test
	public void appleDotCom() throws IOException {
		String itunes = AlbumClient.albumClient.get("https://www.apple.com");
		Assert.assertNotNull(itunes);
		Assert.assertTrue(!itunes.isEmpty());
	}
}
