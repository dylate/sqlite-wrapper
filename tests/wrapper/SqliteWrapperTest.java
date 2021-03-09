package wrapper;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.FileSystems;
import java.sql.ResultSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import query.DeleteQuery;
import query.InsertQuery;
import query.SelectQuery;
import query.UpdateQuery;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SqliteWrapperTest {
	
	SqliteWrapper wrapper;
	int insertedId;

	@BeforeAll
	void setUp() throws Exception {
		String dbPath = FileSystems.getDefault().getPath("./tests/wrapper/test.db").normalize().toAbsolutePath().toString();
		wrapper = new SqliteWrapper(dbPath);
		wrapper.open();
	}

	@AfterAll
	void tearDown() throws Exception {
		wrapper.close();
	}

	@Test
	@Order(1)
	void testInsert() throws Exception {
		InsertQuery query = new InsertQuery("users");
		query.addValue("name", "Morgan Billingsley")
			.addValue("age", "22");
		insertedId = wrapper.insert(query);
		assertNotNull(insertedId);
	}
	
	@Test
	@Order(2)
	void testSelect() throws Exception {
		SelectQuery query = new SelectQuery("users");
		query.field("name").field("age").where(String.format("id = %d", insertedId));
		ResultSet rs = wrapper.select(query);
		if (rs.next()) {
			assertEquals(rs.getString(1), "Morgan Billingsley");
			assertEquals(rs.getInt(2), 22);
		} else {
			fail("The SELECT query could not find any rows that match the criteria.");
		}
	}
	
	@Test
	@Order(3)
	void testUpdate() throws Exception {
		UpdateQuery query = new UpdateQuery("users");
		query.set("name", "John Doe").set("age", "45").where(String.format("id = %d", insertedId));
		wrapper.update(query);
		SelectQuery squery = new SelectQuery("users");
		squery.where(String.format("id = %d", insertedId));
		ResultSet rs = wrapper.select(squery);
		if (rs.next()) {
			assertEquals(rs.getInt(1), insertedId);
			assertEquals(rs.getString(2), "John Doe");
			assertEquals(rs.getInt(3), 45);
		} else {
			fail("The SELECT query could not find the updated row in the database.");
		}
	}
	
	@Test
	@Order(4)
	void testDelete() throws Exception {
		DeleteQuery query = new DeleteQuery("users");
		query.where(String.format("id = %d", insertedId));
		wrapper.delete(query);
		SelectQuery squery = new SelectQuery("users");
		squery.where(String.format("id = %d", insertedId));
		ResultSet rs = wrapper.select(squery);
		if (rs.next()) {
			fail("The delete query failed to delete all of the rows in the users table.");
		} else {
			assertTrue(true);
		}
	}

}
