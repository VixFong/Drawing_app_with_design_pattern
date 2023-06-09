package mvc;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class FrmDrawing extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textBcColor;
	private JTextField textFillColor;
	public  Color shapeColor = Color.BLACK;
	private Color fillColor = Color.WHITE;
	private final ButtonGroup btnG = new ButtonGroup();
	private final ButtonGroup btnG1 = new ButtonGroup();
	
	
	private DrawingController controller;
	private PnlDrawing view = new PnlDrawing(); 
	

	private DefaultListModel<String> dlm = new DefaultListModel<String>(); 
	
	
	//Buttons for shapes
	private JToggleButton tgbtnPoint;
	private JToggleButton tgbtnLine;
	private JToggleButton tgbtnCircle;
	private JToggleButton tgbtnRectangle;
	private JToggleButton tgbtnDonut;
//	private JToggleButton tgbtnHexagon;
	
	// Buttons for color panel
	private JButton btnSetBcColor;
	private JButton btnSetFillColor;
	
	//Buttons for modify i delete
	private JButton btnModify;
	private JButton btnDelete;
	
	//Buttons for mode of work
	private JToggleButton tglbtnDraw;
	private JToggleButton tglbtnSelect;
	
	//Undo and Redo buttons
	private JButton btnUndo;
	private JButton btnRedo;
	
	//Buttons for position
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	
	private JButton btnSave;
	
	
	// components for log part of frame
	private JScrollPane scrollPane;
	private JList<String> Loglist;
	private JPanel logPanel;
	private JButton btnLoadNext;

	/**
	 * Create the frame.
	 */
	public FrmDrawing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 731);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setTitle("Painting Application");
		view.setBounds(151, 95, 669, 522);
		
		view.setLayout(null);
		view.setBackground(Color.WHITE);
		contentPane.add(view,BorderLayout.CENTER);
		
		//Panel with all shapes buttons
		JPanel ShapesBtnPanel = new JPanel();
		ShapesBtnPanel.setBounds(15, 16, 126, 342);
		ShapesBtnPanel.setBackground(UIManager.getColor("Button.background"));
		ShapesBtnPanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Shapes", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		//Pannel for mode--draw or select
		JPanel modePanel = new JPanel();
		modePanel.setBounds(15, 364, 125, 119);
		modePanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Mode", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		//Border color panel
		JPanel bColorPanel = new JPanel();
		bColorPanel.setBounds(151, 16, 202, 72);
		bColorPanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Border Color", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		bColorPanel.setBackground(SystemColor.menu);
		
		
		//Button for setting border color 
		btnSetBcColor = new JButton("Set Color");
		btnSetBcColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSetBcColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeColor=JColorChooser.showDialog(null, "Color--BORDER--Color", shapeColor);
				if(shapeColor==null) {
					shapeColor=Color.BLACK;
					textBcColor.setBackground(Color.BLACK);
				}else {
					textBcColor.setBackground(shapeColor);
				}
				
				
			}
		});
		
		textBcColor = new JTextField();
		textBcColor.setColumns(10);
		textBcColor.setEditable(false);
		textBcColor.setBackground(shapeColor);
		GroupLayout gl_bColorPanel = new GroupLayout(bColorPanel);
		gl_bColorPanel.setHorizontalGroup(
			gl_bColorPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bColorPanel.createSequentialGroup()
					.addComponent(btnSetBcColor)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textBcColor, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		gl_bColorPanel.setVerticalGroup(
			gl_bColorPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bColorPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_bColorPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSetBcColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(textBcColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		bColorPanel.setLayout(gl_bColorPanel);
		
		
		//Button for setting fill color 
		JPanel fColorPanel = new JPanel();
		fColorPanel.setBounds(359, 16, 192, 73);
		fColorPanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Fill Color", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		fColorPanel.setBackground(SystemColor.menu);
		
		textFillColor = new JTextField();
		textFillColor.setColumns(10);
		textFillColor.setEditable(false);
		textFillColor.setBackground(fillColor);
		
		btnSetFillColor = new JButton("Set Color");
		btnSetFillColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSetFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillColor=JColorChooser.showDialog(null, "Color--FILL--Color", fillColor);
				if(fillColor==null) {
					fillColor=Color.BLACK;
					textFillColor.setBackground(Color.WHITE);
				}else {
					textFillColor.setBackground(fillColor);
				}
				
				
			}
		});
		
		
		GroupLayout gl_fColorPanel = new GroupLayout(fColorPanel);
		gl_fColorPanel.setHorizontalGroup(
			gl_fColorPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_fColorPanel.createSequentialGroup()
					.addComponent(btnSetFillColor)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textFillColor, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_fColorPanel.setVerticalGroup(
			gl_fColorPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_fColorPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_fColorPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSetFillColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFillColor, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		fColorPanel.setLayout(gl_fColorPanel);
		
		
		//Panel for Modify and Delete button
		JPanel actonPanel = new JPanel();
		actonPanel.setBounds(15, 494, 126, 118);
		actonPanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Action", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		actonPanel.setBackground(SystemColor.menu);
		
		//Modify button
		btnModify = new JButton("Modify");
		btnModify.setEnabled(false);
		btnModify.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnModify.isEnabled()) {
					controller.modifyShape();
				}
			}
		});
		
		//Delete button
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnDelete.isEnabled()) {					
						controller.deleteShape();
				}
			}
		});
		
		GroupLayout gl_actonPanel = new GroupLayout(actonPanel);
		gl_actonPanel.setHorizontalGroup(
			gl_actonPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_actonPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_actonPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnModify, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_actonPanel.setVerticalGroup(
			gl_actonPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_actonPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnModify, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(7))
		);
		actonPanel.setLayout(gl_actonPanel);
		
		//Panel for undo and redo button
		JPanel undoRedoPanel = new JPanel();
		undoRedoPanel.setBounds(15, 623, 221, 68);
		undoRedoPanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Undo/Redo", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		//Undo button
		btnUndo = new JButton("Undo");
		btnUndo.setEnabled(false);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnUndo.isEnabled()) {
				controller.undo();
				}
				
			}
		});
		btnUndo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		//Redo button
		btnRedo = new JButton("Redo");
		btnRedo.setEnabled(false);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnRedo.isEnabled()) {
				controller.redo();
				}
			}
		});
		btnRedo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_undoRedoPanel = new GroupLayout(undoRedoPanel);
		gl_undoRedoPanel.setHorizontalGroup(
			gl_undoRedoPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_undoRedoPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnUndo, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
					.addGap(34)
					.addComponent(btnRedo, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_undoRedoPanel.setVerticalGroup(
			gl_undoRedoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_undoRedoPanel.createSequentialGroup()
					.addGroup(gl_undoRedoPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUndo, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRedo, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		undoRedoPanel.setLayout(gl_undoRedoPanel);
		
		//Pannel for position change button
		JPanel frontBackPanel = new JPanel();
		frontBackPanel.setBounds(561, 16, 518, 73);
		frontBackPanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Position", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frontBackPanel.setBackground(SystemColor.menu);
		
		// Button to move shape one place up
		btnToFront = new JButton("To Front");
		btnToFront.setEnabled(false);
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnToFront.isEnabled()) {
				controller.toFront();
				
				}
				
			}
		});
		btnToFront.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		// Button to move shape one place down
		btnToBack = new JButton("To Back");
		btnToBack.setEnabled(false);
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnToBack.isEnabled()) {
				controller.toBack();
				
				}
				
			}
		});
		btnToBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		// Button to move shape on top
		btnBringToFront = new JButton("Bring to front");
		btnBringToFront.setEnabled(false);
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnBringToFront.isEnabled()) {
				controller.bringToFront();
				
				}
			}
		});
		btnBringToFront.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		// Button to move shape on bottom
		btnBringToBack = new JButton("Bring to back");
		btnBringToBack.setEnabled(false);
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnBringToBack.isEnabled()) {
				controller.bringToBack();
				
				}
			}
		});
		btnBringToBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_frontBackPanel = new GroupLayout(frontBackPanel);
		gl_frontBackPanel.setHorizontalGroup(
			gl_frontBackPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_frontBackPanel.createSequentialGroup()
					.addGap(14)
					.addComponent(btnToFront, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnToBack, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(btnBringToFront)
					.addGap(18)
					.addComponent(btnBringToBack)
					.addContainerGap())
		);
		gl_frontBackPanel.setVerticalGroup(
			gl_frontBackPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_frontBackPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_frontBackPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBringToBack, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBringToFront, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnToBack, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnToFront, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		frontBackPanel.setLayout(gl_frontBackPanel);
		
		//Log panel setup
		logPanel = new JPanel();
		logPanel.setBounds(830, 95, 249, 596);
		logPanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Log", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		logPanel.setLayout(null);
		
		//scroll pane for log panel
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 12, 249, 584);
		
		scrollPane.setAutoscrolls(true);
		
		logPanel.add(scrollPane); //Setting scroll pane to logPanel
		
		//Log list for all logs 
		Loglist = new JList<String>();
		Loglist.setBackground(SystemColor.controlLtHighlight);
		Loglist.setBounds(16, 450, 148, 479);
		;
		
		
		scrollPane.setViewportView(Loglist); // adding logList to scrollPane for logging
		Loglist.setModel(dlm);
		
		
		contentPane.add(undoRedoPanel);				
		
		
		tglbtnDraw = new JToggleButton("Draw");
		tglbtnDraw.setSelected(true);
		
		tglbtnDraw.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		
		tglbtnSelect = new JToggleButton("Select");
		tglbtnSelect.setEnabled(false);;
		tglbtnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			
						tglbtnSelect.setSelected(true);		
			}
		});
		tglbtnSelect.setSelected(true);
		
		
		tglbtnSelect.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		GroupLayout gl_modePanel = new GroupLayout(modePanel);
		gl_modePanel.setHorizontalGroup(
			gl_modePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_modePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_modePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(tglbtnSelect, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnDraw, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_modePanel.setVerticalGroup(
			gl_modePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modePanel.createSequentialGroup()
					.addGap(7)
					.addComponent(tglbtnDraw, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tglbtnSelect, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		modePanel.setLayout(gl_modePanel);
		
		tgbtnPoint = new JToggleButton("Point");
		tgbtnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnDraw.setSelected(true);
			}
		});
		tgbtnPoint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tgbtnPoint.setForeground(Color.BLACK);
		tgbtnPoint.setBackground(UIManager.getColor("Button.darkShadow"));
		
		tgbtnLine = new JToggleButton("Line");
		tgbtnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnDraw.setSelected(true);
			}
		});
		tgbtnLine.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tgbtnLine.setBackground(UIManager.getColor("Button.darkShadow"));
		
		tgbtnRectangle = new JToggleButton("Rectangle");
		tgbtnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnDraw.setSelected(true);
			}
		});
		tgbtnRectangle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tgbtnRectangle.setBackground(UIManager.getColor("Button.darkShadow"));
		
		tgbtnCircle = new JToggleButton("Circle");
		tgbtnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnDraw.setSelected(true);
			}
		});
		tgbtnCircle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		tgbtnDonut = new JToggleButton("Donut");
		tgbtnDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnDraw.setSelected(true);
			}
		});
		tgbtnDonut.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
