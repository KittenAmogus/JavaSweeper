package com.kittenamogus.game;


import javax.swing.JButton;
import java.awt.*;


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

	public void configureButton() {
		this.setPreferredSize( new Dimension(100, 100) );
		this.setFont( new Font( "Arial", Font.BOLD, 24 ) );
	}

	public void onButtonClick() {
		this.openCell();
	}

	public boolean openCell() {
		if ( this.isOpen || this.isFlag )
			return false;

		this.isOpen = true;

		if ( this.isMine ) {
			this.setText( "@" );
		}
		else if ( this.isFlag ) {
			this.setText( "F" );
		}
		else {
			switch ( this.nearMines ) {
				case ( 1 ): { this.setText( "1" ); break; }
				case ( 2 ): { this.setText( "2" ); break; }
				case ( 3 ): { this.setText( "3" ); break; }
				case ( 4 ): { this.setText( "4" ); break; }
				case ( 5 ): { this.setText( "5" ); break; }
				case ( 6 ): { this.setText( "6" ); break; }
				case ( 7 ): { this.setText( "7" ); break; }
				case ( 8 ): { this.setText( "8" ); break; }
				case ( 0 ): { this.setText( "" ); break; }
				default: { this.setText( "Error" ); }
			}
		}

		return this.isMine;
	}
}

