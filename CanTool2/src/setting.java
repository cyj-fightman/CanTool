import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TouchEvent;
import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import serialPort.SerialTool;

public class setting extends Dialog {
	private Combo combo_1 ;
	private Combo combo_2 ;
	private Combo combo_3 ;
	private Combo combo;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public setting(Shell parentShell) {
		super(parentShell);
	}

	
	
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected Control createDialogArea(Composite parent) {
		parent.setTouchEnabled(true);
		Composite container = (Composite) super.createDialogArea(parent);
		container.setEnabled(true);
		container.setTouchEnabled(true);
		container.setLayout(null);
		
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setTouchEnabled(true);
		lblNewLabel.setBounds(11, 19, 36, 17);
		lblNewLabel.setBounds(27, 8, 61, 17);
		lblNewLabel.setText("\u6CE2\u7279\u7387");
	    combo = new Combo(container, SWT.NONE);
	    combo.setTouchEnabled(true);
		combo.setBounds(83, 15, 69, 25);
		combo.setItems(new String[] {"9600", "19200", "36400", "57600", "115200"});
		combo.setBounds(106, 5, 88, 25);
		combo.select(4);
		
		Label label = new Label(container, SWT.NONE);
		label.setTouchEnabled(true);
		label.setBounds(11, 53, 36, 17);
		label.setText("\u6570\u636E\u4F4D");
		label.setBounds(27, 45, 61, 17);
		
		combo_1 = new Combo(container, SWT.NONE);
		combo_1.setTouchEnabled(true);
		combo_1.setBounds(83, 49, 34, 25);
		combo_1.setItems(new String[] {"5", "6", "7", "8"});
		combo_1.setBounds(106, 42, 88, 25);
		combo_1.select(3);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setTouchEnabled(true);
		label_1.setBounds(11, 87, 36, 17);
		label_1.setText("\u505C\u6B62\u4F4D");
		label_1.setBounds(27, 87, 61, 17);
		
		combo_2 = new Combo(container, SWT.NONE);
		combo_2.setTouchEnabled(true);
		combo_2.setBounds(83, 83, 44, 25);
		combo_2.setItems(new String[] {"1", "2"});
		combo_2.setBounds(106, 84, 88, 25);
		combo_2.select(0);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setTouchEnabled(true);
		label_2.setBounds(11, 121, 24, 17);
		label_2.setText("\u4E32\u53E3");
		label_2.setBounds(27, 134, 61, 17);
		
		
		combo_3 = new Combo(container, SWT.NONE);
		combo_3.setTouchEnabled(true);
		combo_3.setBounds(83, 117, 64, 25);
		combo_3.setItems(new String[] {"COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "COM10"});
		combo_3.setBounds(106, 131, 88, 25);
		combo_3.select(1);
//		return container;
		Control control = super.createDialogArea(parent);
		control.setEnabled(true);
		control.setTouchEnabled(true);
		control.addTouchListener(new TouchListener() {
			public void touch(TouchEvent arg0) {
				
			}
		});
		return control;  
	}
	@Override
	protected void buttonPressed(int buttonId) {
		 if (buttonId == IDialogConstants.OK_ID){
			 if(Main.serialPort!=null){
				 Main.serialPort.close();
			 }
			  System.out.println("serial close");
			  try {
				
				 Main.serialPort = SerialTool.openPort(combo_3.getText().toString(), Integer.parseInt(combo.getText().toString()));
				 Main.serialPort.setSerialPortParams(Integer.parseInt(combo.getText().toString()), Integer.parseInt(combo_1.getText().toString()), Integer.parseInt(combo_2.getText().toString()), SerialPort.PARITY_NONE);
				 SerialTool.addListener(Main.serialPort, new SerialListener(Main.serialPort));
				 System.out.println("shell closed");
				 getShell().close();
				 
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} 
			 
		 }// 如果单击确定按钮，则将值保存到变量
			
		super.buttonPressed(buttonId);
	}
//	/**
//	 * Create contents of the button bar.
//	 * @param parent
//	 */
//	@Override
//	protected void createButtonsForButtonBar(Composite parent) {
//		
//	}
//	
	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
