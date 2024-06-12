package com.act.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;

import java.util.*;
import com.act.model.*;

@MultipartConfig(fileSizeThreshold = 0 * 1024 * 1024, maxFileSize = 1 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
public class ActServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("actPicNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("actPicNo","請輸入圖片編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/act/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer actPicNo = null;
				try {
					actPicNo = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("actPicNo","圖片編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/act/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ActService actSvc = new ActService();
				ActVO actVO = actSvc.getOneAct(actPicNo);
				if (actVO == null) {
					errorMsgs.put("actPicNo","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/act/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actVO", actVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("getOne_For_Display", "true"); // 旗標getOne_For_Display見select_page.jsp的第155行 -->
//				String url = "/back-end/act/listOneEmp.jsp";    // 成功轉交 listOneEmp.jsp
				String url = "/back-end/act/select_page.jsp";   // 查詢完成後轉交select_page.jsp由其include file="listOneEmp-div.fragment"
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer actPicNo = Integer.valueOf(req.getParameter("actPicNo"));
				
				/***************************2.開始查詢資料****************************************/
				ActService actSvc = new ActService();
				ActVO actVO = actSvc.getOneAct(actPicNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?actPicNo="+actVO.getActPicNo()
				+ "&actPic="+actVO.getActPic();
				String url = "/back-end/act/update_act_input.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer actPicNo = Integer.valueOf(req.getParameter("actPicNo").trim());
				
				String actPicName = req.getParameter("actPicName");
				String actPicReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (actPicName == null || actPicName.trim().length() == 0) {
					errorMsgs.put("actPic","圖片名稱: 請勿空白");
				} else if(!actPicName.trim().matches(actPicReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("actPic","圖片名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				
				//照片
				InputStream in = req.getPart("actPic").getInputStream(); //從javax.servlet.http.Part物件取得上傳檔案的InputStream
				byte[] actPic = null;
				if(in.available()!=0){
					actPic = new byte[in.available()];
					in.read(actPic);
					in.close();
				}  else {
					ActService actSvc = new ActService();
					actPic = actSvc.getOneAct(actPicNo).getActPic();
				}
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					errorMsgs.put("Exception","修改資料失敗:---------------");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/act/update_act_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ActService actSvc = new ActService();
				ActVO actVO = actSvc.updateAct(actPicNo, actPicName, actPic);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("success", "- (修改成功)");
				req.setAttribute("actVO", actVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/act/listOneAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			Integer actNo = Integer.valueOf(req.getParameter("actNo"));
			
			String actPic = req.getParameter("actPic");
				if (actPic == null || actPic.trim().length() == 0) {
					errorMsgs.put("actPic","圖片名稱: 請勿空白");
				}				
				
				//照片
				InputStream in = req.getPart("actPicType").getInputStream(); //從javax.servlet.http.Part物件取得上傳檔案的InputStream
				byte[] actPicType = null;
				if(in.available()!=0){
					actPicType = new byte[in.available()];
					in.read(actPicType);
					in.close();
				}  else errorMsgs.put("actPicType","揪團照片: 請上傳照片");
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/act/addAct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ActService actSvc = new ActService();
				actSvc.addAct(actNo, actPic, actPicType);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("success", "- (新增成功)");
				String url = "/back-end/act/listAllAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer actPicNo = Integer.valueOf(req.getParameter("actPicNo"));
				
				/***************************2.開始刪除資料***************************************/
				ActService actSvc = new ActService();
				actSvc.deleteAct(actPicNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				req.setAttribute("success", "- (刪除成功)");
				String url = "/back-end/act/listAllAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
		
		if ("listEmps_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				Map<String, String[]> map = req.getParameterMap();
				
				/***************************2.開始複合查詢***************************************/
				ActService actSvc = new ActService();
				List<ActVO> list  = actSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("actListData", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/act/listAllAct.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
		}		
	}
}
