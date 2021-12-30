package database;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLUnionQuery;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlASTVisitorAdapter;
import com.alibaba.druid.util.JdbcConstants;

import java.util.List;
import java.util.Map;

/**
 * @program: van
 * @description:
 * @author: 遥远
 * @create: 2020-06-23 11:46
 */
public class DruidTest {

    public static void main(String[] args) {
        String dbType = JdbcConstants.MYSQL.toString(); // JdbcConstants.MYSQL或者JdbcConstants.POSTGRESQL
        //        String sql = "select * from mytable a where a.id = 3";
        String sql = "SELECT name from da_api UNION SELECT user_name from da_user";
        String sql2 =
            "SELECT a.proj_no,b.biz_cust_count+nvl (b.td_biz_cust,0) AS biz_cust_count,a.quoto_cust_count+nv1 (b" 
                + ".td_quot_cust,0) AS quoto_cust_count,a.detail_cust_count,a.credit_quota+nv1 (b.td_quot_amt,0) AS " 
                + "credit_quota,a.encash_amt,a.loan_amt,a.ovd_loan_amt,a.ovd_amt,a.cde_amt,a.yql,a.bll FROM (\n"
                +
                "SELECT t.proj_no,t.biz_cust_count,t.quoto_cust_count,t.detail_cust_count,t.credit_quota,t" 
                + ".encash_amt,t.loan_amt,t.ovd_loan_amt,t.ovd_amt,t.cde_amt,t.ovd_loan_amt/t.loan_amt AS yql,t" 
                + ".cde_amt/t.loan_amt AS bll FROM (\n"
                +
                "SELECT proj_no,biz_cust_count,quoto_cust_count,detail_cust_count,credit_quota,encash_amt," 
                + "stock_prin_bal+stock_ovd_prin_bal AS loan_amt,overdue_prin_bal+overdue_ovd_prin_bai AS " 
                + "ovd_loan_amt,overdue_ovd_prin_bal+overdue_ovd_int_ba1 AS ovd_amt," 
                + "fiveclass_cde_prin_bal+fiveclass_cde_ovd_prin_bal AS cde_amt FROM net_dw_yw_day_all WHERE date_add" 
                + "(cdate,1)-to_date (now())) t) a LEFT JOIN (\n"
                +
                "SELECT project_no,td_biz_cust,td_quot_cust,td_quot_amt FROM (\n" +
                "SELECT a.proj_no AS project_no,td_biz_cust,td_quot_cust,td_quot_amt FROM (\n" +
                "SELECT proj_no,COUNT(DISTINCT cust_id) AS td_biz_cust FROM hzfh_pri_net_stream_abs_biz_info WHERE " 
                + "create_date>=to_date (now()) GROUP BY proj_no) a FULL JOIN (\n"
                +
                "SELECT proj_no,COUNT(DISTINCT credit_no) AS td_quot_cust,SUM(awark_quota)/100 AS td_quot_amt FROM (\n"
                +
                "SELECT credit_no,proj_no,MAX(awark_quota) AS awark_quota FROM net_stream_abs_loan_quoto WHERE concat" 
                + "(is_admin,is_agree,platform_access)='YYY' AND create_date>=to_date (now()) GROUP BY credit_no," 
                + "proj_no) t GROUP BY proj_no) b ON a.proj_no=b.proj_no UNION \n"
                +
                "SELECT a.project_no AS project_no,td_biz_cust,td_quot_cust,td_quot_amt FROM (\n" +
                "SELECT trim(project_no) AS project_no,COURT (cust_no) AS td_biz_cust FROM " 
                + "net_stream_icp_biz_info_base WHERE create_date>=to_date (now()) GROUP BY project_no) a FULL JOIN (\n"
                +
                "SELECT project_no,COUNT(DISTINCT cust_NO) AS td_quot_cust,SUM(credlt.quota)/100 AS td_quot_amt FROM " 
                + "(\n"
                +
                "SELECT cust_no,trim(project_no) AS project_no,MAX(credit_quota) AS credit_quota FROM " 
                + "net_stream_icp_accounting_quota_info WHERE quot_status=1 AND create_date>=to_date (now()) GROUP BY" 
                + " cust_no,project_no) t GROUP BY project_no) b ON a.project_no=b.project_no) tmp) b ON a.proj_no=b" 
                + ".project_no";
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);
        ExportTableAliasVisitor visitor = new ExportTableAliasVisitor();
        SQLSelectStatement sqlStatement = (SQLSelectStatement)stmtList.get(0);
        //        sqlStatement.accept(visitor);
        SQLUnionQuery sqlUnionQuery = (SQLUnionQuery)sqlStatement.getSelect().getQuery();
        System.out.println(sqlUnionQuery.getLeft());
        System.out.println(sqlUnionQuery.getRight());
        for (SQLSelectItem sqlSelectItem : sqlStatement.getSelect().getQueryBlock().getSelectList()) {
            System.out.println(sqlSelectItem.toString());
        }
    }

    public static class ExportTableAliasVisitor extends MySqlASTVisitorAdapter {

        private Map<String, String> aliasMap;

        private List<String> list;

        public boolean visit(SQLSelectItem x) {
            System.out.println(x.toString());
            return true;
        }

        public Map<String, String> getAliasMap() {
            return aliasMap;
        }

        public List<String> getList() {
            return list;
        }
    }
}
