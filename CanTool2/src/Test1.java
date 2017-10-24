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

public class Test1 extends AbstractExample{
    private Table table;
    private Tree tree;
    public Test1() {
		shell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				System.out.println("hello");
			}
		});
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
                        item.setText(new String[]{database_dao.getSignal_name(),"0.0"});
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
	              text.forceFocus();
	              text.addModifyListener(new ModifyListener() {

	                  @Override
	                  public void modifyText(final ModifyEvent e) {
	                   editor.getItem().setText(editor.getColumn(), text.getText());
	                   			
	                  }
	                 });
	                 text.addFocusListener(new FocusListener() {
	                	 		
	                  @Override
	                  public void focusGained(final FocusEvent e) {
	                   // TODO Auto-generated method stub
	                	  		
	                  }

	                  @Override
	                  public void focusLost(final FocusEvent e) {
	                	  System.out.println(table.getItem(1).getText(1).toString());
//	                   text.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
//	                   text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
	                  }
	                 });
        	}
		});
        }
    }
}