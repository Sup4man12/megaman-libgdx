package com.game.megaman;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Megaman extends Game {
	SpriteBatch batch;
	SpriteBatch batch2;
  BitmapFont texto;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		batch2 = new SpriteBatch();
    texto = new BitmapFont();
    this.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
           super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		batch2.dispose();
    texto.dispose();
	}
}