//		tgbtnHexagon = new JToggleButton("Hexagon");
//		tgbtnHexagon.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				tglbtnDraw.setSelected(true);
//			}
//		});
//		tgbtnHexagon.setFont(new Font("Tahoma", Font.PLAIN, 13));

		GroupLayout gl_ShapesBtnPanel = new GroupLayout(ShapesBtnPanel);
		gl_ShapesBtnPanel.setHorizontalGroup(
			gl_ShapesBtnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ShapesBtnPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ShapesBtnPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(tgbtnDonut, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
						.addComponent(tgbtnCircle, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
						.addComponent(tgbtnRectangle, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
						.addComponent(tgbtnLine, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
						.addComponent(tgbtnPoint, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
//						.addComponent(tgbtnHexagon, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_ShapesBtnPanel.setVerticalGroup(
			gl_ShapesBtnPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ShapesBtnPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(tgbtnPoint, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(tgbtnLine, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(tgbtnRectangle, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(tgbtnCircle, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(tgbtnDonut, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
//					.addComponent(tgbtnHexagon, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		ShapesBtnPanel.setLayout(gl_ShapesBtnPanel);
		
		//Shapes button group
		btnG.add(tgbtnPoint);
		btnG.add(tgbtnLine);
		btnG.add(tgbtnRectangle);
		btnG.add(tgbtnCircle);
		btnG.add(tgbtnDonut);
//		btnG.add(tgbtnHexagon);
		
		//Mode button group
		btnG1.add(tglbtnDraw);
		btnG1.add(tglbtnSelect);
		
		tgbtnPoint.setSelected(true);
		contentPane.setLayout(null);
		contentPane.add(view);
		contentPane.add(ShapesBtnPanel);
		contentPane.add(modePanel);
		contentPane.add(actonPanel);
		contentPane.add(bColorPanel);
		contentPane.add(fColorPanel);
		contentPane.add(frontBackPanel);
		contentPane.add(logPanel);
		
		//Panel for saving and loading buttons
		JPanel saveOpenPanel = new JPanel();
		saveOpenPanel.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)), "Save/Load", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		saveOpenPanel.setBounds(488, 623, 332, 68);
		contentPane.add(saveOpenPanel);
		
		
		//Button for saving log or ser
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] choice = { "Save Log", "Save Drawing"};
				int optionChosen=JOptionPane.showOptionDialog(null, "Choose What To Save!", "Save File!",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, choice, choice[0]);
				controller.save(optionChosen);
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSave.setEnabled(true);
		
		//Open button for loading from files
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] choice = { "Open Log", "Open Drawing"};
				int optionChosen=JOptionPane.showOptionDialog(null, "Choose Loading Option!", "Open :)",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, choice, choice[0]);
				controller.open(optionChosen);
			}
		});
		btnOpen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnOpen.setEnabled(true);
		
		
		// Button for loading log step by step
		btnLoadNext = new JButton("Load Next");
		btnLoadNext.setVisible(false);
		btnLoadNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnLoadNext.isEnabled()) {
					controller.loadNext();
				}
			}
		});
		btnLoadNext.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLoadNext.setEnabled(false);
		GroupLayout gl_saveOpenPanel = new GroupLayout(saveOpenPanel);
		gl_saveOpenPanel.setHorizontalGroup(
			gl_saveOpenPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_saveOpenPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnLoadNext, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_saveOpenPanel.setVerticalGroup(
			gl_saveOpenPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_saveOpenPanel.createSequentialGroup()
					.addGroup(gl_saveOpenPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_saveOpenPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnLoadNext, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		saveOpenPanel.setLayout(gl_saveOpenPanel);
		
	
		
		//Action when user click on panel
		view.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(tglbtnSelect.isSelected()) {
					
					controller.selectShape(e);
				}
				
				if(tglbtnDraw.isSelected()) {
					controller.addShape(e);
				}
				
				
				
			}
			
			
			
		});
		
	}
	
	
	public void addToLogList(String string)
	{
		this.dlm.addElement(string);
	}
	
	
	

