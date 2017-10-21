import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.ibm.icu.impl.Grego;

import gnu.io.SerialPort;
import serialPort.SerialTool;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;

public class Main extends ApplicationWindow {
	private Text text;
	private Label lblNewLabel=null;
	private Group grpTextD=null;
	public static SerialPort serialPort=null;
	public static StringBuffer stringBuffer=new StringBuffer();
	public static String receive_data=null;
	private Action action;
	public Shell shell;
	public Map map;
	private Text text_1;
	/**
	 * Create the application window.
	 */
	public Main() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		read_database read_database2=new read_database();
		 map =read_database2.analyze_database("database.txt");
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
	
	public static byte[] intToBytes2(int n){
	    byte[] b = new byte[4];
	    for(int i = 0;i < 4;i++)
	    {
			b[i]=(byte)(n>>(24-i*8));
	    } 
	    return b;
	}

	public static int byteToInt2(byte[] b)
	{
		int mask=0xff;
	    int temp=0;
		int n=0;            
	    for(int i=0;i<b.length;i++){
	        n<<=8;                
			temp=b[i]&mask;        
	        n|=temp;            
	      }      
	  return n;  
	}
	
	public static byte[] subBytes(byte[] src, int begin, int count) {  
	    byte[] bs = new byte[count];  
	    System.arraycopy(src, begin, bs, 0, count);  
	    return bs;  
	} 
	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		text = new Text(container, SWT.BORDER);
		text.setBounds(0, 0, 530, 25);	
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
		btnInput.setBounds(536, -2, 80, 27);
		btnInput.setText("send");
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setBounds(0, 31, 80, 17);
		lblNewLabel_1.setText("\u63A5\u6536\u5230\u7684\u5185\u5BB9\uFF1A");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayout(new FillLayout());
		scrolledComposite.setBounds(0, 54, 665, 306);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setAlwaysShowScrollBars(true);
		grpTextD = new Group(scrolledComposite, SWT.NONE|SWT.MULTI| SWT.WRAP);
		grpTextD.setText("数据");
		scrolledComposite.setMinSize(new Point(50,680));//面板的最小大小
		scrolledComposite.setContent(grpTextD);
		scrolledComposite.setToolTipText("can scroll");
//				lblNewLabel = new Label(scrolledComposite, SWT.NONE);
//				lblNewLabel.setSize(644, 285);
//				lblNewLabel.setText("none");
//				scrolledComposite.setContent(lblNewLabel);
		new Thread() {//线程操作
            public void run() {
                while(true){
                    try {
                    	//lblNewLabel.getDisplay().asyncExec(new Runnable() {
                    	grpTextD.getDisplay().asyncExec(new Runnable() {
                         @Override
                         public void run() {
                       if(SerialListener.receive_data!=null){
                    	   
//                    	   String str="t12380011121314151617\t";
                    	   	byte[] sequence_one=new byte[64];//用于存储data的byte[]
	                   		CAnalData cAnalData=new CAnalData(receive_data);
	                   		cAnalData.computeData();
	                   		char flag=cAnalData.getFLAG();
	                   		String id=cAnalData.getID();
	                   		int dlc=cAnalData.getDLC();
	                   		int length=cAnalData.getDATA().size();
	                   		StringBuffer data=new StringBuffer();
	                   		int index=0;
	                   		for(int i=0;i<length;i++){
	                   			byte[] temp=intToBytes2(Integer.valueOf(cAnalData.getDATA().get(i)[0]-48).intValue());
	                   			for(int j =0;j<4;j++){
	                   				sequence_one[index]=temp[j];
	                   				index++;
	                   			}
	                   			byte[] temp1=intToBytes2(Integer.valueOf(cAnalData.getDATA().get(i)[1]-48).intValue());
	                   			for(int j =0;j<4;j++){
	                   				sequence_one[index]=temp1[j];
	                   				index++;
	                   			}
	                		}
	                   		String data_once="flag:"+flag+'\t'+"id:"+id+'\t'+"dlc:"+dlc+'\t'+"length:"+length+"\t"+"data:"+data;
	                   		System.out.println(map.keySet().size());
	                   		
	                   		if(map.containsKey(id)){
	                   			List<database_Dao> list = (List<database_Dao>) map.get(id);
		                   		for(int i=0;i<list.size();i++){
		                   			 database_Dao database_Dao2=list.get(i);
		                   			 String signal_name=database_Dao2.getSignal_name();
		                   			 int start_position=database_Dao2.getStart_position();
		                   			 int length1=database_Dao2.getLength();
		                   			 float A=database_Dao2.getA();
		                   			 float B=database_Dao2.getB();
		                   			 String sign=database_Dao2.getSign();
		                   			 float finalnum=Float.valueOf(byteToInt2(subBytes(sequence_one,start_position,length1)));
		                   			 stringBuffer.append(signal_name+finalnum+"\n\r");
//		                   			 System.out.println("signal_name:"+signal_name+"start_position:"+start_position+"length"+length1+"A:"+A+"B:"+B+"sign:"+sign);
		                   		}
	                   		}
//	                   		stringBuffer.append(data_once+'\n');
                            //lblNewLabel.setText(stringBuffer.toString());//输出到Label上
	                   		grpTextD.setText(stringBuffer.toString());
                            SerialListener.receive_data=null;
                       }
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
		return new Point(681, 450);
	}
}
