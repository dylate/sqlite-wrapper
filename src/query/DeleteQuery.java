package query;

public class DeleteQuery extends SearchQuery {
	public DeleteQuery(String table) {
		this.sql = String.format("DELETE FROM %s", table);
	}
}
