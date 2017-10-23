import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class Import_File extends Action{
	File file=null;
	public static String import_COM_parameter_content=null;
	@Override
	public void run() {
		FileDialog OpenFileDialog = new FileDialog(new Shell(), SWT.OPEN);
		OpenFileDialog.setText("打开型文件选择对话框");
		OpenFileDialog.setFilterExtensions(new String[] { "*.*", "*.psd",
		"*.jpg", "*.txt", "*.doc", "*.exe" });
		OpenFileDialog.setFilterNames(new String[] { "所有类型(*.*)",
		"potoshopg 格式(*.psd)", "文本格式(*.txt)" });
		OpenFileDialog.setFilterPath("C:\\");
		String selectedOpenFile=OpenFileDialog.open();
		file = new File(selectedOpenFile);
		try {
		/**
		 * FileReader 文件字符流，构造方法：
		* public FileReader(File file)
		* 例如：FileReader fileReader=new FileReader(file)
		* public FileReader(String filenames)
		* filenames 为包含文件名的字符串
		*/
		FileReader fileReader = new FileReader(file);
		/**
		* BufferedReader 类用来把字符流的字符读入缓冲区
		*/
		BufferedReader reader = new BufferedReader(fileReader);
		/**
		* 对Stringbuffer 字符串缓冲实例化
		*/
		StringBuffer sb = new StringBuffer();
		String line = null;
		while ((line = reader.readLine()) != null) {
		/**
		* 通过append()方法实现将字符串添加到字符缓冲区。
		* 也可以通过insert()方法将字符串插入缓冲区中
		*/
		sb.append(line);
		// 读入一行后自动换行
		sb.append("\r\n");
		}
		import_COM_parameter_content=sb.toString();
		fileReader.close();
		Main.map=Main.read_database2.analyze_database_by_String(import_COM_parameter_content);
		} catch (IOException e) {
		
	}
	}
	
}
