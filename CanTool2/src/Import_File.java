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
		OpenFileDialog.setText("�����ļ�ѡ��Ի���");
		OpenFileDialog.setFilterExtensions(new String[] { "*.*", "*.psd",
		"*.jpg", "*.txt", "*.doc", "*.exe" });
		OpenFileDialog.setFilterNames(new String[] { "��������(*.*)",
		"potoshopg ��ʽ(*.psd)", "�ı���ʽ(*.txt)" });
		OpenFileDialog.setFilterPath("C:\\");
		String selectedOpenFile=OpenFileDialog.open();
		file = new File(selectedOpenFile);
		try {
		/**
		 * FileReader �ļ��ַ��������췽����
		* public FileReader(File file)
		* ���磺FileReader fileReader=new FileReader(file)
		* public FileReader(String filenames)
		* filenames Ϊ�����ļ������ַ���
		*/
		FileReader fileReader = new FileReader(file);
		/**
		* BufferedReader ���������ַ������ַ����뻺����
		*/
		BufferedReader reader = new BufferedReader(fileReader);
		/**
		* ��Stringbuffer �ַ�������ʵ����
		*/
		StringBuffer sb = new StringBuffer();
		String line = null;
		while ((line = reader.readLine()) != null) {
		/**
		* ͨ��append()����ʵ�ֽ��ַ�����ӵ��ַ���������
		* Ҳ����ͨ��insert()�������ַ������뻺������
		*/
		sb.append(line);
		// ����һ�к��Զ�����
		sb.append("\r\n");
		}
		import_COM_parameter_content=sb.toString();
		fileReader.close();
		Main.map=Main.read_database2.analyze_database_by_String(import_COM_parameter_content);
		} catch (IOException e) {
		
	}
	}
	
}
