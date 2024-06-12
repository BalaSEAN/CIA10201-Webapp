package com.act.model;

import java.util.List;
import java.util.Map;

public class ActService {

	private ActDAO_interface dao;

	public ActService() {
		dao = new ActJDBCDAO();
	}

	public ActVO addAct(Integer actNo, String actPicName, byte[] actPic) 
	{

		ActVO actVO = new ActVO();
	
		actVO.setActNo(actNo);
		actVO.setActPicName(actPicName);
		actVO.setActPic(actPic);		
		dao.insert(actVO);

		return actVO;
	}

	public ActVO updateAct(Integer actPicNo, String actPicName,
			byte[] actPic) {

		ActVO actVO = new ActVO();

		actVO.setActPicNo(actPicNo);		
		actVO.setActPicName(actPicName);
		actVO.setActPic(actPic);		
		dao.update(actVO);

		return actVO;
	}

	public void deleteAct(Integer actPicNo) {
		dao.delete(actPicNo);
	}

	public ActVO getOneAct(Integer actPicNo) {
		return dao.findByPrimaryKey(actPicNo);
	}

	public List<ActVO> getAll() {
		return dao.getAll();
	}
	
	public List<ActVO> getAll(Map<String, String[]> map) {
		return dao.getAll();
	}
}
