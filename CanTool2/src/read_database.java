import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class read_database extends database_Dao{
	public static Map<String,List<database_Dao>> name_map = new HashMap<String,List<database_Dao>>();
	public static Map<String,List<String>> DLC_map = new HashMap<String,List<String>>();
	public static Map<String,String> DLC_id=new HashMap<String,String>();
	public static List<String> name_list=new ArrayList<String>();
	  public read_database(String signal_name, int start_position, int length, float a, float b, String sign) {
		super(signal_name, start_position, length, a, b, sign);
		// TODO 自动生成的构造函数存根
	}
	public read_database() {
		// TODO 自动生成的构造函数存根
	}
	public Map analyze_database(String fileName) {
		 name_list=new ArrayList<String>();
		  Map<String,List<database_Dao>> map = new HashMap<String,List<database_Dao>>();
		  List<database_Dao> list = new ArrayList<database_Dao>();
		  List<database_Dao> list2 = new ArrayList<database_Dao>();
		  List<String> list3=new ArrayList<String>();
		  StringBuffer stringBuffer =new StringBuffer();
	        File file = new File(fileName);
	        BufferedReader reader = null;
	        try {
	            reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            String mark=null;
	            String mark_name=null;
	            String DLC=null;
	            // 一次读入一行，直到读入null为文件结束
	            while ((tempString = reader.readLine()) != null) {
	                // 显示行号
	            	
	            	if(tempString.length()!=0){
	            		stringBuffer.append(tempString);
	            		
	            		if(String.valueOf(tempString.charAt(0))!=" "&&!String.valueOf(tempString.charAt(0)).equals(" ")){
	            			mark=tempString.split(" ")[1];
	            			mark_name=tempString.split(" ")[2].split(":")[0].trim();
	            			DLC=tempString.split(" ")[3].trim();
	            			name_list.add(mark_name);
	            		}
	            		else{
	            			
//	            			System.out.println(tempString);
	            			database_Dao database_Dao2=analyze_database_Dao(tempString);
	            			list.add(database_Dao2);	
	            			list2.add(database_Dao2);
	            			list3.add(DLC);
//	            			System.out.println(list.size()+"");
	            		}
	            	}else{
	            		DLC_id.put(mark_name, mark);
	            		name_map.put(mark_name, list2);
	            		map.put(mark, list);
	            		DLC_map.put(mark_name, list3);
	            		list2=new ArrayList<database_Dao>();
	            		list=new ArrayList<database_Dao>();
	            		list3=new ArrayList<String>();;
	            		mark=null;
	            		mark_name=null;
	            	}
	            	
	            }
	            DLC_id.put(mark_name, mark);
	            name_map.put(mark_name, list2);
        		map.put(mark, list);
        		DLC_map.put(mark_name, list3);
        		list2=new ArrayList<database_Dao>();
        		list=new ArrayList<database_Dao>();
        		list3=new ArrayList<String>();;
        		mark=null;
        		mark_name=null;
	            
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
	
	public Map analyze_database_by_String(String str) {
		 name_list=new ArrayList<String>();
		  Map<String,List<database_Dao>> map = new HashMap<String,List<database_Dao>>();
		  List<database_Dao> list = new ArrayList<database_Dao>();
		  List<database_Dao> list2 = new ArrayList<database_Dao>();
		  List<String> list3=new ArrayList<String>();
		  StringBuffer stringBuffer =new StringBuffer();
		  FileWriter fw;
		try {
			fw = new FileWriter("temp_database.txt");
			fw.write(str);
			fw.flush();
			fw.close(); 
		} catch (IOException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}          
	        File file = new File("temp_database.txt");
	        BufferedReader reader = null;
	        try {
	            reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            String mark=null;
	            String mark_name=null;
	            String DLC=null;
	            // 一次读入一行，直到读入null为文件结束
	            while ((tempString = reader.readLine()) != null) {
	                // 显示行号
//	            	System.out.println(tempString);
	            	if(tempString.length()!=0){
	            		stringBuffer.append(tempString);
	            		
	            		if(String.valueOf(tempString.charAt(0))!=" "&&!String.valueOf(tempString.charAt(0)).equals(" ")){
	            			mark=tempString.split(" ")[1];
	            			mark_name=tempString.split(" ")[2].split(":")[0].trim();
	            			DLC=tempString.split(" ")[3].trim();
	            			name_list.add(mark_name);
	            		}
	            		else{
//	            			System.out.println(tempString);
	            			database_Dao database_Dao2=analyze_database_Dao(tempString);
	            			list.add(database_Dao2);	
	            			list2.add(database_Dao2);
	            			list3.add(DLC);
//	            			System.out.println(list.size()+"");
	            		}
	            	}else{
	            		DLC_id.put(mark_name, mark);
	            		name_map.put(mark_name, list2);
	            		map.put(mark, list);
	            		DLC_map.put(mark_name, list3);
	            		list2=new ArrayList<database_Dao>();
	            		list=new ArrayList<database_Dao>();
	            		list3=new ArrayList<String>();;
	            		mark=null;
	            		mark_name=null;
	            		DLC=null;
	            		
	            	}
	            	
	            }
	            DLC_id.put(mark_name, mark);
	            name_map.put(mark_name, list2);
        		map.put(mark, list);
        		DLC_map.put(mark_name, list3);
        		list2=new ArrayList<database_Dao>();
        		list=new ArrayList<database_Dao>();
        		list3=new ArrayList<String>();;
        		mark=null;
        		mark_name=null;
        		DLC=null;
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
	        System.out.println("import database success");
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
