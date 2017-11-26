import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleTypes;

/*
 * create or replace function queryEmpIncome(eno in number)
 *return  number
 */
public class TestOracleFunc {
	public static void main(String[] args) {
		String sql = "{?=call queryEmpIncome(?)}";
		Connection conn = null;
		CallableStatement call = null;
		conn = JDBCUtils.getConnection();
		try {
			call = conn.prepareCall(sql);
			call.registerOutParameter(1, OracleTypes.NUMBER);
			call.setInt(2, 7839);
			//执行
			call.execute();
			//输出
			double income = call.getDouble(1);
			
			System.out.println("年收入："+income);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
