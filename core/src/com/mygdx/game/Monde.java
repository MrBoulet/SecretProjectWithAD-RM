package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Monde {
	private World world; // On créer notre monde.
	private Box2DDebugRenderer debugRenderer; // Permet de Debug l'affichage. A utiliser seulement lors des Debug.
	
	public Monde() {
		Box2D.init(); // On initialise Box2D.
		world = new World(new Vector2(0, -10), true); // Vector2(0, -10) -> Gravity. On va certainement mettre du (0,0). || true -> On veux que les objets puissent dormir.
		debugRenderer = new Box2DDebugRenderer();
	}
	
	void stepping() {
		world.step(1/60f, 6, 2); // $1 -> Temps de simulation. shift && shift. (On verra après).
	}
	
}
