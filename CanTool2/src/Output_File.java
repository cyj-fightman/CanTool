import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class Output_File extends Action{
	File file=null;
	public static String import_COM_parameter_content=null;
	@Override
	public void run() {
		FileDialog OpenFileDialog = new FileDialog(new Shell(), SWT.OPEN);
		OpenFileDialog.setText("打开型文件选择对话框");
		OpenFileDialog.setFilterExtensions(new String[] {"*.txt"});
		OpenFileDialog.setFilterNames(new String[] { "文本格式(*.txt)" });
		OpenFileDialog.setFilterPath("C:\\");
		String selectedOpenFile=OpenFileDialog.open();
		file = new File(selectedOpenFile);
		 FileWriter fw;
			try {
				fw = new FileWriter(selectedOpenFile);
				fw.write(Main.text_2.getText().toString());
				fw.flush();
				fw.close(); 
			} catch (IOException e2) {
				// TODO 自动生成的 catch 块
				e2.printStackTrace();
			}       
		
	}
}
