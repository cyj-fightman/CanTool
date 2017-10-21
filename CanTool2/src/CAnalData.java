

import java.util.ArrayList;

public class CAnalData extends MAnalData{
	private char[] 	  datas;
	private ArrayList<char[]> list;
	//	控制类的构�?�方�?;
	public CAnalData(String iData) {
		setiData(iData);
		datas=getDatas();
		list=new ArrayList<char[]>();
	}
	
	public void computeData(){
		char FLAG=datas[0];
		//	设置ID编号
		setFLAG(FLAG);
		String temp="";
		int    size=0;
		//	位数的�?�择;
		switch (FLAG) {
		//	标准�?;
		case 't':
			for(int i=1;i<4;i++){
				temp+=""+datas[i];
			}
			setDLC(Integer.parseInt(datas[4]+""));
			size=getDLC();
			
			for(int i=0;i<size;i++){
				int index=0;
				char[] data=new char[2];
				for(int j=i*2+5;j<(i*2+5)+2;j++){
					data[index]=datas[j];
					index++;
				}
				list.add(data);
				setDATA(list);
			}
			break;
		//	扩展�?;
		case 'T':
			for(int i=1;i<9;i++){
				temp+=""+datas[i];
			}
			setDLC(Integer.parseInt(datas[9]+""));
			size=getDLC();
			for(int i=0;i<size;i++){
				int index=0;
				char[] data=new char[2];
				for(int j=i*2+10;j<(i*2+10)+2;j++){
					data[index]=datas[j];
					index++;
				}
				list.add(data);
				setDATA(list);
			}
			break;
		default:
			break;
		}
		setID(temp);
	}
}
