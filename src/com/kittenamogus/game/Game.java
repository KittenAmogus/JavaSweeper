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

	public Game() {
		this.graphics	=	new Graphics( COL, ROW, TITLE );
		this.random		=	new Random( 1488L );
	}

	// Init
	private void createMap() {
		this.cells = new Cell[ ROW ][ COL ];
		for ( int y=0; y<ROW; y++ ) {
			for ( int x=0; x<COL; x++ ) {
				Cell cell = new Cell( "#", x, y );
				
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

	// Game
	private void buttonEventListener( ActionEvent e ) {
		Cell cell = (Cell) e.getSource();

		this.openCell( cell );

		if ( this.firstClick ) {
			this.firstClick = false;
			this.genMines( cell );
		}
	} 

	private void openCell( Cell cell ) {
		if ( cell.openCell() ) {
			System.out.println( "Opened mine!" );
		}
	}

	// Start

	public void start() {
		this.createMap();
		this.firstClick = true;

		this.graphics.init();
		this.graphics.drawFull( this.cells );
	}
}

