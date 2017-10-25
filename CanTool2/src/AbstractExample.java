import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class AbstractExample{
	  public static Shell shell=new Shell();
	  public AbstractExample() {
		  shell= new Shell();
	}
    public void run(){
    	
        shell.setText("±à¼­Êý¾Ý");
        shell.setBounds(200,200,400,400);
        shell.setLayout(new FillLayout());
        todo(shell);
        shell.open();
  
    }
    public abstract void todo(Shell shell);//extension something here
}