package query;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeleteQueryTest {
	DeleteQuery query;

	@BeforeEach
	void setUp() throws Exception {
		query = new DeleteQuery("users");
	}

	@Test
	void test() {
		query.where("name = 'Morgan Billingsley'");
		assertEquals("DELETE FROM users WHERE name = 'Morgan Billingsley';", query.getSql());
	}

}
