package com.kittenamogus.game;

import java.util.Random;
import java.awt.*;

import com.kittenamogus.graphics.Graphics;

public class Game {
	static int ROW		=	8;
	static int COL		=	8;
	static int MINES	=	10;

	static String TITLE		=	"JavaSweeper";

	Graphics graphics;
	Random random;
	Cell[] [] cells;

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
					cell.onButtonClick();
				} );

				this.graphics.addCell( cell );
			}
		}

		for ( int i=0; i<MINES; i++ ) {
			int x, y;
			x	=	this.random.nextInt( COL );
			y	=	this.random.nextInt( ROW );
			
			if ( this.cells[ y ][ x ].isMine ) {
				i --;
				continue;
			}
			
			this.cells[ y ][ x ].isMine = true;
		}
	}

	// Game

	// Start

	public void start() {
		this.createMap();

		this.graphics.init();
		this.graphics.drawFull( this.cells );
	}
}

