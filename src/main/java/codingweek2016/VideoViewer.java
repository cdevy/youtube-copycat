package codingweek2016;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class VideoViewer {
	
	public VideoViewer(String title, final String id) {
		
		String url = "http://www.youtube.com/embed/"+id+"?autoplay=1";

        Display display = new Display();
        Shell shell = new Shell(display);

        Browser browser = new Browser(shell, SWT.NONE);
        browser.setBounds(5,5,500,300);

        browser.setUrl(url);    
        shell.open();

        browser.setVisible(true);
        shell.setVisible(true);

        while (!shell.isDisposed())
        {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
	}
}
