package com.adriapp.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	InitializeBox2D ib2d;
	
	@Override
	public void create () {
		Gdx.graphics.setTitle("AdRi");
		ib2d = new InitializeBox2D();
		/*batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");*/
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		ib2d.render();
		/*batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
	}
	
	@Override
	public void dispose () {
		ib2d.dispose();
	}
	
	public void update (float delta) {
		ib2d.update(delta);
	}
}