//	public JToggleButton getTgbtnHexagon() {
	//		return tgbtnHexagon;
//	}

	public JToggleButton getTgbtnPoint() {
		return tgbtnPoint;
	}

	public JToggleButton getTgbtnLine() {
		return tgbtnLine;
	}

	public JToggleButton getTgbtnCircle() {
		return tgbtnCircle;
	}

	public JToggleButton getTgbtnRectangle() {
		return tgbtnRectangle;
	}

	public JToggleButton getTgbtnDonut() {
		return tgbtnDonut;
	}

	public DrawingController getController() {
		return controller;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

	public PnlDrawing getView() {
		return view;
	}

	public void setView(PnlDrawing view) {
		this.view = view;
	}



	public Color getShapeColor() {
		return shapeColor;
	}




	public void setShapeColor(Color shapeColor) {
		this.shapeColor = shapeColor;
	}




	public Color getFillColor() {
		return fillColor;
	}




	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}




	public JButton getBtnModify() {
		return btnModify;
	}




	



	public JButton getBtnDelete() {
		return btnDelete;
	}




	public JButton getBtnUndo() {
		return btnUndo;
	}




	public JButton getBtnRedo() {
		return btnRedo;
	}




	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}




	public JButton getBtnToFront() {
		return btnToFront;
	}




	public JButton getBtnToBack() {
		return btnToBack;
	}




	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}




	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}




	public DefaultListModel<String> getDlm() {
		return dlm;
	}




	public void setDlm(DefaultListModel<String> dlm) {
		this.dlm = dlm;
	}


	public JButton getBtnLoadNext() {
		return btnLoadNext;
	}
	
}
