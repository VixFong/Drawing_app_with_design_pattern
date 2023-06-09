package strategy;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.PrintWriter;

public class LogSaving implements Saving { // Class for saving log file
	
	
	private DefaultListModel<String> dlm;
	
	public LogSaving(DefaultListModel<String> defaultListModel) {
		this.dlm=defaultListModel;
	}
	

	@Override
	public void save() {
		JFileChooser jFileChooser=new JFileChooser("C:\\Users\\dell\\Desktop");
		jFileChooser.setFileFilter(new FileNameExtensionFilter("log file (.log)", "log"));
		jFileChooser.setDialogTitle("Please choose location to save!");
		
		if(jFileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
			File log = new File(jFileChooser.getSelectedFile().getAbsolutePath()+ ".log");
			
			if(log.exists()) {
				JOptionPane.showMessageDialog(null, "File with that name already exists! Try something unique!", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				try {
					PrintWriter printWriter = new PrintWriter(log);
					for(int i=0; i<dlm.size();i++) {
						printWriter.println(dlm.get(i));
					}
					printWriter.close();
					JOptionPane.showMessageDialog(null, "Successfully Saved!", "Saving Done :}",
							JOptionPane.INFORMATION_MESSAGE);
				}catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error while saving the file. Please contact a developer :)", "Error!",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
	}

}

