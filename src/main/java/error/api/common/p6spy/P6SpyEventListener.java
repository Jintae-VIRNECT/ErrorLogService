package error.api.common.p6spy;

import java.sql.SQLException;

import com.p6spy.engine.common.ConnectionInformation;
import com.p6spy.engine.event.JdbcEventListener;
import com.p6spy.engine.spy.P6SpyOptions;

/**
 * Project        : PF-Workspace
 * DATE           : 2024-04-24
 * AUTHOR         : jtkim (Jintae kim)
 * EMAIL          : jtkim@virnect.com
 * DESCRIPTION    :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-24      jtkim          최초 생성
 */
public class P6SpyEventListener extends JdbcEventListener {
	@Override
	public void onAfterGetConnection(ConnectionInformation connectionInformation, SQLException e) {
		P6SpyOptions.getActiveInstance().setLogMessageFormat(P6spyPrettySqlFormatter.class.getName());
	}
}
