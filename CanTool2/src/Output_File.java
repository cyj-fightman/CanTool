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
		OpenFileDialog.setText("�����ļ�ѡ��Ի���");
		OpenFileDialog.setFilterExtensions(new String[] {"*.txt"});
		OpenFileDialog.setFilterNames(new String[] { "�ı���ʽ(*.txt)" });
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
				// TODO �Զ����ɵ� catch ��
				e2.printStackTrace();
			}       
		
	}
}
