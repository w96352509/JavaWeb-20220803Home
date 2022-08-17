package service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CalcAreaService {
    
	// 紀錄前端模式
    private static Map<Integer, String> types; 
    static {
        types = new LinkedHashMap<>();
        types.put(1, "圓面積");
        types.put(2, "球體積");
    }
    
    // 找 type 對應 value(模式)
    public String getNameByType(int type) {   
        return types.get(type); 
    }
    
    // 計算面積
    public double getAreaResult(int type, int r) {
        
    	// 預設面積
    	double area = 0;
        
    	// 依照 type 取對應方法
        switch(type) {
            case 1: // 圓面積
                area = Math.pow(r, 2) * Math.PI;
                break;
            case 2: // 球體積
                area = Math.pow(r, 3) * Math.PI * 4/3;
                break;
            default:
                area = -1;
        }
        
        return area;
    }

    // 處理 Servlet 過來的資料 
    public List<Map> getAreaResults(String[] types, String[] rs) {
        
    	// 紀錄資料 : [{r=10, area=314, name="圓面積"}, ...]
        List<Map> list = new ArrayList<>();
        
        // for 迴圈處理資料
        for(int i=0;i<types.length;i++) {
        	
        	// 如果 i 沒輸入補 0 area = -1
            if (rs[i] == null || rs[i].equals("")) {
                rs[i] = "0";
            }
            
            // 將資料轉型
            int type    = Integer.parseInt(types[i]);
            int r       = Integer.parseInt(rs[i]);
            
            // 取得模式
            String name = getNameByType(type);
            
            // 丟入面積處理方法
            double result = getAreaResult(type, r);
            
            // 紀錄每次輪巡的結果
            Map map = new LinkedHashMap();
            // 半徑
            map.put("r", r);
            // 結果
            map.put("result", result);
            // 方法(模式)名稱
            map.put("name", name);
            list.add(map);
        }
        return list;
    }
}
