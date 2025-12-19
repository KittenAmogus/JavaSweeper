package com.kittenamogus.game;

import java.util.Random;
import java.awt.*;
import java.awt.event.*;

import com.kittenamogus.graphics.Graphics;

public class Game {
	static int ROW		=	8;
	static int COL		=	8;
	static int MINES	=	10;

	static String TITLE		=	"JavaSweeper";

	Graphics graphics;
	Random random;
	Cell[] [] cells;

	boolean firstClick	=	false;
	boolean gameRunning	=	false;

	public Game() {
		this.graphics	=	new Graphics( COL, ROW, TITLE );
		this.random		=	new Random( 1488L );
	}

	// Init
	private void createMap() {
		this.cells = new Cell[ ROW ][ COL ];
		for ( int y=0; y<ROW; y++ ) {
			for ( int x=0; x<COL; x++ ) {
				Cell cell = new Cell( "", x, y );
				
				this.cells[ y ][ x ] = cell;
				cell.configureButton();

				cell.addActionListener( e -> {
					this.buttonEventListener( e );
				} );

				this.graphics.addCell( cell );
			}
		}
	}

	private void genMines( Cell startCell ) {
		for ( int i=0; i<MINES; i++ ) {
			int x, y;
			x	=	this.random.nextInt( COL );
			y	=	this.random.nextInt( ROW );
			
			if ( this.cells[ y ][ x ].isMine ||
				this.cells[ y ][ x ] == startCell) {
				i --;
				continue;
			}

			for ( int ay=y-1; ay<y+2; ay++ ) {
				if ( ay < 0 || ay >= COL )
					continue;
				for ( int ax=x-1; ax<x+2; ax++ ) {
					if ( ax < 0 || ax >= COL )
						continue;
					
					this.cells[ ay ][ ax ].nearMines ++;
				}
			}
			
			this.cells[ y ][ x ].isMine = true;
		}
	}

	private void restartGame() {
		for ( Cell[] row : this.cells ) {
			for ( Cell cell : row ) {
				cell.reset();
			}
		}

		this.firstClick = true;
		this.gameRunning = true;
	}

	// Game
	private void buttonEventListener( ActionEvent e ) {
		Cell cell = (Cell) e.getSource();

		if (! this.gameRunning ) {
			this.restartGame();
		}


		if ( this.firstClick ) {
			this.firstClick = false;
			this.genMines( cell );
		}
		this.openCell( cell );
	}

	private Cell[] getNeiborgs( Cell cell ) {
		Cell[] neibs = new Cell[8];
		char nlen = 0;

		for ( int x=cell.x-1; x<cell.x+2; x++) {
			if ( x < 0 || x >= COL )
				continue;
			for ( int y=cell.y-1; y<cell.y+2; y++ ) {
				if ( y < 0 || y >= ROW )
					continue;
				if ( y == cell.y && x == cell.x )
					continue;
			
			neibs[ nlen ++ ] = cells[ y ][ x ];
			}
		}

		return neibs;
	}

	private void openCell( Cell cell ) {
		if ( cell.openCell() ) {
			System.out.println( "Opened mine!" );
			this.gameRunning = false;
		}

		else if ( cell.nearMines == 0 ) {
			this.openCellNeiborgs( cell );
		}
	}

	private void openCellNeiborgs( Cell cell ) {
		for ( Cell a: this.getNeiborgs( cell ) ) {
			if ( a == null )
				break;
			if ( a.isOpen )
				continue;
			this.openCell( a );
		}
	}

	// Start

	public void start() {
		this.createMap();
		this.firstClick = true;
		this.gameRunning = true;

		this.graphics.init();
		this.graphics.drawFull( this.cells );
	}
}

