package query;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import query.utils.SqlFormatter;

public class UpdateQuery extends SearchQuery {
	private String table;
	private Map<String,String> updates;
	
	public UpdateQuery(String table) {
		this.table = table;
		this.updates = new HashMap<String,String>();
	}
	
	public UpdateQuery set(String field, String value) {
		updates.put(field, value);
		this.sql = String.format("UPDATE %s SET %s", table, getUpdatesString());
		return this;
	}
	
	private String getUpdatesString() {
		String[] updateStrings = new String[updates.size()];
		int i = 0;
		for (Entry<String,String> entry : updates.entrySet()) {
			updateStrings[i] = String.format(
					"%s = %s",
					entry.getKey(),
					SqlFormatter.value(entry.getValue()));
			i++;
		}
		return String.join(", ", updateStrings);
	}
}
