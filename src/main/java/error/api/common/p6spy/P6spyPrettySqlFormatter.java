package error.api.common.p6spy;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.engine.jdbc.internal.FormatStyle;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

/**
 * Project        : PF-Account
 * DATE           : 2022-12-06
 * AUTHOR         : VIRNECT (Jintae Kim)
 * EMAIL          : jtkim@virnect.com
 * DESCRIPTION    :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-12-06      VIRNECT          최초 생성
 */
public class P6spyPrettySqlFormatter implements MessageFormattingStrategy {

	private static final String NEW_LINE = "\n";
	private static final String TAB = "\t";
	private static final String EXECUTE = "Execute";
	private static final String EXECUTION_TIME = "Execution Time ";
	private static final String SEPARATOR = "----------------------------------------------------";

	@Override
	public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
		String trimmedSql = sql.trim();
		if (StringUtils.isEmpty(trimmedSql)) {
			return formatExecute(category);
		}
		return formatSql(trimmedSql, category) + formatPerformanceMetrics(elapsed);
	}

	private String formatExecute(String category) {
		return NEW_LINE
			+ EXECUTE
			+ TAB
			+ category
			+ NEW_LINE
			+ SEPARATOR;
	}

	private String formatSql(String sql, String category) {
		boolean isDDL = isDDLStatement(sql, category);
		CommandType type = isDDL ? CommandType.DDL : CommandType.DML;
		FormatStyle style = isDDL ? FormatStyle.DDL : FormatStyle.BASIC;
		return NEW_LINE
			+ type
			+ style.getFormatter().format(sql);
	}

	private String formatPerformanceMetrics(long elapsed) {
		return NEW_LINE
			+ EXECUTION_TIME
			+ TAB
			+ elapsed
			+ " ms"
			+ NEW_LINE
			+ SEPARATOR;
	}

	private boolean isDDLStatement(String sql, String category) {
		if (!Category.STATEMENT.getName().equals(category)) {
			return false;
		}

		String upperCaseSql = sql.toUpperCase(Locale.ROOT);
		return upperCaseSql.startsWith(SqlStatementType.CREATE.name()) ||
			upperCaseSql.startsWith(SqlStatementType.ALTER.name()) ||
			upperCaseSql.startsWith(SqlStatementType.DROP.name()) ||
			upperCaseSql.startsWith(SqlStatementType.COMMENT.name());
	}
}
