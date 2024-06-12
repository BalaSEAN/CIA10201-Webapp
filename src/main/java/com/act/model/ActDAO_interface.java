package com.act.model;

import java.util.*;

public interface ActDAO_interface {
          public void insert(ActVO actVO);
          public void update(ActVO actVO);
          public void delete(Integer actPicNo);
          public ActVO findByPrimaryKey(Integer actPicNo);
          public List<ActVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//          public List<ActVO> getAll(Map<String, String[]> map); 
}
