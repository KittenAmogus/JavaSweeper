package com.kittenamogus.graphics;

import javax.swing.*;
import java.awt.*;

import com.kittenamogus.game.*;


public class Graphics {
	int COL;
	int ROW;
	String TITLE;

	JFrame frame;

	public Graphics( int col, int row, String title ) {
		this.COL	=	col;
		this.ROW	=	row;
		this.TITLE	=	title;

		this.frame	=	new JFrame( TITLE );
	}

	// Private

	private void configureFrame() {
		this.frame.setResizable( false );
		this.frame.setSize( 100 * COL, 100 * ROW );
		this.frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		this.frame.getContentPane().setLayout( new GridLayout( ROW, COL ) );

		this.frame.setLocation(
			1000, 200
		);

		Image icon = Toolkit.getDefaultToolkit().getImage(
			"icon.ico"
		);
		this.frame.setIconImage(icon);

		this.frame.setVisible( true );
	}

	// Public

	public void addCell( Cell cell ) {
		this.frame.getContentPane().add( cell );
	}

	public void init() {
		this.configureFrame();
	}

	public void draw() {

	}

	public void drawFull( Cell[] [] cells ) {
		for ( int y=0; y<ROW; y++ ) {
			for ( int x=0; x<COL; x++ ) {
				Cell cell = cells[ y ][ x ];
			}
		}
	}
}

