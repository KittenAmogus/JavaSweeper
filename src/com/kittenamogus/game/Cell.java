package com.kittenamogus.game;


import javax.swing.JButton;
import java.awt.*;
import java.awt.Color;


public class Cell extends JButton {
	public int x, y;
	public boolean isMine, isOpen, isFlag;
	public int nearMines;

	public Cell( String text, int x, int y ) {

		super( text );

		this.x = x;
		this.y = y;

		this.isMine = false;
		this.isOpen = false;
		this.isFlag = false;

		this.nearMines = 0;
	}

	public void reset() {
		this.isMine = false;
		this.isOpen = false;
		this.isFlag = false;

		this.nearMines = 0;
		this.setText( "" );
		this.setBackground( Color.LIGHT_GRAY );
	}

	public void configureButton() {
		this.setPreferredSize( new Dimension(100, 100) );
		this.setFont( new Font( "Arial", Font.BOLD, 24 ) );
	}

	public void onButtonClick() {
		this.openCell();
	}

	private void reconfigure() {
		this.setForeground( Color.LIGHT_GRAY );

		if ( this.isFlag ) {
			this.setBackground( Color.YELLOW );
			this.setText( "F" );
			return;
		}
		if ( ! this.isOpen ) {
			// this.setText( "F" );
			return;
		}
		if ( this.isMine ) {
			this.setBackground( Color.RED );
			this.setText( "@" );
			return;
		}
		
		if ( this.nearMines != 0 )
			this.setText( String.valueOf( this.nearMines ) );
		else
			this.setText( "" );

		switch ( this.nearMines ) {
			case ( 1 ): { this.setBackground( new Color(0, 0, 255) ); break; }
			case ( 2 ): { this.setBackground( new Color(0, 255, 0) ); break; }
			case ( 3 ): { this.setBackground( new Color(255, 0, 0) ); break; }
			case ( 4 ): { this.setBackground( new Color(0, 0, 100) ); break; }
			case ( 5 ): { this.setBackground( new Color(100, 0, 0) ); break; }
			case ( 6 ): { this.setBackground( new Color(0, 255, 255) ); break; }
			case ( 7 ): { this.setBackground( new Color(255, 80, 80) ); break; }
			case ( 8 ): { this.setBackground( new Color(30, 30, 30) ); break; }
			case ( 0 ): { this.setBackground( new Color(80, 80, 80) ); break; }
		}
	}

	public boolean openCell() {
		if ( this.isOpen || this.isFlag )
			return false;

		this.isOpen = true;

		this.reconfigure();

		return this.isMine;
	}
}

