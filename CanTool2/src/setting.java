import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import serialPort.SerialTool;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class setting extends Dialog {
	private Combo combo_1 ;
	private Combo combo_2 ;
	private Combo combo_3 ;
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
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(null);
		
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(11, 19, 36, 17);
		lblNewLabel.setBounds(27, 8, 61, 17);
		lblNewLabel.setText("\u6CE2\u7279\u7387");
		Combo combo = new Combo(container, SWT.NONE);
		combo.setBounds(83, 15, 69, 25);
		combo.setItems(new String[] {"9600", "19200", "36400", "57600", "115200"});
		combo.setBounds(106, 5, 88, 25);
		
		Label label = new Label(container, SWT.NONE);
		label.setBounds(11, 53, 36, 17);
		label.setText("\u6570\u636E\u4F4D");
		label.setBounds(27, 45, 61, 17);
		
		combo_1 = new Combo(container, SWT.NONE);
		combo_1.setBounds(83, 49, 34, 25);
		combo_1.setItems(new String[] {"5", "6", "7", "8"});
		combo_1.setBounds(106, 42, 88, 25);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setBounds(11, 87, 36, 17);
		label_1.setText("\u505C\u6B62\u4F4D");
		label_1.setBounds(27, 87, 61, 17);
		
		combo_2 = new Combo(container, SWT.NONE);
		combo_2.setBounds(83, 83, 44, 25);
		combo_2.setItems(new String[] {"1", "1.5", "2"});
		combo_2.setBounds(106, 84, 88, 25);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setBounds(11, 121, 24, 17);
		label_2.setText("\u4E32\u53E3");
		label_2.setBounds(27, 134, 61, 17);
		
	
		
		combo_3 = new Combo(container, SWT.NONE);
		combo_3.setBounds(83, 117, 64, 25);
		combo_3.setItems(new String[] {"COM2", "COM3"});
		combo_3.setBounds(106, 131, 88, 25);
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try{
					Main.serialPort.close();
					  Main.serialPort = SerialTool.openPort(combo_3.getText().toString(), Integer.parseInt(combo.getText().toString()));
					 SerialTool.addListener(Main.serialPort, new SerialListener(Main.serialPort));
				 }catch(Exception ee){
					 
					 ee.printStackTrace();
				 }finally{
					 
				 }
			}
		});
		button.setBounds(354, 215, 80, 27);
		button.setText("\u786E\u5B9A");
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
