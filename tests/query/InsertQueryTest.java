package query;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InsertQueryTest {
	InsertQuery query;

	@BeforeEach
	void setUp() throws Exception {
		query = new InsertQuery("users");
	}

	@Test
	void test() {
		query
			.addValue("name", "John Doe")
			.addValue("age", "45");
		assertEquals("INSERT INTO users (name, age) VALUES ('John Doe', 45);", query.getSql());
	}

}
