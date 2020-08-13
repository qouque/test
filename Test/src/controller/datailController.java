package controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.CalendarService;
import service.ICalendarService;
import util.Session;
import vo.CalendarVO;
	/**
	 * 캘린더의 특정 날짜를 클릭했을 때 세부사항을 출력해주는 클래스
	 * <pre>
	 * <b>History</b>
	 * 	  우다희 , 1.0, 2019.11.11, 최초작성
	 * </pre>
	 * 
	 * @author 우다희
	 * @version 2.0 2019.11.22 소스 수정 완료 
	 */
public class datailController {
	private List<calDateVO> detailList;
	private ICalendarService service;
	private int clickYear,
				clickMonth,
				clickDay;
	
    @FXML
    private Label lblDel;
    
    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnComplete;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtTitle;

    @FXML
    private Label lblDate;
    
    @FXML
    private TextArea txtareacont;
    
    @FXML
    void completeClick(ActionEvent event) {
    	CalendarVO calVo = null;
    	if((txtareacont.getText()).equals("내용없음")) {
    		String date = calendarController.clickDate;
    		if(date.length()==1) {
    			date = "0" + date;
    		}
    		//insert
    		String clickDate = Integer.toString(clickYear)+Integer.toString(clickMonth)+date;
    		calVo = new CalendarVO();
    		
    		calVo.setCal_title(txtTitle.getText());
    		calVo.setCal_conn(txtareacont.getText());
        	calVo.setCal_edate(clickDate);
        	calVo.setCal_sdate(clickDate);
        	calVo.setEmp_id(Session.session.getMem_id());
        	
        	service.insertCal(calVo); 
    	}else {
    		//update
    		calVo = new CalendarVO();
    		
    		calVo.setCal_title(txtTitle.getText());
    		calVo.setCal_conn(txtareacont.getText());
    		calVo.setCal_num(detailList.get(0).getCal_num());
    		
    		service.updateDetail(calVo);
    	}
    	//수정되었습니다 띄우고 폼 닫기
    	infoMsg("수정완료", "수정되었습니다.");
    	
    	Stage thisForm = (Stage) btnComplete.getScene().getWindow();
    	thisForm.close();
    }
    @FXML
    void updateClick(ActionEvent event) {
    	txtTitle.setDisable(false);
    	txtareacont.setDisable(false);
    	
    	
    	btnComplete.setStyle("-fx-text-fill: #ee3737; -fx-background-color : white ;");
    }
    @FXML
    void delClick(MouseEvent event) {
    	service.deleteCal(detailList.get(0).getCal_num());
    	infoMsg("삭제완료!", "삭제가 완료 되었습니다.");
    	Stage thisform = (Stage) lblDel.getScene().getWindow();
    	thisform.close();
    }
    
    @FXML
    void initialize() throws IOException {
    	if(Session.session.getMem_id()!=20) {
    		lblDel.setDisable(true);
    		btnUpdate.setDisable(true);
    		btnComplete.setDisable(true);
    	}
    	service = CalendarService.getInstance();
    	
    	clickDay = Integer.parseInt(calendarController.clickDate);
    	clickMonth = calendarController.clickMonth+1;
    	clickYear = calendarController.clickYear;
    	lblDate.setText(clickYear + "." + clickMonth + "." + clickDay);
//		 //이거를 detail에서 부르기 
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("clickYear", clickYear);
		paramMap.put("clickMonth", clickMonth);
		paramMap.put("clickDay", clickDay);
		
		detailList = service.getDetail(paramMap);
		
		try {
			txtTitle.setText(detailList.get(0).getClickTitle());
			txtareacont.setText(detailList.get(0).getClickCont());
		} catch (Exception e) {
			txtTitle.setText("일정이 없습니다.");
			txtareacont.setText("내용없음"); 
			lblDate.setDisable(true);
			lblDel.setStyle("-fx-opacity : 0.7;");
		}
    }
    public void infoMsg(String head, String msg) {
		Alert info = new Alert(AlertType.INFORMATION);
		info.setHeaderText(head);
		info.setContentText(msg);
		
		info.showAndWait();
	}
}
