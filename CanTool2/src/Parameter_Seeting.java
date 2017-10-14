import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;

public class Parameter_Seeting extends ApplicationWindow {

	/**
	 * Create the application window.
	 */
	public Parameter_Seeting() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		Combo combo = new Combo(container, SWT.NONE);
		combo.setItems(new String[] {"9600", "19200", "36400", "57600", "115200"});
		combo.setBounds(106, 5, 88, 25);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(27, 8, 61, 17);
		lblNewLabel.setText("\u6CE2\u7279\u7387");
		
		Label label = new Label(container, SWT.NONE);
		label.setText("\u6570\u636E\u4F4D");
		label.setBounds(27, 45, 61, 17);
		
		Combo combo_1 = new Combo(container, SWT.NONE);
		combo_1.setItems(new String[] {"5", "6", "7", "8"});
		combo_1.setBounds(106, 42, 88, 25);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("\u505C\u6B62\u4F4D");
		label_1.setBounds(27, 87, 61, 17);
		
		Combo combo_2 = new Combo(container, SWT.NONE);
		combo_2.setItems(new String[] {"1", "1.5", "2"});
		combo_2.setBounds(106, 84, 88, 25);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setText("\u4E32\u53E3");
		label_2.setBounds(27, 134, 61, 17);
		
		Combo combo_3 = new Combo(container, SWT.NONE);
		combo_3.setItems(new String[] {"COM2", "COM3"});
		combo_3.setBounds(106, 131, 88, 25);

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
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
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("New Application");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
