import java.io.UnsupportedEncodingException;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class Send_Values extends AbstractExample{
    private Table table;
    private Tree tree;
    public static String final_reault="T";
    public Send_Values() {
		shell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				try{
					byte[] send_bytes=new byte[64];
					
					for(int i=0;i<64;i++){
						send_bytes[i]=0;
					}
					System.out.println(String.valueOf(send_bytes));
					String map_name=tree.getSelection()[0].getText().toString();
					String id=read_database.DLC_id.get(map_name);
						
					List<database_Dao> list = (List<database_Dao>) Main.map.get(id);
					String num=Integer.toHexString(Integer.valueOf(id));
					if(num.length()<8){
						for(int l=num.length();l<8;l++){
							num='0'+num;
						}
					}
					final_reault=final_reault+num+"8";
					for(int i=0;i<list.size();i++){
	          			 database_Dao database_Dao2=list.get(i);
	          			 String signal_name=database_Dao2.getSignal_name();
	          			 int start_position=database_Dao2.getStart_position();
	          			 int length1=database_Dao2.getLength();
	          			 float A=database_Dao2.getA();
	          			 float B=database_Dao2.getB();
	          			 String sign=database_Dao2.getSign();
	          			 int value=Integer.valueOf((int) ((Float.valueOf(table.getItem(i).getText(1))-B)/A));
	          			String binaryStr = java.lang.Integer.toBinaryString(value); 
	        	        try {
	        	        	byte final_results[]=new byte[length1];
	        	        	
							byte results[] = binaryStr.getBytes("utf8");
//							for(int p=0;p<results.length;p++){
//								System.out.println(results[p]);
//							}
							int len=results.length;
							int index=length1-1;
							while(len-1>=0){
								final_results[index]=results[len-1];
								len--;
								index--;
							}
							index=0;
							for(int j=start_position;j<length1+start_position;j++){
								send_bytes[j]=final_results[index];
								index++;
							}
							
							
						} catch (UnsupportedEncodingException e1) {
							shell.close();
							System.out.println("shell close");
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}  
	        	        
//	        	        String t = new String(send_bytes);
//	        	        System.out.println(t);
//	          			 float finalnum=Float.valueOf(byteToInt2(subBytes(sequence_one,start_position,length1)));
//	          			 Main.sb.append(signal_name+finalnum+"\n\r");
//	          			 System.out.println("signal_name:"+signal_name+"start_position:"+start_position+"length"+length1+"A:"+A+"B:"+B+"sign:"+sign);
	          		}
					for(int pt=0;pt<send_bytes.length;pt++){
	    	        	if(send_bytes[pt]>=48){
	    	        		send_bytes[pt]=(byte) (send_bytes[pt]-48);
	    	        	}
//	    	        	System.out.println(send_bytes[pt]);
	    	        }
					int mark_place=0;
					for(int pt=0;pt<send_bytes.length;pt++){
	    	        	if((pt+1)%4==0){
	    	        		byte[] subB=subBytes(send_bytes, mark_place, 4);
//	    	        		for(int jj=0;jj<subB.length;jj++){
//	    	        			System.out.println(subB[jj]);
//	    	        		}
	    	        		final_reault=final_reault+Integer.toHexString(toInt(subB));
	    	        		mark_place=pt+1;
	    	        	}
					}
					System.out.println(final_reault);
					Main.smart_send_data=final_reault;
					shell.dispose();
				}catch(Exception ee){
					Main.smart_send_data="数据设置错误，请设置值并且值在数据库给定区间内";
					shell.dispose();
					
					ee.printStackTrace();
					
				}
			}
		});
    }
    
    public static byte[] subBytes(byte[] src, int begin, int count) {  
	    byte[] bs = new byte[count];  
	    System.arraycopy(src, begin, bs, 0, count);  
	    return bs;  
	} 
    
    public static int toInt(byte[] bi) {
		int len = bi.length;
		int sum = 0;
		int tmp,  max = len - 1;
		for (int i = 0; i < len; ++i) {
		tmp = bi[i];
		sum += tmp * Math.pow(2, max--);
		}
		return sum;
		}
    public void todo(Shell shell) {
        TabFolder tabFolder = new TabFolder(shell,SWT.BORDER);
        
        TabItem tabItem1 = new TabItem(tabFolder,SWT.NONE);
        tabItem1.setText("第一页");
        
        Composite compsoite1 = new Composite(tabFolder,SWT.NONE);
        tabItem1.setControl(compsoite1);
        
        
        
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        compsoite1.setLayout(layout);
        Group treeGroup = new Group(compsoite1,SWT.NONE);
        treeGroup.setText("Tree");
        GridData griddata = new GridData(GridData.FILL_BOTH);
        griddata.heightHint = 50;
        treeGroup.setLayoutData(griddata);
        treeGroup.setLayout(new GridLayout(1,false));
        
        Group tableGroup = new Group(compsoite1,SWT.NONE);
        tableGroup.setText("Table");
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.heightHint = 20;
        tableGroup.setLayoutData(gd);
        tableGroup.setLayout(new GridLayout(1,false));
        
        {
            tree = new Tree(treeGroup,SWT.SINGLE);
            tree.setLayoutData(new GridData(GridData.FILL_BOTH));
            for(int i=0;i<read_database.name_list.size();i++){
            	  TreeItem stu1 = new TreeItem(tree,SWT.NONE);
            	  stu1.setText(read_database.name_list.get(i));
            }
          
            
            tree.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent evt){
                	table.clearAll();
                	table.setItemCount(0);;
                	int list_size=read_database.name_map.get(tree.getSelection()[0].getText().toString()).size();
                	List list=read_database.name_map.get(tree.getSelection()[0].getText().toString());
                	for(int i=0;i<list_size;i++){
		
                		
                		
                		TableItem item = new TableItem(table,SWT.NONE);
                		database_Dao database_dao=(database_Dao) list.get(i);
                		if(database_dao.getSignal_name().equals("CDU_HVAC_DriverTempSelect")||database_dao.getSignal_name().equals("HVAC_PsnTempSelect")
                				||database_dao.getSignal_name().equals("HVAC_PsnTempSelect")||database_dao.getSignal_name().equals("HVAC_TempSelect")){
                				item.setText(new String[]{database_dao.getSignal_name(),"19.0"});
                		}else if(database_dao.getSignal_name().equals("BCM_KeySt")){
                			item.setText(new String[]{database_dao.getSignal_name(),"1"});
                		}else if(database_dao.getSignal_name().equals("HVAC_Checksum")){
                			item.setText(new String[]{database_dao.getSignal_name(),"155.0"});
                		}
                		else{
                				item.setText(new String[]{database_dao.getSignal_name(),"0.0"});
                		}
                	}

                	
                }
            });
        }
        
       
        {
        table = new Table(tableGroup,SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
        table.setHeaderVisible(true);//设置表头可见
        table.setLinesVisible(true);//设置线条可见
        table.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        TableColumn column1 = new TableColumn(table,SWT.NULL);
        column1.setText("字符段");
        column1.pack();
        column1.setWidth(150);
        
        TableColumn column2 = new TableColumn(table,SWT.NULL);
        column2.setText("值");
        column2.pack();
        column2.setWidth(150);   
        
        table.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
	           	 final TableEditor editor = new TableEditor(table);
	              final Control oldEditor = editor.getEditor();
	              if (oldEditor != null) {
	               oldEditor.dispose();
	              }
	              final Text text = new Text(table, SWT.NONE);
	              text.computeSize(SWT.DEFAULT, table.getItemHeight());
	              editor.grabHorizontal = true;
	              editor.minimumHeight = text.getSize().y;
	              editor.minimumWidth = text.getSize().x;
	              editor.setEditor(text, table.getItem(table.getSelectionIndex()), 1);
	              text.setText(table.getItem(1).getText(1));
	              text.setForeground(table.getItem(table.getSelectionIndex()).getForeground());
	              text.setFocus();
//	              text.forceFocus();
	              
//	              text.addFocusListener(new FocusListener() {
//          	 		
//	                  @Override
//	                  public void focusGained(final FocusEvent e) {
//	                   // TODO Auto-generated method stub
//	                	  table.getItem(table.getSelectionIndex()).setText(1, "0.0");
//	                	  System.out.println(text.getText().toString());
//	                  }
//
//	                  @Override
//	                  public void focusLost(final FocusEvent e) {
//	                	  table.getItem(table.getSelectionIndex()).setText(1, text.getText().toString());
//	                	  
////	                   text.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
////	                   text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
//	                  }
//	                 });
	              
	              text.addModifyListener(new ModifyListener() {

	                  @Override
	                  public void modifyText(final ModifyEvent e) {
	                   table.getItem(table.getSelectionIndex()).setText(1, text.getText());
	                   			
	                  }
	                 });
        	}
		});
        }
    }
}