import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import gnu.io.SerialPort;
import serialException.NoSuchPort;
import serialException.NotASerialPort;
import serialException.PortInUse;
import serialException.SerialPortParameterFailure;
import serialPort.SerialTool;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

public class Main extends ApplicationWindow {
	private Text text;
	private Label lblNewLabel=null;
	public static SerialPort serialPort=null;
	public static StringBuffer stringBuffer=new StringBuffer();
	private Action action;
	public Shell shell;
	/**
	 * Create the application window.
	 */
	public Main() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		
		  
		 try{
			  serialPort = SerialTool.openPort("COM3", 115200);
			 SerialTool.addListener(serialPort, new SerialListener(serialPort));
		 }catch(Exception e){
			 
			 e.printStackTrace();
		 }finally{
			 
		 }
		 
	}
	
	public void set_label(){
		if(lblNewLabel!=null){
    		lblNewLabel.setText(SerialListener.receive_data);
    	}
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		text = new Text(container, SWT.BORDER);
		text.setBounds(0, 0, 172, 23);
		
		Button btnInput = new Button(container, SWT.NONE);
		btnInput.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			        String temp;
			        //以波特率115200打开串口COM11
//			        SerialPort serialPort;
					try {
//						serialPort = SerialTool.openPort("COM3", 115200);
				
				            SerialTool.sendToPort(serialPort,text.getText().getBytes() );
				        
					} catch (Exception e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
			}
		});
		btnInput.setBounds(210, 0, 80, 27);
		btnInput.setText("send");
		
		lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
			}
		});
		lblNewLabel.setText("none");
		lblNewLabel.setBounds(0, 54, 424, 173);
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setBounds(0, 29, 80, 17);
		lblNewLabel_1.setText("\u63A5\u6536\u5230\u7684\u5185\u5BB9\uFF1A");
		new Thread() {//线程操作
            public void run() {
                while(true){
                    try {
                    	lblNewLabel.getDisplay().asyncExec(new Runnable() {       
                         @Override
                         public void run() {
                       if(SerialListener.receive_data!=null)
                            		 lblNewLabel.setText(stringBuffer.toString());//输出到Label上        		    
                         }
                     });
                     Thread.sleep(1000);//每隔一秒刷新一次
                 } catch (Exception e) {
                 }

                }
            }
     }.start();
		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
		{
			action = new Action("New Action") {
			};
			
			action.addPropertyChangeListener(new IPropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
				
				}
			});
		}
	}

	

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		
		MenuManager menuManager_1 = new MenuManager("\u53C2\u6570\u8BBE\u7F6E");
		AddParameter addParameter = new AddParameter(shell);

		addParameter.setToolTipText("\u8BBE\u7F6E\u4E32\u53E3\u53C2\u6570");
		addParameter.setText("\u6253\u5F00\u8BBE\u7F6E\u7A97\u53E3");
		menuManager_1.setVisible(true);
		
		menuManager.add(menuManager_1);
		menuManager_1.add(addParameter);
		
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Main window = new Main();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		shell=newShell;
		newShell.setText("CanTool");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 351);
	}
}
