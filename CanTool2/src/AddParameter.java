import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class AddParameter extends Action {
	Shell parentShell;
	 public AddParameter(Shell parentShell) {
		 super();
		 this.parentShell=parentShell;
		 System.out.println("21 team");
		 setText("��(&O)");
		 setToolTipText("�����ļ�");  
	      
		// TODO �Զ����ɵĹ��캯�����
	}
		public class SecondShell {
	        private Shell shell;
	        public SecondShell() {
	                Display display = Display.getDefault();
	                createContents(display);
	                shell.open();
	        }
	        
	        protected void createContents(Display display) {
	        	try {
	    			Parameter_Seeting window = new Parameter_Seeting();
	    			window.setBlockOnOpen(true);
	    			window.open();
	    			Display.getCurrent().dispose();
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
	        }
	}

	@Override
	public void run() {
		// TODO �Զ����ɵķ������
//		new SecondShell();
		setting s=new setting(parentShell);
		
		s.open();
		 /*MessageBox messageBox = new MessageBox(null);
	        messageBox.setMessage("Hello World!");
	        messageBox.open();*/
	}
	

}
