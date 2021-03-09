# Sqlite Wrapper

This project is a basic Sqlite library in Java that uses JDBC.

## Usage

### The Sqlite Wrapper

The Sqlite wrapper is the class that executes all of the queries in the database. Without instatiating this class, you will not be able to use this library to communicate to the database.

```java

public class UsingSqliteWrapper {

	private SqliteWrapper wrapper;
	
	public UsingSqliteWrapper() {
		wrapper = new SqliteWrapper("path/to/sqlite/database.db");
	}

}

```

### Query Objects

Once you have the SqliteWrapper instantiated *(demonstrated above)*, you can pass a Query Object to one of the SqliteWrapper methods: **insert**, **select**, **update**, **delete**.

#### Inserting Data

Inserting data is simple, just use the InsertQuery object to create a query and pass that object to the SqliteWrapper.insert() method.

```java

public class UsingSqliteWrapper {

	...
	
	public void createUser(String name, String email, int age) throws SQLException {
		InsertQuery query = new InsertQuery("users");
		query.addValue("name", name)
			.addValue("email", email)
			.addValue("age", String.valueOf(age));
		int id = wrapper.insert(query);
		System.out.println("The id for user " + name + " is " + id);
	}

}

```

#### Selecting Data

To select data from the database, similarly create a SelectQuery object and use it to build a query. Then pass that object to the SqliteWrapper.select() method. The SqliteWrapper.select() method will return a [ResultSet](https://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html).

```java

public class UsingSqliteWrapper {

	public void getUsers() throws SQLException {
		SelectQuery query = new SelectQuery("users");
		query.field("name")
			.field("email)
			.where("age > 32");
		ResultSet results = wrapper.select(query);
		while (rs.next()) {
			System.out.println(
				"Name: " + res.getString(1) + " " +
				"Email: " + rs.getEmail(2));
		}
	}

}
```

#### Updating Data

To update data that is already in the database, create an UpdateQuery object and use it to build a query. Then pass this object to the SqliteWrapper.update() method.

```java

public class UsingSqliteWrapper {

	...

	public void updateUserEmail(int id, String newEmail) throws SQLException {
		UpdateQuery query = new UpdateQuery("users");
		query.set("email", email)
			.where(String.format("id = %d", id);
		SqliteWrapper.update(query);
	}

}
```

#### Deleting Data

To delete data that is already in the database, create a DeleteQuery object and use it to build a query. Then pass this object to the SqliteWrapper.delete() method.

```java

public class UsingSqliteWrapper {

	...

	public void deleteUser(int id) throws SQLException {
		DeleteQuery query = new DeleteQuery("users");
		query.where(String.format("id = %d", id);
		SqliteWrapper.delete(query);
	}

}
```

## Tools

- [JUnit 5](https://junit.org/junit5/) - A popular unit testing framework for the Java programming language.
- [Sqlite JDBC Driver](https://github.com/xerial/sqlite-jdbc) - A library for accessing and creating SQLite database files in Java.