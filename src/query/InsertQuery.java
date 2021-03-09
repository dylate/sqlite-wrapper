package query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import query.utils.SqlFormatter;

public class InsertQuery extends Query {
	private String table;
	private Map<String, String> values;
	
	public InsertQuery(String table) {
		this.table = table;
		this.values = new HashMap<String,String>();
	}
	
	public InsertQuery addValue(String field, String value) {
		values.put(field, value);
		return this;
	}
	
	private String getFields() {
		return String.join(", ", values.keySet());
	}
	
	private String getValues() {
		List<String> stringValues = new ArrayList<String>();
		for (String value : values.values()) {
			String sqlValue = SqlFormatter.value(value);
			stringValues.add(sqlValue);
		}
		return String.join(", ", stringValues);
	}
	
	@Override
	public String getSql() {
		return String.format(
				"INSERT INTO %s (%s) VALUES (%s);",
				this.table,
				this.getFields(),
				this.getValues());
	}

}
