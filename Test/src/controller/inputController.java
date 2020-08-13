package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import service.CalendarService;
import service.ICalendarService;
import util.Session;
import vo.CalendarVO;
	/**
	 * 캘린더 일정을 추가하는 클래스
	 * <pre>
	 * <b>History</b>
	 * 	  우다희 , 1.0, 2019.11.11, 최초작성
	 * </pre>
	 * 
	 * @author 우다희
	 * @version 2.0 2019.11.22 소스 수정 완료 
	 */
public class inputController {
	private ICalendarService service;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addDate;

    @FXML
    private TextField txttitle;

    @FXML
    private DatePicker Sdate;

    @FXML
    private DatePicker Edate;

    @FXML
    private TextArea txtCont;

    @FXML
    void addClick(ActionEvent event) {
    	//1. 화면에서 값들 가져오기
    	String title = txttitle.getText();
    	String cont = txtCont.getText();
    	String sdate = Sdate.getValue().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    	String edate = Edate.getValue().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

    	CalendarVO calVo = new CalendarVO();
    	calVo.setCal_conn(cont);
    	calVo.setCal_edate(edate);
    	calVo.setCal_sdate(sdate);
    	calVo.setCal_title(title);
    	calVo.setEmp_id(Session.session.getMem_id()); //지금로그인한 사람의 사원번호
    	
    	service.insertCal(calVo); 
    	
    	Stage thisForm = (Stage) addDate.getScene().getWindow();
    	thisForm.close();
    }
    //alert창
    @FXML
    void initialize() {
    	service = CalendarService.getInstance();
    	
    	Sdate.setValue(LocalDate.now());
    	Edate.setOnAction(e->{
    		if(Sdate.getValue().isAfter(Edate.getValue())) {
    			errorMsg("날짜 입력 오류 !", "종료날짜가 시작날짜보다 빠릅니다.");
    		}
    	});
    }
    public void errorMsg(String head, String msg) {
		Alert info = new Alert(AlertType.ERROR);
		info.setHeaderText(head);
		info.setContentText(msg);
		
		info.showAndWait();
	}
}
