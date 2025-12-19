package com.kittenamogus.game;

import com.kittenamogus.graphics.Graphics;

public class Game {
	static int ROW	=	8;
	static int COL	=	8;
	static int MINES	=	10;

	static String TITLE		=	"JavaSweeper";

	Graphics graphics;

	public Game() {
		this.graphics = new Graphics( COL, ROW, TITLE );
	}

	// Init

	// Game

	// Start

	public void start() {
		this.graphics.init();
		this.graphics.drawFull();
	}
}

