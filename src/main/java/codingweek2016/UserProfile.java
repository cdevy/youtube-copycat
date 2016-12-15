package codingweek2016;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class UserProfile {
	
	public UserProfile(String name, String url){
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setBounds(20,20,700,628);
        shell.setText(name+"'s profile");

        Browser browser = new Browser(shell, SWT.NONE);
        browser.setBounds(0,0,700,600);
        browser.setUrl(url);
        
        shell.open();

        browser.setVisible(true);
        shell.setVisible(true);
        
        /* While the viewer window is open,  */
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
	}
}
