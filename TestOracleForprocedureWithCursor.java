import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;

public class TestCursor {

	public static void main(String[] args) {
		String sql = "{call mypackage.queryEmpList(?,?)}";
		Connection conn = null;
		CallableStatement call = null;
		ResultSet rs = null;
		conn = JDBCUtils.getConnection();
		try {
			call = conn.prepareCall(sql);
			//赋值
			call.setInt(1, 10);
			call.registerOutParameter(2, OracleTypes.CURSOR);
			//执行
			call.execute();
			//输出
			rs = ((OracleCallableStatement)call).getCursor(2);
			while(rs.next()){
				String name = rs.getString("ename");
				double sal = rs.getDouble("sal");
				System.out.println(name+": "+sal);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.release(conn, call, rs);
		}
	}
}
