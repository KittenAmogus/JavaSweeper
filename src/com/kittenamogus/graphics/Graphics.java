package com.kittenamogus.graphics;

import javax.swing.*;


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
		this.frame.setSize( 800, 800 );
		this.frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		this.frame.setVisible( true );
	}

	// Public

	public void init() {
		this.configureFrame();
	}

	public void draw() {

	}

	public void drawFull() {

	}
}

