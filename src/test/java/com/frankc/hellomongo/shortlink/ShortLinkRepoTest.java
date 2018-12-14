package com.frankc.hellomongo.shortlink;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShortLinkRepoTest {

	@Autowired
	private ShortLinkRepo shortLinkRepo;

	@Test
	public void ShortLinkRepoAdd() {
		String id = "1234";

		shortLinkRepo.add(new ShortLink(id, "testSrcPath"));
		assertNotNull("ShortLinkRepo should return object just added",
				      shortLinkRepo.get(id));
	}

}
