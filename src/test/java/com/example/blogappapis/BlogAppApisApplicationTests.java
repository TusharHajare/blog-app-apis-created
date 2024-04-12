package com.example.blogappapis;

import com.example.blogappapis.repositories.userRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApisApplicationTests {

	@Autowired
	private userRepo userRepos;

	@Test
	void contextLoads() {
	}

	@Test
	public void repoTest()
	{
		String className = this.userRepos.getClass().getName();
		String packageName = this.userRepos.getClass().getPackageName();
		System.out.println(className);
		System.out.println(packageName);
	}


}
