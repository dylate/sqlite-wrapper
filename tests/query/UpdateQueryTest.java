package query;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateQueryTest {
	UpdateQuery query;

	@BeforeEach
	void setUp() throws Exception {
		query = new UpdateQuery("users");
	}

	@Test
	void test() {
		query.set("name", "Jane Doe").set("age", "40").where("name = 'Morgan Billingsley'");
		assertEquals("UPDATE users SET name = 'Jane Doe', age = 40 WHERE name = 'Morgan Billingsley';", query.getSql());
	}

}
