package com.epam.testapp.database.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetExtractor<T> {
	T extractData(ResultSet resultSet) throws SQLException;
}
