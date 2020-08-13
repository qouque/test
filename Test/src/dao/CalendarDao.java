package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.ibatis.sqlmap.client.SqlMapClient;

import controller.calDateVO;
import theBug.util.IbatisUtil;
import theBug.vo.CalendarVO;

public class CalendarDao implements ICalendarDao {
	static Logger logger = Logger.getLogger(CalendarDao.class);
	private static CalendarDao caldao;
	private SqlMapClient smc;
	public CalendarDao() {
		smc = IbatisUtil.getSqlMapClient();
	}
	public static CalendarDao getInstance() {
		if(caldao==null)
			caldao = new CalendarDao();
		return caldao;
	}
	@Override
	public List<CalendarVO> getAllCal(Map<String, Integer> calMap) {
		List<CalendarVO> calList = null;
		try {
			calList = smc.queryForList("calendar.selectCal", calMap);
//			logger.info("calendar정보 가져오기 성공");
		} catch (SQLException e) {
			logger.error("calendar정보 가져오기 실패");
			e.printStackTrace();
		}
		return calList;
	}
	@Override
	public void insertCal(CalendarVO calVo) {
		try {
			smc.insert("calendar.insertCal",calVo);
			logger.info("달력 정보 입력 성공");
		} catch (SQLException e) {
			logger.error("달력 정보 입력 실패");
			e.printStackTrace();
		}
	}
	@Override
	public List<calDateVO> getDetail(Map<String, Integer> calMap) {
		List<calDateVO> getDetail = null;
		try {
			getDetail=smc.queryForList("calendar.selectdetail",calMap);
			logger.info("detail성공");
		} catch (SQLException e) {
			logger.error("detail실패");
			e.printStackTrace();
		}
		return getDetail;
	}
	@Override
	public void deleteCal(int cal_num) {
		try {
			smc.delete("calendar.delDetail", cal_num);
			logger.info("detail삭제 성공");
		} catch (SQLException e) {
			logger.error("detail 삭제 실패");
			e.printStackTrace();
		}
	}
	@Override
	public void updateDetail(CalendarVO calVo) {
		try {
			smc.update("calendar.updateDetail", calVo);
			logger.info("detail수정 성공");
		} catch (SQLException e) {
			logger.error("detail수정 실패");
			e.printStackTrace();
		}
	}
}
