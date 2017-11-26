import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleTypes;

public class TestOracle {
/*
 * 测试调用存储过程
 * create or replace procedure queryEmpInformation(eno in number,
                                                pename out varchar2,
                                                psal   out number,
                                                pjob   out varchar2
                                                )
 */
public static void main(String[] args) {
	
	String sql = "{call queryEmpInformation(?,?,?,?)}";
	CallableStatement call = null;
	Connection conn = null;
	try {
		conn = JDBCUtils.getConnection();
		call = conn.prepareCall(sql);
		//对于输入参数，赋值
		call.setInt(1, 7839);
		//对于out参数，声明
		call.registerOutParameter(2, OracleTypes.VARCHAR);
		call.registerOutParameter(3, OracleTypes.NUMBER);
		call.registerOutParameter(4, OracleTypes.VARCHAR);
		//执行调用
		call.execute();
		
		//输出
		String name = call.getString(2);
		double sal = call.getDouble(3);
		String job = call.getString(4);
		System.out.println(name+":"+sal+":"+job);
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		JDBCUtils.release(conn, call, null);
	}
}
}
