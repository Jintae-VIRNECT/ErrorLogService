package error.api.common.p6spy;

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
public enum CommandType {
	DDL("Execute DDL :"),
	DML("Execute DML :");

	private final String label;

	CommandType(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return label;
	}
}
