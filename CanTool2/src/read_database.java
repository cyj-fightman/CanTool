import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class read_database extends database_Dao{
	
	  public read_database(String signal_name, int start_position, int length, float a, float b, String sign) {
		super(signal_name, start_position, length, a, b, sign);
		// TODO 自动生成的构造函数存根
	}
	public read_database() {
		// TODO 自动生成的构造函数存根
	}
	public Map analyze_database(String fileName) {
		  Map<String,List<database_Dao>> map = new HashMap<String,List<database_Dao>>();
		  List<database_Dao> list = new ArrayList<database_Dao>();
		  StringBuffer stringBuffer =new StringBuffer();
	        File file = new File(fileName);
	        BufferedReader reader = null;
	        try {
	            reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            String mark=null;
	            // 一次读入一行，直到读入null为文件结束
	            while ((tempString = reader.readLine()) != null) {
	                // 显示行号
	            	
	            	if(tempString.length()!=0){
	            		stringBuffer.append(tempString);
	            		
	            		if(String.valueOf(tempString.charAt(0))!=" "&&!String.valueOf(tempString.charAt(0)).equals(" ")){
	            			mark=tempString.split(" ")[1];
	            		}
	            		else{
//	            			System.out.println(tempString);
	            			database_Dao database_Dao2=analyze_database_Dao(tempString);
	            			list.add(database_Dao2);	
//	            			System.out.println(list.size()+"");
	            		}
	            	}else{
	            		
	            		map.put(mark, list);
	            		list=new ArrayList<database_Dao>();
	            		mark=null;
	            		
	            	}
	            	
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
	        return map;
	    }
	// SG_ CDU_HVACACMaxButtonStVD : 23|1@0+ (1,0) [0|1] ""  HVAC
	 public database_Dao analyze_database_Dao(String str){
		 String[] split_str=str.trim().split(" ");
		 String signal_name=split_str[1];
		 int start_position=Integer.valueOf(split_str[3].split("|")[0]).intValue();
		 int length=Integer.valueOf(split_str[3].split("|")[0].split("@")[0]).intValue();
		 float A=Float.valueOf(split_str[4].substring(1, split_str[4].length()-1).split(",")[0]);
		 float B=Float.valueOf(split_str[4].substring(1, split_str[4].length()-1).split(",")[1]);
		 String sign=split_str[6];
//		 System.out.println("signal_name:"+signal_name+"start_position:"+start_position+"length"+length+"A:"+A+"B:"+B+"sign:"+sign);
		 database_Dao database_Dao2=new database_Dao(signal_name, start_position, length, A, B, sign);
		 return database_Dao2;
	 }
}
