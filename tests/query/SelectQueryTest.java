package query;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SelectQueryTest {
	SelectQuery query;
	
	@BeforeEach
	void setUp() {
		query = new SelectQuery("users");
	}

	@Test
	void test() {
		query
			.field("name")
			.field("age")
			.where("name = 'Morgan Billingsley'")
			.andWhere("age = 22");
		assertEquals("SELECT name, age FROM users WHERE name = 'Morgan Billingsley' AND age = 22;", query.getSql());
	}

}
