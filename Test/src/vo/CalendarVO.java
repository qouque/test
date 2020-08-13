package vo;

public class CalendarVO {

	private String Cal_conn;
	private String Cal_edate;
	private String Cal_sdate;
	private String Cal_title;
	private int cal_num;
	
	
	public int getCal_num() {
		return cal_num;
	}
	public void setCal_num(int cal_num) {
		this.cal_num = cal_num;
	}
	private int Emp_id;
	public String getCal_conn() {
		return Cal_conn;
	}
	public void setCal_conn(String cal_conn) {
		Cal_conn = cal_conn;
	}
	public String getCal_edate() {
		return Cal_edate;
	}
	public void setCal_edate(String cal_edate) {
		Cal_edate = cal_edate;
	}
	public String getCal_sdate() {
		return Cal_sdate;
	}
	public void setCal_sdate(String cal_sdate) {
		Cal_sdate = cal_sdate;
	}
	public String getCal_title() {
		return Cal_title;
	}
	public void setCal_title(String cal_title) {
		Cal_title = cal_title;
	}
	public int getEmp_id() {
		return Emp_id;
	}
	public void setEmp_id(int emp_id) {
		Emp_id = emp_id;
	}
	
	
}
